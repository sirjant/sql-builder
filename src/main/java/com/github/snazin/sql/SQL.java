package com.github.snazin.sql;


import org.intellij.lang.annotations.Language;

import static org.apache.commons.lang3.StringUtils.join;


public class SQL {

    @Language("SQL")
    private String query = "";


    public SQL() {
    }

    public SQL(@Language("SQL") String query) {
        this.query = query;
    }


    public static SQL create() {
        return new SQL();
    }


    private SQL append(@Language("SQL") String statement) {
        query = query + statement;
        return this;
    }

    private SQL appendLine(@Language("SQL") String statement) {
        return append(" \n" + statement);
    }

    public SQL select(String... fields) {
        query = "select " + join(fields, ", ");
        return this;
    }

    public SQL selectAll() {
        query = "select *";
        return this;
    }

    public SQL from(String... tables) {
        return appendLine("from " + join(tables, ", "));
    }

    public SQL where(String... conditions) {
        return appendLine("where " + join(conditions, " \nand "));
    }

    @Override
    public String toString() {
        return query;
    }
}
