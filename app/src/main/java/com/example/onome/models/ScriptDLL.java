package com.example.onome.models;

public class ScriptDLL {

    public static String createTableName(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE IF NOT EXISTS tbNome( ");
        sql.append(" NAME TEXT PRIMARY KEY NOT NULL, ");
        sql.append(" PERIO0 INTEGER NOT NULL, ");
        sql.append(" PERIO1 INTEGER NOT NULL, ");
        sql.append(" PERIO2 INTEGER NOT NULL, ");
        sql.append(" PERIO3 INTEGER NOT NULL, ");
        sql.append(" PERIO4 INTEGER NOT NULL, ");
        sql.append(" PERIO5 INTEGER NOT NULL, ");
        sql.append(" PERIO6 INTEGER NOT NULL, ");
        sql.append(" PERIO7 INTEGER NOT NULL  ); ");
        return sql.toString();
    }
}
