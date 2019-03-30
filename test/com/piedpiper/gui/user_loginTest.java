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

  /**
   * Test of getId method, of class user_login.
   */
  @Test
  public void testGetId() {
    System.out.println("getId");
    user_login instance = new user_login();
    Integer expResult = null;
    Integer result = instance.getId();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case failed.");
  }

  /**
   * Test of setId method, of class user_login.
   */
  @Test
  public void testSetId() {
    System.out.println("setId");
    Integer id = null;
    user_login instance = new user_login();
    instance.setId(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case failed.");
  }

  /**
   * Test of getEmail method, of class user_login.
   */
  @Test
  public void testGetEmail() {
    System.out.println("getEmail");
    user_login instance = new user_login();
    String expResult = "";
    String result = instance.getEmail();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case failed.");
  }

  /**
   * Test of setEmail method, of class user_login.
   */
  @Test
  public void testSetEmail() {
    System.out.println("setEmail");
    String user_email = "";
    user_login instance = new user_login();
    instance.setEmail(user_email);
    // TODO review the generated test code and remove the default call to fail.
    fail("Falied");
  }

  /**
   * Test of getPassword method, of class user_login.
   */
  @Test
  public void testGetPassword() {
    System.out.println("getPassword");
    user_login instance = new user_login();
    String expResult = "";
    String result = instance.getPassword();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("Falied");
  }

  /**
   * Test of setPassword method, of class user_login.
   */
  @Test
  public void testSetPassword() {
    System.out.println("setPassword");
    String user_password = "";
    user_login instance = new user_login();
    instance.setPassword(user_password);
    // TODO review the generated test code and remove the default call to fail.
    fail("Falied");
  }
  
}
