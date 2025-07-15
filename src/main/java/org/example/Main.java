package org.example;

import org.example.dao.RegionDao;
import org.example.enums.Climate;
import org.example.entity.RegionEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("regionPU");
        EntityManager em = emf.createEntityManager();
        RegionDao regionDao = new RegionDao(em);

        try {
            RegionEntity region = createRegion(regionDao);
            printAllRegions(regionDao);
            updateRegion(regionDao, region);
            deleteRegion(regionDao, region.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static RegionEntity createRegion(RegionDao dao) {
        RegionEntity region = new RegionEntity();
        region.setName("Sahara");
        region.setSurface(9100000);
        region.setClimate(Climate.ARID);
        dao.create(region);
        System.out.println("Created region: " + region);
        return region;
    }

    private static void printAllRegions(RegionDao dao) {
        List<RegionEntity> regions = dao.findAll();
        System.out.println("All regions:");
        regions.forEach(System.out::println);
    }

    private static void updateRegion(RegionDao dao, RegionEntity region) {
        region.setName("Great Sahara");
        dao.update(region);
        RegionEntity updated = dao.findById(region.getId());
        System.out.println("Updated region: " + updated);
    }

    private static void deleteRegion(RegionDao dao, Long id) {
        dao.delete(id);
        System.out.println("Deleted region with ID: " + id);
    }
}
