package com.nys.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryUtil extends QueryRunner{

    private Connection connection;

    @Override
    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh) throws SQLException {
        connection = conn;
        T query = super.query(connection, sql, rsh);
        connection.close();
        return query;
    }

    @Override
    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        connection = conn;
        T query = super.query(connection, sql, rsh,params);
        connection.close();
        return query;
    }

    @Override
    public int update(Connection conn, String sql, Object... params) throws SQLException {
        connection = conn;
        int update = super.update(connection, sql, params);
        connection.close();
        return update;
    }

    @Override
    public int update(Connection conn, String sql, Object param) throws SQLException {
        connection = conn;
        int update = super.update(connection, sql, param);
        connection.close();
        return update;
    }

    @Override
    public int update(Connection conn, String sql) throws SQLException {
        connection = conn;
        int update = super.update(connection, sql);
        connection.close();
        return update;
    }
}
