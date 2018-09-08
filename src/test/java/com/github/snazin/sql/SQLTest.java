package com.github.snazin.sql;

import org.intellij.lang.annotations.Language;
import org.junit.Test;

import static com.github.snazin.sql.SQL.create;
import static org.assertj.core.api.Assertions.*;

public class SQLTest {

    private SQL query;

    @Language("SQL")
    private String expected;


    private void assertThatQueryIsExpected() {
        assertThat(query.toString()).isEqualTo(expected);
    }


    @Test
    public void select() {
        query = create().select("name");
        expected = "select name";
        assertThatQueryIsExpected();
    }

    @Test
    public void selectMultiple() {
        query = create().select("name", "address", "phone");
        expected = "select name, address, phone";
        assertThatQueryIsExpected();
    }

    @Test
    public void selectFrom() {
        query = create()
                .select("*")
                .from("user");
        expected = "" +
                "select * \n" +
                "from user";
        assertThatQueryIsExpected();
    }

    @Test
    public void selectFromMultiple() {
        query = create()
                .select("*")
                .from("user u", "address a");
        expected = "select * \n" +
                "from user u, address a";
        assertThatQueryIsExpected();
    }
}
