package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ContratServiceTestUnitaire {

    @Autowired
    private ContratServiceImpl contratService;

    @Autowired
    private ContratRepository contratRepository;

    private Contrat contrat;

    @BeforeEach
    public void setUp() {
        // Nettoyer la base de données avant chaque test
        contratRepository.deleteAll();

        contrat = new Contrat();
        contrat.setIdContrat(1);
        // Définir tous les autres champs nécessaires dans l'objet Contrat
    }

    @Test
    public void testAddContrat() {
        Contrat result = contratService.addContrat(contrat);
        assertEquals(contrat, result);
        assertTrue(contratRepository.findById(1).isPresent());
    }

    @Test
    public void testRetrieveAllContrats() {
        contratService.addContrat(contrat);

        List<Contrat> result = contratService.retrieveAllContrats();

        assertEquals(1, result.size());
        assertEquals(contrat, result.get(0));
    }



    @Test
    public void testRetrieveContrat() {
        contratService.addContrat(contrat);

        Contrat result = contratService.retrieveContrat(1);

        assertEquals(contrat, result);
    }

    @Test
    public void testRemoveContrat() {
        contratService.addContrat(contrat);

        contratService.removeContrat(1);

        assertFalse(contratRepository.findById(1).isPresent());
    }

    @Test
    public void testRetrieveContrat_NotFound() {
        Contrat result = contratService.retrieveContrat(1);
        assertNull(result);
    }

}