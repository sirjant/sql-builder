package com.github.snazin.sql;

import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.snazin.sql.SQL.create;
import static org.assertj.core.api.Assertions.*;

@DisplayName("SQL")
class SQLTest {

    private SQL query;

    @Language("SQL")
    private String expected;


    private void assertThatQueryIsExpected() {
        assertThat(query.toString()).isEqualTo(expected);
    }


    @Nested
    @DisplayName("select")
    class Select {
        @Test
        @DisplayName("one field")
        void select() {
            query = create().select("name");
            expected = "select name";
            assertThatQueryIsExpected();
        }

        @Test
        @DisplayName("all")
        void selectAll() {
            query = create().selectAll();
            expected = "select *";
            assertThatQueryIsExpected();
        }

        @Test
        @DisplayName("multiple fields")
        void selectMultiple() {
            query = create().select("name", "address", "phone");
            expected = "select name, address, phone";
            assertThatQueryIsExpected();
        }
    }

    @Nested
    @DisplayName("select * from")
    class SelectFrom {

        @Test
        @DisplayName("one table")
        void selectFrom() {
            query = create()
                    .select("*")
                    .from("user");
            expected = "" +
                    "select * \n" +
                    "from user";
            assertThatQueryIsExpected();
        }

        @Test
        @DisplayName("multiple tables")
        void selectFromMultiple() {
            query = create()
                    .select("*")
                    .from("user u", "address a");
            expected = "select * \n" +
                    "from user u, address a";
            assertThatQueryIsExpected();
        }
    }

    @Nested
    @DisplayName("select * from table where")
    class SelectWhere {

        @Test
        @DisplayName("one condition")
        void selectWhere() {
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
        @DisplayName("multiple conditions")
        void selectWhereMultiple() {
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
        @DisplayName("condition1 and condition2")
        void selectWhereAnd() {
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

        @Test
        @DisplayName("field = value")
        void selectWhereIs() {
            query = create()
                    .selectAll()
                    .from("user")
                    .where("user.id").is(1);
            expected = "" +
                    "select * \n" +
                    "from user \n" +
                    "where user.id = 1";
            assertThatQueryIsExpected();
        }

        @Test
        @DisplayName("field = string value")
        void selectWhereIsString() {
            query = create()
                    .selectAll()
                    .from("user")
                    .where("user.name").is("John");
            expected = "" +
                    "select * \n" +
                    "from user \n" +
                    "where user.name = 'John'";
            assertThatQueryIsExpected();
        }
    }
}
