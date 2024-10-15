package tn.esprit.spring.kaddem.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contrat = new Contrat();
        contrat.setIdContrat(1);
        // Set any other necessary fields in the Contrat object
    }

    @Test
    public void testAddContrat() {
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.addContrat(contrat);

        assertEquals(contrat, result);
        verify(contratRepository).save(contrat);
    }

    @Test
    public void testRetrieveAllContrats() {
        List<Contrat> contrats = new ArrayList<>();
        contrats.add(contrat);
        when(contratRepository.findAll()).thenReturn(contrats);

        List<Contrat> result = contratService.retrieveAllContrats();

        assertEquals(1, result.size());
        assertEquals(contrat, result.get(0));
        verify(contratRepository).findAll();
    }

    @Test
    public void testUpdateContrat() {
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.updateContrat(contrat);

        assertEquals(contrat, result);
        verify(contratRepository).save(contrat);
    }

    @Test
    public void testRetrieveContrat() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(1);

        assertEquals(contrat, result);
        verify(contratRepository).findById(1);
    }

    @Test
    public void testRemoveContrat() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(1);

        verify(contratRepository).delete(contrat);
    }

    @Test
    public void testRetrieveContrat_NotFound() {
        when(contratRepository.findById(1)).thenReturn(Optional.empty());

        Contrat result = contratService.retrieveContrat(1);

        assertNull(result);
        verify(contratRepository).findById(1);
    }
}