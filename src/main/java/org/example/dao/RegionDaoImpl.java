package org.example.dao;

import java.util.List;

import org.example.interfaces.RegionDao;
import org.example.entity.RegionEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RegionDaoImpl implements RegionDao {

    private final EntityManager em;

    public RegionDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(RegionEntity region) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(region);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            throw new RuntimeException("Failed to create region: " + e.getMessage(), e);
        }
    }

    @Override
    public RegionEntity findById(Long id) {
        return em.find(RegionEntity.class, id);
    }

    @Override
    public List<RegionEntity> findAll() {
        return em.createQuery("FROM RegionEntity", RegionEntity.class).getResultList();
    }

    @Override
    public void update(RegionEntity region) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(region);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            throw new RuntimeException("Failed to update region: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            RegionEntity region = em.find(RegionEntity.class, id);
            if (region != null) {
                tx.begin();
                em.remove(region);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            throw new RuntimeException("Failed to delete region: " + e.getMessage(), e);
        }
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
