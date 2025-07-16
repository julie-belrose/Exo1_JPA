package org.example;

import org.example.dao.ObservationDaoImpl;
import org.example.dao.RegionDao;
import org.example.dao.SpecieDaoImpl;
import org.example.dao.TravelLogDaoImpl;
import org.example.enums.Category;
import org.example.enums.Climate;
import org.example.enums.TravelMode;
import org.example.interfaces.ObservationDao;
import org.example.interfaces.SpecieDao;
import org.example.interfaces.TravelLogDao;
import org.example.entity.ObservationEntity;
import org.example.entity.RegionEntity;
import org.example.entity.SpecieEntity;
import org.example.entity.TravelLogEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
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
            System.err.println("An error occurred during execution:");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void main2(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("regionPU");
        EntityManager em = emf.createEntityManager();

        RegionDao regionDao = new RegionDao(em);
        SpecieDao specieDao = new SpecieDaoImpl(em);
        ObservationDao observationDao = new ObservationDaoImpl(em);
        TravelLogDao travelLogDao = new TravelLogDaoImpl(em);

        try {
            SpecieEntity eagle = createSpecie(specieDao);
            ObservationEntity obs = createObservation(observationDao, eagle);
            TravelLogEntity log = createTravelLog(travelLogDao, obs);
            printAllSpecies(specieDao);
            printAllObservations(observationDao);
            printAllTravelLogs(travelLogDao);

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

    // specie

    private static SpecieEntity createSpecie(SpecieDao dao) {
        SpecieEntity specie = new SpecieEntity();
        specie.setCommonName("Golden Eagle");
        specie.setScientificName("Aquila chrysaetos");
        specie.setCategory(Category.BIRD);
        dao.save(specie);
        System.out.println("Created specie: " + specie);
        return specie;
    }

    private static ObservationEntity createObservation(ObservationDao dao, SpecieEntity specie) {
        ObservationEntity obs = new ObservationEntity();
        obs.setSpecie(specie);
        obs.setObserverName("Julie");
        obs.setLocation("Forêt de Fontainebleau");
        obs.setLatitude(48.4);
        obs.setLongitude(2.7);
        obs.setObservationDate(LocalDate.now());
        obs.setComment("Spotted at dawn.");
        dao.save(obs);
        System.out.println("Created observation: " + obs);
        return obs;
    }

    private static TravelLogEntity createTravelLog(TravelLogDao dao, ObservationEntity obs) {
        TravelLogEntity log = new TravelLogEntity();
        log.setObservation(obs);
        log.setDistanceKm(12.5);
        log.setMode(TravelMode.BIKE);
        dao.save(log);
        System.out.println("Created travel log: " + log);
        return log;
    }

    private static void printAllSpecies(SpecieDao dao) {
        System.out.println("All species:");
        dao.findAll().forEach(System.out::println);
    }

    private static void printAllObservations(ObservationDao dao) {
        System.out.println("All observations:");
        dao.findAll().forEach(System.out::println);
    }

    private static void printAllTravelLogs(TravelLogDao dao) {
        System.out.println("All travel logs:");
        dao.findAll().forEach(System.out::println);
    }
}
