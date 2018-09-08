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

    public SQL select(String... fields) {
        query = "select " + join(fields, ", ");
        return new SQL(query);
    }


    public SQL selectAll() {
        query = "select *";
        return new SQL(query);
    }

    public SQL from(String... tables) {
        query = query + " \nfrom " + join(tables, ", ");
        return new SQL(query);
    }

    public SQL where(String condition) {
        query = query + " \nwhere " + condition;
        return new SQL(query);
    }

    @Override
    public String toString() {
        return query;
    }
}
