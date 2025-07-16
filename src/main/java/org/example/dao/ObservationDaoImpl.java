package org.example.dao;

import org.example.entity.ObservationEntity;
import org.example.interfaces.ObservationDao;

import javax.persistence.EntityManager;
import java.util.List;

public class ObservationDaoImpl implements ObservationDao {

    private final EntityManager em;

    public ObservationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public ObservationEntity findById(Long id) {
        return em.find(ObservationEntity.class, id);
    }

    @Override
    public List<ObservationEntity> findAll() {
        return em.createQuery("SELECT o FROM ObservationEntity o", ObservationEntity.class).getResultList();
    }

    @Override
    public ObservationEntity save(ObservationEntity obs) {
        em.getTransaction().begin();
        em.persist(obs);
        em.getTransaction().commit();
        return obs;
    }

    @Override
    public ObservationEntity update(ObservationEntity obs) {
        em.getTransaction().begin();
        ObservationEntity merged = em.merge(obs);
        em.getTransaction().commit();
        return merged;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        ObservationEntity obs = em.find(ObservationEntity.class, id);
        if (obs != null)
            em.remove(obs);
        em.getTransaction().commit();
    }

    @Override
    public List<ObservationEntity> findBySpecieId(Long specieId) {
        return em.createQuery("SELECT o FROM ObservationEntity o WHERE o.specie.id = :id", ObservationEntity.class)
                .setParameter("id", specieId)
                .getResultList();
    }

    @Override
    public List<ObservationEntity> findByLocation(String location) {
        return em.createQuery("SELECT o FROM ObservationEntity o WHERE o.location = :loc", ObservationEntity.class)
                .setParameter("loc", location)
                .getResultList();
    }
}

