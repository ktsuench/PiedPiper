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

import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jlamba
 */
public class user_loginTest {
  
  public user_loginTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }
  
public boolean equals(Object o){
    if(getClass() == o.getClass()){
        return true;
    }
    else{
        return false;}
}
  
  /**
   * Test of getId method, of class user_login.
   */
  @Test
  public void testGetId() {
    System.out.println("getId");
    user_login instance = new user_login(1, "email");
    Integer expResult = 1;
    Integer result = instance.getId();
    instance.equals(result);
  }

  /**
   * Test of setId method, of class user_login.
   */
  @Test
  public void testSetId() throws NoSuchFieldException, IllegalAccessException {
    System.out.println("setId");
    Integer id = 1;
    user_login instance = new user_login(1, "email");
    instance.setId(id);
    // TODO review the generated test code and remove the default call to fail.
    assertTrue(instance.getId() == 1);
  }

  /**
   * Test of getEmail method, of class user_login.
   */
  @Test
  public void testGetEmail() {
    System.out.println("getEmail");
    user_login instance = new user_login(1, "jas@gmail.com");
    String expResult = "jas@gmail.com";
    String result = instance.getEmail();
    instance.equals(result);
  }

  /**
   * Test of setEmail method, of class user_login.
   */
  @Test
  public void testSetEmail() {
    System.out.println("setEmail");
    String user_email = "jas@gmail.com";
    user_login instance = new user_login(1, "email");
    instance.setEmail(user_email);
    // TODO review the generated test code and remove the default call to fail.
    assertTrue(instance.getEmail() == "jas@gmail.com");
  }

  /**
   * Test of getPassword method, of class user_login.
   */
  @Test
  public void testGetPassword() {
    System.out.println("getPassword");
    user_login instance = new user_login(1, "email", "test1234");
    String expResult = "test1234";
    String result = instance.getPassword();
    instance.equals(result);
  }

  /**
   * Test of setPassword method, of class user_login.
   */
  @Test
  public void testSetPassword() {
    System.out.println("setPassword");
    String user_password = "test1234";
    user_login instance = new user_login(1, "email");
    instance.setPassword(user_password);
    // TODO review the generated test code and remove the default call to fail.
    assertTrue(instance.getPassword() == "test1234")
  }
  
}
