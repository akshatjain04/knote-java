package com.learnk8s.knote.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.multipart.MultipartFile;
import com.learnk8s.knote.Note.Note;
import com.learnk8s.knote.Repository.NotesRepository;
import com.learnk8s.knote.UploadConfig.KnoteProperties;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.io.File;
import java.util.Collections;
import java.util.UUID;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@RunWith(MockitoJUnitRunner.class)
public class KnoteControllerIndexTest {

	@Mock
	private NotesRepository notesRepository;

	@Mock
	private Model model;

	@InjectMocks
	private KnoteController knoteController;

	@Before
	public void setUp() {
		knoteController = new KnoteController(notesRepository, new KnoteProperties(), Parser.builder().build(),
				HtmlRenderer.builder().build());
	}

	@Test
	public void testIndexShouldReturnListOfNotes() {
		// Arrange
		List<Note> expectedNotes = Arrays.asList(new Note(), new Note());
		when(notesRepository.findAll()).thenReturn(expectedNotes);
		// Act
		ResponseEntity<List<Note>> responseEntity = knoteController.index(model);
		// Assert
		assertEquals("Expected status code is not returned.", HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Expected notes list is not returned.", expectedNotes, responseEntity.getBody());
	}

	@Test
    public void testIndexShouldReturnEmptyListWhenNoNotes() {
        // Arrange
        when(notesRepository.findAll()).thenReturn(new ArrayList<>());
        // Act
        ResponseEntity<List<Note>> responseEntity = knoteController.index(model);
        // Assert
        assertEquals("Expected status code is not returned.", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Expected empty list is not returned.", new ArrayList<>(), responseEntity.getBody());
    }

	@Test(expected = RuntimeException.class)
    public void testIndexShouldHandleNotesRepositoryException() {
        // Arrange
        when(notesRepository.findAll()).thenThrow(new RuntimeException());
        // Act
        knoteController.index(model);
    }

	@Test
	public void testIndexWithSpecificModelParameter() {
		// Arrange
		List<Note> expectedNotes = Arrays.asList(new Note(), new Note());
		when(notesRepository.findAll()).thenReturn(expectedNotes);
		Mockito.doNothing().when(model).addAttribute(Mockito.eq("notes"), Mockito.eq(expectedNotes));
		// Act
		ResponseEntity<List<Note>> responseEntity = knoteController.index(model);
		// Assert
		assertEquals("Expected status code is not returned.", HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Expected notes list is not returned based on model attributes.", expectedNotes,
				responseEntity.getBody());
	}

	@Test
    public void testIndexShouldHandleNullNotesList() {
        // Arrange
        when(notesRepository.findAll()).thenReturn(null);
        // Act
        ResponseEntity<List<Note>> responseEntity = knoteController.index(model);
        // Assert
        assertEquals("Expected status code is not returned.", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Expected empty list is not returned when null is received.", new ArrayList<>(), responseEntity.getBody());
    }

}