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

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Objects;

/**
 *
 * @author Kent Tsuenchy
 */
public class Datagram {
  public static final String STRING_ENCODING = "ISO-8859-1";
  private static final int MAX_DATA_LENGTH = 120;
  private static final int MAX_TIMESTAMP_LENGTH = 24;
  private static final int MAX_TYPE_LENGTH = 15;

  public static Datagram fromBytes(byte[] bytes) throws UnsupportedEncodingException {
    int pad = ServerConnectionManager.MAX_NAME_LENGTH;
    String temp = new String(bytes, STRING_ENCODING);
    String sender;
    String receiver;
    DATA_TYPE type;
    Instant timestamp;
    String data;

    sender = temp.substring(0, pad).trim();
    receiver = temp.substring(pad, pad * 2).trim();
    type = DATA_TYPE.valueOf(temp.substring(pad * 2, pad * 2 + MAX_TYPE_LENGTH).trim());
    pad = pad * 2 + MAX_TYPE_LENGTH;
    timestamp = Instant.parse(temp.substring(pad, pad + MAX_TIMESTAMP_LENGTH).trim());
    pad += MAX_TIMESTAMP_LENGTH;
    data = temp.substring(pad).trim();

    return new Datagram(type, sender, receiver, data, timestamp);
  }

  private final String DATA;
  private final String RECEIVER_ID;
  private final String SENDER_ID;
  private final Instant TIMESTAMP;
  private final DATA_TYPE TYPE;

  public Datagram(DATA_TYPE type, String sender, String receiver, String data, Instant timestamp) {
    this.TYPE = type;
    this.SENDER_ID = sender;
    this.RECEIVER_ID = receiver;
    this.DATA = data;
    this.TIMESTAMP = timestamp;
  }

  public Datagram(DATA_TYPE type, String sender, String receiver, String data) {
    this(type, sender, receiver, data, Instant.now());
  }

  @Override
  public boolean equals(Object o) {
    boolean equal = false;

    if (o instanceof Datagram) {
      Datagram d = (Datagram) o;

      equal = this.hashCode() == d.hashCode();
    }

    return equal;
  }

  public byte[] getBytes() throws UnsupportedEncodingException {
    int pad = ServerConnectionManager.MAX_NAME_LENGTH;
    String temp;

    temp = String.format("%-" + pad + "s%-" + pad + "s", this.SENDER_ID, this.RECEIVER_ID);
    temp += String.format("%-" + MAX_TYPE_LENGTH + "s", this.TYPE.toString());
    temp += String.format("%-" + MAX_TIMESTAMP_LENGTH + "s", this.TIMESTAMP.toString());
    temp += String.format("%-" + MAX_DATA_LENGTH + "s", this.DATA);

    return temp.getBytes(STRING_ENCODING);
  }

  public String getData() {
    return this.DATA;
  }

  public String getReceiver() {
    return this.RECEIVER_ID;
  }

  public String getSender() {
    return this.SENDER_ID;
  }

  public Instant getTimestamp() {
    return this.TIMESTAMP;
  }

  public DATA_TYPE getType() {
    return this.TYPE;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.DATA);
    hash = 97 * hash + Objects.hashCode(this.RECEIVER_ID);
    hash = 97 * hash + Objects.hashCode(this.SENDER_ID);
    hash = 97 * hash + Objects.hashCode(this.TIMESTAMP);
    hash = 97 * hash + Objects.hashCode(this.TYPE);
    return hash;
  }

  public static enum DATA_TYPE {
    MESSAGE, NOTIFICATION, UPDATE_ID
  }
}
