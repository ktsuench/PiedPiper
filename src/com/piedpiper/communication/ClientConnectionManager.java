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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

/**
 *
 * @author Kent Tsuenchy
 */
public class ClientConnectionManager {
  private static final Logger LOG = Logger.getLogger(ClientConnectionManager.class.getName());
  private final SocketChannel channel;
  private final String name;

  public ClientConnectionManager(String name) throws IOException {
    Datagram updateName = new Datagram(Datagram.DATA_TYPE.UPDATE_ID, name, ServerConnectionManager.SERVER_NAME, name);
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    this.name = name;
    this.channel = SocketChannel.open();

    // place datagram in buffer
    buffer.put(updateName.getBytes());
    // flip buffer for writing to server
    buffer.flip();

    this.channel.connect(new InetSocketAddress("localhost", 6000));
    this.channel.write(buffer);
  }
}
