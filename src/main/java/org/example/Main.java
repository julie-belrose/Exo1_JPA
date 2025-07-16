package org.example;

import org.example.dao.ObservationDaoImpl;
import org.example.dao.SpecieDaoImpl;
import org.example.dao.TravelLogDaoImpl;
import org.example.enums.Category;
import org.example.enums.Climate;
import org.example.enums.TravelMode;
import org.example.service.ObservationService;
import org.example.service.RegionService;
import org.example.service.SpecieService;
import org.example.service.TravelLogService;
import org.example.entity.ObservationEntity;
import org.example.entity.RegionEntity;
import org.example.entity.SpecieEntity;
import org.example.entity.TravelLogEntity;
import org.example.dao.RegionDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManager em;
    private static RegionService regionService;
    private static SpecieService specieService;
    private static ObservationService observationService;
    private static TravelLogService travelLogService;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("regionPU");
        em = emf.createEntityManager();

        try {
            initServices();
            runExample();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void initServices() {
        regionService = new RegionService(new RegionDaoImpl(em));
        specieService = new SpecieService(new SpecieDaoImpl(em));
        observationService = new ObservationService(new ObservationDaoImpl(em));
        travelLogService = new TravelLogService(new TravelLogDaoImpl(em));
    }

    private static void runExample() {
        try {
            createAndPrintRegion();
            SpecieEntity specie = createAndPrintSpecie();
            ObservationEntity observation = createAndPrintObservation(specie);
            createAndPrintTravelLog(observation);
        } catch (Exception e) {
            System.err.println("Error during example execution: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createAndPrintRegion() {
        try {
            RegionEntity region = regionService.createRegion("Sahara", 9100000, Climate.ARID);
            System.out.println("Region: " + region);
        } catch (Exception e) {
            System.err.println("Failed to create region: " + e.getMessage());
        }
    }

    private static SpecieEntity createAndPrintSpecie() {
        try {
            SpecieEntity specie = specieService.createSpecie("Golden Eagle", "Aquila chrysaetos", Category.BIRD);
            System.out.println("Specie: " + specie);
            return specie;
        } catch (Exception e) {
            System.err.println("Failed to create specie: " + e.getMessage());
            return null;
        }
    }

    private static ObservationEntity createAndPrintObservation(SpecieEntity specie) {
        try {
            if (specie == null)
                throw new IllegalArgumentException("Specie is null.");
            ObservationEntity obs = observationService.createObservation(
                    specie, "Julie", "Forêt de Fontainebleau", 48.4, 2.7, "Spotted at dawn");
            System.out.println("Observation: " + obs);
            return obs;
        } catch (Exception e) {
            System.err.println("Failed to create observation: " + e.getMessage());
            return null;
        }
    }

    private static void createAndPrintTravelLog(ObservationEntity obs) {
        try {
            if (obs == null)
                throw new IllegalArgumentException("Observation is null.");
            TravelLogEntity log = travelLogService.createTravelLog(obs, 12.5, TravelMode.BIKE);
            System.out.println("Travel log: " + log);
        } catch (Exception e) {
            System.err.println("Failed to create travel log: " + e.getMessage());
        }
    }
}
