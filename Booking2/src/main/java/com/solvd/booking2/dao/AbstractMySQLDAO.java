package com.solvd.booking2.dao;

import com.solvd.booking2.ConnectionPool;

import java.sql.Connection;

public abstract class AbstractMySQLDAO {

    protected Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }
    protected void releaseConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}


