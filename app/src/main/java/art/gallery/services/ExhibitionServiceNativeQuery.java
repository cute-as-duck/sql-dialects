package art.gallery.services;

import art.gallery.models.Exhibition;
import art.gallery.models.Work;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class ExhibitionServiceNativeQuery {

    public List<Exhibition> findAll() {
        EntityManager em = beginTransaction();
        Query query = em.createNativeQuery("select * from exhibition");
//        Query query = em.createNativeQuery("select e from exhibition e");
        List<Exhibition> exhibitions = query.getResultList();
        closeTransaction(em);
        return exhibitions;
    }

    public Exhibition addExhibition(Exhibition exhibition) {
        EntityManager em = beginTransaction();
        em.persist(exhibition);
        em.flush();
        closeTransaction(em);
        return exhibition;
    }

    public void deleteById(long id) {
        EntityManager em = beginTransaction();
        Query query = em.createNativeQuery("delete from exhibition where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        closeTransaction(em);
    }

    public void clear() {
        EntityManager em = beginTransaction();
        Query query = em.createNativeQuery("delete from exhibition");
        query.executeUpdate();
        closeTransaction(em);
    }

    public void addWorkToExhibition(Exhibition exhibition, Work work) {
        EntityManager em = beginTransaction();
        Exhibition retrievedExhibition = em.find(Exhibition.class, exhibition.getId());
        Work retrievedWork = em.find(Work.class, work.getId());
        retrievedExhibition.addWork(retrievedWork);
        em.persist(retrievedExhibition);
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
