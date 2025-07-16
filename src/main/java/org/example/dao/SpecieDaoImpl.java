package org.example.dao;

import org.example.entity.SpecieEntity;
import org.example.interfaces.SpecieDao;
import org.example.enums.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class SpecieDaoImpl implements SpecieDao {

    private final EntityManager em;

    public SpecieDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public SpecieEntity findById(Long id) {
        return em.find(SpecieEntity.class, id);
    }

    @Override
    public List<SpecieEntity> findAll() {
        return em.createQuery("SELECT s FROM SpecieEntity s", SpecieEntity.class).getResultList();
    }

    @Override
    public SpecieEntity save(SpecieEntity specie) {
        em.getTransaction().begin();
        em.persist(specie);
        em.getTransaction().commit();
        return specie;
    }

    @Override
    public SpecieEntity update(SpecieEntity specie) {
        em.getTransaction().begin();
        SpecieEntity merged = em.merge(specie);
        em.getTransaction().commit();
        return merged;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        SpecieEntity specie = em.find(SpecieEntity.class, id);
        if (specie != null)
            em.remove(specie);
        em.getTransaction().commit();
    }

    @Override
    public List<SpecieEntity> findByCategory(Category category) {
        return em.createQuery("SELECT s FROM SpecieEntity s WHERE s.category = :category", SpecieEntity.class)
                .setParameter("category", category)
                .getResultList();
    }

}
