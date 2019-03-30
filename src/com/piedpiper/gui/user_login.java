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
package com.piedpiper.gui;

/**
 *
 * @author sandeepsuri
 */
public class user_login {
  Integer id;
  String user_email, user_password;
  
  public user_login(Integer id, String user_email, String user_password) {
    this.id = id;
    this.user_email = user_email;
    this.user_password = user_password;
  }
  
  public user_login(int id, String user_email) {} //Jas added id, user_email and user_passwords in as parameters to test if this causes any erros please delete
  
  public Integer getId() {return id;}
  public void setId(Integer id) {this.id = id;}
  
  public String getEmail() {return user_email;}
  public void setEmail(String user_email) {this.user_email = user_email;}
  
  public String getPassword() {return user_password;}
  public void setPassword(String user_password) {this.user_password = user_password;}

}
