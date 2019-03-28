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

import java.sql.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author sandeepsuri
 */
public class SQLiteDatabaseManager {
    
    private Connection connection;
    
    private SQLiteDatabaseManager(){}
    
    private static SQLiteDatabaseManager singletonInstance;
    public synchronized static Connection getConnection() throws IOException, ClassNotFoundException, Exception {
        if(singletonInstance == null || singletonInstance.connection == null || singletonInstance.connection.isClosed()){
            singletonInstance = new SQLiteDatabaseManager();
            Properties settings = new Properties();
            settings.load(SQLiteDatabaseManager.class.getResourceAsStream("config.properties"));
            Class.forName(settings.getProperty("JdbcDriver"));
            Properties info = new Properties();
//            info.setProperty("user", settings.getProperty("DatabaseUser"));
//            info.setProperty("password", settings.getProperty("DatabasePassword"));
//            info.setProperty("allowMultiQueries", settings.getProperty("AllowMultiQueries"));
            
            singletonInstance.connection = DriverManager.getConnection(settings.getProperty("DatabaseUrl") + settings.getProperty("DatabaseName"), info);
            if(singletonInstance.connection == null)
                throw new Exception("Conncetion to the database failed!", null);
            }
        
            //This is very critical for handling transaction commit by program!
            singletonInstance.connection.setAutoCommit(false);
        return singletonInstance.connection;
        
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        singletonInstance.connection.close();
    }
    
}