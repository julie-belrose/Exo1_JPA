package org.example.dao;

import org.example.entity.TravelLogEntity;
import org.example.enums.TravelMode;
import org.example.interfaces.TravelLogDao;

import javax.persistence.EntityManager;
import java.util.List;

public class TravelLogDaoImpl implements TravelLogDao {

    private final EntityManager em;

    public TravelLogDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public TravelLogEntity findById(Long id) {
        return em.find(TravelLogEntity.class, id);
    }

    @Override
    public List<TravelLogEntity> findAll() {
        return em.createQuery("SELECT t FROM TravelLogEntity t", TravelLogEntity.class).getResultList();
    }

    @Override
    public TravelLogEntity save(TravelLogEntity log) {
        em.getTransaction().begin();
        em.persist(log);
        em.getTransaction().commit();
        return log;
    }

    @Override
    public TravelLogEntity update(TravelLogEntity log) {
        em.getTransaction().begin();
        TravelLogEntity merged = em.merge(log);
        em.getTransaction().commit();
        return merged;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        TravelLogEntity log = em.find(TravelLogEntity.class, id);
        if (log != null)
            em.remove(log);
        em.getTransaction().commit();
    }

    @Override
    public List<TravelLogEntity> findByMode(TravelMode mode) {
        return em.createQuery("SELECT t FROM TravelLogEntity t WHERE t.mode = :mode", TravelLogEntity.class)
                .setParameter("mode", mode)
                .getResultList();
    }
}

