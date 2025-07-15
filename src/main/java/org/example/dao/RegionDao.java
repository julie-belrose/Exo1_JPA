package org.example.dao;

import org.example.entity.RegionEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class RegionDao {

    private final EntityManager em;

    public RegionDao(EntityManager em) {
        this.em = em;
    }

    public void create(RegionEntity region) {
        em.getTransaction().begin();
        em.persist(region);
        em.getTransaction().commit();
    }

    public RegionEntity findById(Long id) {
        return em.find(RegionEntity.class, id);
    }

    public List<RegionEntity> findAll() {
        return em.createQuery("FROM RegionEntity", RegionEntity.class).getResultList();
    }

    public void update(RegionEntity region) {
        em.getTransaction().begin();
        em.merge(region);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        RegionEntity region = em.find(RegionEntity.class, id);
        if (region != null) {
            em.getTransaction().begin();
            em.remove(region);
            em.getTransaction().commit();
        }
    }
}
