/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package art.gallery;

import art.gallery.models.Artist;
import art.gallery.models.Work;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {

    private EntityManager em;

    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @BeforeEach
    void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArtGallery");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @AfterEach
    void close() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getEntityManagerFactory().close();
        em.close();
    }

    @Test
    void createTables() {
        String exhibitionTable = "CREATE TABLE IF NOT EXISTS exhibition (\n" +
                "    id BIGSERIAL PRIMARY KEY NOT NULL,\n" +
                "    title VARCHAR NOT NULL);";
        Query query = em.createNativeQuery(exhibitionTable);
        query.executeUpdate();

        String exhibitionWorkTable = "CREATE TABLE IF NOT EXISTS exhibition_work (\n" +
                "    exhibition_id BIGINT,\n" +
                "    work_id BIGINT);";
        Query query1 = em.createNativeQuery(exhibitionWorkTable);
        query1.executeUpdate();
    }

    @Test
    void dropTables() {
        String dropExhibitionTable = "drop table exhibition;";
        Query query = em.createNativeQuery(dropExhibitionTable);
        query.executeUpdate();

        String dropExhibitionWorkTable = "drop table exhibition_work;";
        Query query1 = em.createNativeQuery(dropExhibitionWorkTable);
        query1.executeUpdate();

        String dropTable = "drop table work;";
        Query query2 = em.createNativeQuery(dropTable);
        query2.executeUpdate();

        String drop = "drop table artist;";
        Query query3 = em.createNativeQuery(drop);
        query3.executeUpdate();
    }
}
