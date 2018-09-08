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
    public void selectAll() {
        query = create().selectAll();
        expected = "select *";
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

    @Test
    public void selectWhere() {
        query = create()
                .selectAll()
                .from("user")
                .where("user.id = 1");
        expected = "" +
                "select * \n" +
                "from user \n" +
                "where user.id = 1";
        assertThatQueryIsExpected();
    }

    @Test
    public void selectWhereMultiple() {
        query = create()
                .selectAll()
                .from("user")
                .where("user.id = 1", "user.name = 'John'");
        expected = "" +
                "select * \n" +
                "from user \n" +
                "where user.id = 1 \n" +
                "and user.name = 'John'";
        assertThatQueryIsExpected();
    }

    @Test
    public void selectWhereAnd() {
        query = create()
                .selectAll()
                .from("user")
                .where("user.id = 1").and("user.name = 'John'");
        expected = "" +
                "select * \n" +
                "from user \n" +
                "where user.id = 1 \n" +
                "and user.name = 'John'";
        assertThatQueryIsExpected();
    }
}
