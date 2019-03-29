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
package com.piedpiper.model;

import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class ContactsList {
  private ArrayList<String> list;

  public ContactsList(ArrayList<String> contacts) {
    this.list = contacts;
  }

  public ContactsList() {
    this(new ArrayList<>());
  }

  public void addContact(String contact){
    this.list.add(contact);
  }

  public UserProfile getContactInfo(String contact) {
    // implement later
    return null;
  }

  public String removeContact(String contact) {
    if (this.list.contains(contact)) {
      return this.list.remove(this.list.indexOf(contact));
    }

    return null;
  }

  public String[] toArray() {
    return this.list.toArray(new String[this.list.size()]);
  }
}
