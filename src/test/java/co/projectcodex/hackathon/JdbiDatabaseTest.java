package co.projectcodex.hackathon;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbiDatabaseTest {
    Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost/spark_hbs_jdbi?username=coder&password=codex123");
    @BeforeEach
    void beforeEach() {
        jdbi.withHandle(h -> {
            h.execute("delete from users");
            return null;
        });
    }

    @Test
    public void shouldBeAbleToConnectToPostgreSQL() {

        int count = jdbi.withHandle(h -> h.createQuery("select count(*) from users")
                .mapTo(int.class)
                .findOnly());

        assertTrue(count >= 0);

    }

    @Test
    public void shouldBeAbleToCleanTable() {

        int count = jdbi.withHandle(h -> {
            return h.createQuery("select count(*) from users")
                    .mapTo(int.class)
                    .findOnly();
        });
        assertEquals(0, count);
    }

    @Test
    public void shouldBeAbleToInsertPerson() {

        int counter = jdbi.withHandle(h -> {
            h.execute("insert into users (first_name, last_name, email) values (?, ?, ?)",
                    "Linda",
                    "Dlamini",
                    "linda@email.com");

            int count = h
                    .createQuery("select count(*) from users where first_name = ?")
                    .bind(0, "Linda")
                    .mapTo(int.class)
                    .findOnly();

            return count;
        });

        assertEquals(1, counter);
    }

    @Test
    public void shouldBeAbleToFindAll() {

        final String INSERT_USER = "insert into users (first_name, last_name, email) values (?, ?, ?)";

        List<Person> people = jdbi.withHandle(h -> {
            h.execute(INSERT_USER, "Name two", "LastName one", "Email one");
            h.execute(INSERT_USER, "Name three", "LastName three", "Email one");
            h.execute(INSERT_USER, "Name four", "LastName four", "Email one");

            List<Person> listPerson = h.createQuery("select first_name, last_name, email from users")
                    .mapToBean(Person.class)
                    .list();
            return listPerson;
        });

        assertEquals(3, people.size());
        assertEquals("Name three", people.get(1).getFirstName());
        assertEquals("LastName three", people.get(1).getLastName());
    }


    }
