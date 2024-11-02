package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DepartServTest {
    @InjectMocks
    private DepartementServiceImpl departementService; // Service que nous testons

    @Mock
    private DepartementRepository departementRepository; // Dépendance simulée

    @BeforeEach
    public void setUp() {
        // Aucune initialisation manuelle des mocks requise ici
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Données de test
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "Informatique"));
        departements.add(new Departement(2, "Ressources Humaines"));

        // Comportement simulé
        when(departementRepository.findAll()).thenReturn(departements);

        // Appel de la méthode à tester
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Informatique", result.get(0).getNomDepart());
    }

    @Test
    public void testAddDepartement() {
        // Données de test
        Departement newDepartement = new Departement(3, "Marketing");

        // Comportement simulé
        when(departementRepository.save(any(Departement.class))).thenReturn(newDepartement);

        // Appel de la méthode à tester
        Departement result = departementService.addDepartement(newDepartement);

        // Assertions
        assertNotNull(result);
        assertEquals("Marketing", result.getNomDepart());
        verify(departementRepository, times(1)).save(newDepartement); // Vérifier que save a été appelé
    }

    @Test
    public void testUpdateDepartement() {
        // Données de test
        Departement updatedDepartement = new Departement(1, "Informatique Avancée");

        // Comportement simulé
        when(departementRepository.save(any(Departement.class))).thenReturn(updatedDepartement);

        // Appel de la méthode à tester
        Departement result = departementService.updateDepartement(updatedDepartement);

        // Assertions
        assertNotNull(result);
        assertEquals("Informatique Avancée", result.getNomDepart());
        verify(departementRepository, times(1)).save(updatedDepartement); // Vérifier que save a été appelé
    }

    @Test
    public void testRetrieveDepartement() {
        // Données de test
        Departement departement = new Departement(1, "Informatique");

        // Comportement simulé
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Appel de la méthode à tester
        Departement result = departementService.retrieveDepartement(1);

        // Assertions
        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
    }

    @Test
    public void testDeleteDepartement() {
        // Données de test
        Departement departement = new Departement(1, "Informatique");

        // Comportement simulé
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Appel de la méthode à tester
        departementService.deleteDepartement(1);

        // Vérification que delete a été appelé une fois
        verify(departementRepository, times(1)).delete(departement);
    }

    @Test
    public void testRetrieveDepartementNotFound() {
        // Comportement simulé
        when(departementRepository.findById(1)).thenReturn(Optional.empty());

        // Appel de la méthode à tester
        Departement result = departementService.retrieveDepartement(1);

        // Assertions
        assertNull(result); // Le résultat doit être null car le département n'existe pas
    }
}


