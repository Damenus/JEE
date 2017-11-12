package pl.darczuk.studia.java.enterprise;

import lombok.extern.java.Log;
import pl.darczuk.studia.java.enterprise.entities.Bow;
import pl.darczuk.studia.java.enterprise.entities.Elf;
import pl.darczuk.studia.java.enterprise.entities.Forest;
import pl.darczuk.studia.java.enterprise.entities.User;
import pl.darczuk.studia.java.enterprise.users.CryptUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Book;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.util.Arrays.asList;

@Singleton
@Startup
@Log
public class InitialFixture {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        List<User> users = asList(
                new User("admin", CryptUtils.sha256("admin"), asList(User.Roles.ADMIN, User.Roles.USER)),
                new User("user1", CryptUtils.sha256("pass1"), asList(User.Roles.USER)),
                new User("user2", CryptUtils.sha256("pass2"), asList(User.Roles.USER))
        );

        users.forEach(user -> em.persist(user));
        em.flush();

        List<Forest> forests = asList(
                new Forest(12,users.get(1)),
                new Forest(144,users.get(2))
        );

        forests.forEach(forest -> em.persist(forest));
        em.flush();

        List<Elf> elfs = asList(
                new Elf("Franke", 1200, Bow.EPIC, forests.get(0)),
                new Elf("Teosle", 100, Bow.COMMON, forests.get(0)),
                new Elf("Elfik", 144, Bow.RARE, forests.get(1)),
                new Elf("Michalke", 1, Bow.RARE, forests.get(1))
        );

        elfs.forEach(book -> em.persist(book));

    }
}
