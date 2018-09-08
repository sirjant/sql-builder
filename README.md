# SQL Builder
Simple Java SQL builder

### Examples
```java
String query = SQL.create()
    .select("u.name", "a.address", "u.phone")
    .from("user u", "address a")
    .where("u.id").is(1)
    .and("u.address_id = a.id")
    .toString();
```
will produce
```sql
select u.name, a.address, u.phone
from user u, address a
where u.id = 1
and u.address_id = a.id
```

To improve logs readability every line ends with `\n` symbol.

You can get full list of features by [tests file](https://github.com/snazin/sql-builder/blob/master/src/test/java/com/github/snazin/sql/SQLTest.java).
