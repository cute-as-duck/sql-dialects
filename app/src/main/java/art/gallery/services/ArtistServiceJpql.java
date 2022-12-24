package art.gallery.services;

import art.gallery.models.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class ArtistServiceJpql {

    public List<Artist> findAll() {
        EntityManager em = beginTransaction();
        Query query = em.createQuery("SELECT a FROM Artist a");
        List<Artist> artists = query.getResultList();
        closeTransaction(em);
        return artists;
    }

    public Artist findById(long id) {
        EntityManager em = beginTransaction();
        Query query = em.createQuery("SELECT a FROM Artist a WHERE a.id=:id").setParameter("id", id);
        Artist artist = (Artist) query.getSingleResult();
        closeTransaction(em);
        return artist;
    }

    public Artist addArtist(Artist artist) {
        EntityManager em = beginTransaction();
        em.persist(artist);
        em.flush();
        closeTransaction(em);
        return artist;
    }

    public void deleteById(long id) {
        EntityManager em = beginTransaction();
        Query query = em.createQuery("DELETE FROM Artist a WHERE a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        closeTransaction(em);
    }

    public void update(Artist artist) {

    }

    public void clear() {
        EntityManager em = beginTransaction();
        Query query = em.createQuery("DELETE FROM Artist a");
        query.executeUpdate();
        closeTransaction(em);
    }

    private EntityManager beginTransaction() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArtGallery");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    private void closeTransaction(EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getEntityManagerFactory().close();
        em.close();
    }
}
