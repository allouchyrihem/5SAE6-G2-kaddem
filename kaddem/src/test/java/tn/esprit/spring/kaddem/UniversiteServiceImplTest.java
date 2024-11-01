package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    private Universite universite;
    private Departement departement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        universite = new Universite();
        universite.setIdUniv(1);
        universite.setNomUniv("Université de Tunis");

        departement = new Departement();
        departement.setIdDepart(1);
    }

    @Test
    public void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        universites.add(universite);

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();
        assertEquals(1, result.size());
        assertEquals("Université de Tunis", result.get(0).getNomUniv());
    }

    @Test
    public void testAddUniversite() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniv());
    }

    @Test
    public void testUpdateUniversite() {
        universite.setNomUniv("Université de Carthage");
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);
        assertNotNull(result);
        assertEquals("Université de Carthage", result.getNomUniv());
    }

    @Test
    public void testRetrieveUniversite() {
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1);
        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniv());
    }

    @Test
    public void testRetrieveUniversiteNotFound() {
        when(universiteRepository.findById(1)).thenReturn(Optional.empty());

        Universite result = universiteService.retrieveUniversite(1);
        assertNull(result);
    }

    @Test
    public void testDeleteUniversite() {
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        doNothing().when(universiteRepository).delete(any(Universite.class));

        universiteService.deleteUniversite(1);
        verify(universiteRepository, times(1)).delete(universite);
    }

    @Test
    public void testAssignUniversiteToDepartement() {
        Set<Departement> departements = new HashSet<>();
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 1);

        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testAssignUniversiteToDepartementUniversiteNotFound() {
        when(universiteRepository.findById(1)).thenReturn(Optional.empty());
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 1);

        verify(universiteRepository, never()).save(any(Universite.class));
    }

    @Test
    public void testRetrieveDepartementsByUniversite() {
        Set<Departement> departements = new HashSet<>(Collections.singletonList(departement));
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);
        assertEquals(1, result.size());
        assertTrue(result.contains(departement));
    }
}
