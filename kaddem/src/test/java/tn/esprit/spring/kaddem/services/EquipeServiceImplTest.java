package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EquipeServiceImplTest {

	@InjectMocks
	private EquipeServiceImpl equipeService;

	@Mock
	private EquipeRepository equipeRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		System.out.println("Setting up the tests...");
	}

	@Test
	public void testRetrieveAllEquipes() {
		List<Equipe> equipes = new ArrayList<>();
		equipes.add(new Equipe());
		when(equipeRepository.findAll()).thenReturn(equipes);

		System.out.println("Executing testRetrieveAllEquipes...");
		List<Equipe> result = equipeService.retrieveAllEquipes();

		System.out.println("Retrieved " + result.size() + " equipes.");
		assertEquals(1, result.size());
		verify(equipeRepository, times(1)).findAll();
	}

	@Test
	public void testAddEquipe() {
		Equipe equipe = new Equipe();
		when(equipeRepository.save(equipe)).thenReturn(equipe);

		System.out.println("Executing testAddEquipe...");
		Equipe result = equipeService.addEquipe(equipe);

		System.out.println("Equipe added: " + result);
		assertNotNull(result);
		verify(equipeRepository, times(1)).save(equipe);
	}

	@Test
	public void testDeleteEquipe() {
		Integer equipeId = 1;
		Equipe equipe = new Equipe();
		when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

		System.out.println("Executing testDeleteEquipe...");
		equipeService.deleteEquipe(equipeId);

		System.out.println("Equipe with ID " + equipeId + " deleted.");
		verify(equipeRepository, times(1)).delete(equipe);
	}

	@Test
	public void testRetrieveEquipe() {
		Integer equipeId = 1;
		Equipe equipe = new Equipe();
		when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

		System.out.println("Executing testRetrieveEquipe...");
		Equipe result = equipeService.retrieveEquipe(equipeId);

		System.out.println("Equipe retrieved: " + result);
		assertNotNull(result);
		verify(equipeRepository, times(1)).findById(equipeId);
	}

	@Test
	public void testUpdateEquipe() {
		Equipe equipe = new Equipe();
		when(equipeRepository.save(equipe)).thenReturn(equipe);

		System.out.println("Executing testUpdateEquipe...");
		Equipe result = equipeService.updateEquipe(equipe);

		System.out.println("Equipe updated: " + result);
		assertNotNull(result);
		verify(equipeRepository, times(1)).save(equipe);
	}
}
