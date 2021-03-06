/*
 * MIT License
 *
 * Copyright (c) 2019 Nailah Azeez, Jaskiran Lamba, Sandeep Suri, Kent Tsuenchy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.piedpiper.communication;

import com.piedpiper.gui.ChatAreaController;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kent Tsuenchy
 */
public class ClientConnectionManager {
  private static final Logger LOG = Logger.getLogger(ClientConnectionManager.class.getName());
  private final SocketChannel CHANNEL;
  private final String NAME;
  private final ArrayList<Datagram> RECEIVE_DATAGRAMS;
  private final Selector SELECTOR;
  private final ArrayList<Datagram> SEND_DATAGRAMS;
  private final TaskHandler TASK_HANDLER;
  private boolean channelActive;
  private ChatAreaController client;
  private boolean die;

  /**
   *
   * @param name should be less than 40 characters
   * <p>
   * @throws IOException
   * @throws com.piedpiper.communication.ChannelSelectorCannotStartException
   */
  public ClientConnectionManager(String name) throws IOException, ChannelSelectorCannotStartException {
    this.TASK_HANDLER = new TaskHandler();
    this.SEND_DATAGRAMS = new ArrayList<>();
    this.RECEIVE_DATAGRAMS = new ArrayList<>();
    this.channelActive = false;
    this.die = false;

    try {
      SELECTOR = Selector.open();
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, null, ex);

      throw new ChannelSelectorCannotStartException("Failed to start up conneciton.");
    }

    this.NAME = name;
    this.CHANNEL = SocketChannel.open();

    Runnable selectorTask = () -> {
      // TODO need to check how to kill thread if required to force quit
      while (true)
        try {
          if (this.die)
            break;

          Thread.sleep(200);

          int readyChannels = SELECTOR.selectNow();

          if (readyChannels == 0)
            continue;

          Set<SelectionKey> selectedKeys = SELECTOR.selectedKeys();
          Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

          while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            if (!this.channelActive) {
              this.channelActive = true;

              if (key.isReadable()) {
                this.CHANNEL.read(buffer);
                buffer.flip();
                if (this.client != null && buffer.array().length > 0) {
                  Datagram d = Datagram.fromBytes(buffer.array());
                  this.client.updateMessageList(d.getSender(), d.getData());
                } else
                  this.RECEIVE_DATAGRAMS.add(Datagram.fromBytes(buffer.array()));
              } else if (key.isWritable() && this.SEND_DATAGRAMS.size() > 0) {
                buffer.put(this.SEND_DATAGRAMS.remove(0).getBytes());
                buffer.flip();
                this.CHANNEL.write(buffer);
              }
            }

            keyIterator.remove();
            this.channelActive = false; // may cause issues if more than one channel is being used
          }
        } catch (IOException ex) {
          // TODO log this to log file
          LOG.log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
          break;
        }
    };

    this.TASK_HANDLER.startTask(selectorTask);

    this.SEND_DATAGRAMS.add(new Datagram(Datagram.DATA_TYPE.UPDATE_ID, name, ServerConnectionManager.SERVER_NAME, name));

    this.CHANNEL.configureBlocking(false);
    this.CHANNEL.connect(new InetSocketAddress("localhost", 6000));

    while (this.CHANNEL.isConnectionPending())
      // do nothing for now, later on do set up if there is any
      this.CHANNEL.finishConnect();

    this.CHANNEL.register(this.SELECTOR, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
  }

  public void cleanup() {
    this.TASK_HANDLER.cleanup();
    this.die = true;
  }

  /**
   *
   * @param recipient should be less than 40 characters
   * @param message   should be less than 120 characters
   */
  public void sendMessage(String recipient, String message) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    Datagram datagram = new Datagram(Datagram.DATA_TYPE.MESSAGE, NAME, recipient, message);

    this.SEND_DATAGRAMS.add(datagram);
  }

  public void setClient(ChatAreaController client) {
    this.client = client;
  }
}
