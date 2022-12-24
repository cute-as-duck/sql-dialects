package art.gallery.services;

import art.gallery.models.Work;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;

public class WorkServiceCriteria {

    public List<Work> findAll() {
        EntityManager em = beginTransaction();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Work> query = cb.createQuery(Work.class);
        Root<Work> w = query.from(Work.class);
        query.select(w);
        TypedQuery<Work> typedQuery = em.createQuery(query);
        List<Work> works = typedQuery.getResultList();
        closeTransaction(em);
        return works;
    }

    public Work getWorkById(long id) {
        EntityManager em = beginTransaction();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Work> query = cb.createQuery(Work.class);
        Root<Work> w = query.from(Work.class);
        query.select(w).where(cb.equal(w.get("id"), id));
        TypedQuery<Work> typedQuery = em.createQuery(query);
        Work work = typedQuery.getSingleResult();
        closeTransaction(em);
        return work;
    }

    public void deleteById(long id) {
        EntityManager em = beginTransaction();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Work> query = cb.createCriteriaDelete(Work.class);
        Root<Work> w = query.from(Work.class);
        query.where(cb.equal(w.get("id"), id));
        em.createQuery(query).executeUpdate();
        closeTransaction(em);
    }

    public void clear() {
        EntityManager em = beginTransaction();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Work> query = cb.createCriteriaDelete(Work.class);
        query.from(Work.class);
        em.createQuery(query).executeUpdate();
        closeTransaction(em);
    }

    public Work addWork(Work work) {
        EntityManager em = beginTransaction();
        em.persist(work);
        em.flush();
        closeTransaction(em);
        return work;
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
