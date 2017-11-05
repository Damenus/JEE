package pl.darczuk.studia.java.enterprise;

import lombok.extern.java.Log;
import pl.darczuk.studia.java.enterprise.entities.Bow;
import pl.darczuk.studia.java.enterprise.entities.Elf;
import pl.darczuk.studia.java.enterprise.entities.Forest;

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

        Forest forest = new Forest(12);

        em.persist(forest);
        em.flush();



        List<Elf> elfs = asList(
                new Elf("Da", 12, Bow.EPIC, forest),
                new Elf("DAA", 12, Bow.EPIC, forest)
        );

        elfs.forEach(book -> em.persist(book));




    }
}
