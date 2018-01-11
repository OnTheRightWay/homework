package com.nys.bookstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class QueryUtil{
    private static DataSource dataSource = null;
    static {
        dataSource = new ComboPooledDataSource();
    }
    public static QueryRunner getQuery(){
        QueryRunner query = new QueryRunner(dataSource);
        return query;
    }
}
