package com.learnk8s.knote.Controller;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito.mock;
import org.mockito.Mockito.when;
import org.junit.jupiter.api;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;

public class KnoteControllerTest {

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("valid")
	public void indexReturnsAllNotesSuccessfullyWhenListPopulated() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);
		List<Note> mockedNotes = Arrays.asList(new Note("1", "Note 1 description"),
				new Note("2", "Note 2 description"));

		knoteController.notesRepository = mock(NotesRepository.class);
		when(knoteController.notesRepository.findAll()).thenReturn(mockedNotes);

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockedNotes, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("boundary")
	public void indexReturnsEmptyListWhenNoNotesAreAvailable() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = mock(NotesRepository.class);
		when(knoteController.notesRepository.findAll()).thenReturn(new ArrayList<>());

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(new ArrayList<>(), response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("invalid")
	public void indexThrowsErrorWhenGetAllNotesFails() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = mock(NotesRepository.class);
		when(knoteController.notesRepository.findAll()).thenThrow(new RuntimeException("Error occurred"));

		try {
			knoteController.index(model);
		}
		catch (RuntimeException e) {
			assertEquals("Error occurred", e.getMessage());
		}
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("integration")
	public void indexInteractsWithModelCorrectly() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = mock(NotesRepository.class);
		List<Note> mockedNotes = Arrays.asList(new Note("1", "Note 1 description"),
				new Note("2", "Note 2 description"));
		when(knoteController.notesRepository.findAll()).thenReturn(mockedNotes);

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(knoteController.notesRepository).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("valid")
	public void indexResponseStructureIsCorrect() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = mock(NotesRepository.class);
		List<Note> mockedNotes = Arrays.asList(new Note("1", "Note 1 description"),
				new Note("2", "Note 2 description"));
		when(knoteController.notesRepository.findAll()).thenReturn(mockedNotes);

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockedNotes, response.getBody());
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("boundary")
	public void indexHandlesEdgeCasesGracefully() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = mock(NotesRepository.class);
		when(knoteController.notesRepository.findAll()).thenReturn(null);

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertEquals(new ArrayList<>(), response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("integration")
	public void indexUtilizesNotesRepositoryCorrectly() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = mock(NotesRepository.class);
		List<Note> mockedNotes = Arrays.asList(new Note("1", "Note 1 description"),
				new Note("2", "Note 2 description"));
		when(knoteController.notesRepository.findAll()).thenReturn(mockedNotes);

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockedNotes, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("invalid")
	public void indexHandlesNullNotesRepositoryGracefully() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = null;

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertEquals(new ArrayList<>(), response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("boundary")
	public void indexHandlesLargeListOfNotesCorrectly() {

		KnoteController knoteController = new KnoteController();
		Model model = mock(Model.class);

		knoteController.notesRepository = mock(NotesRepository.class);
		List<Note> largeNotesList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			largeNotesList.add(new Note(String.valueOf(i), "Description " + i));
		}
		when(knoteController.notesRepository.findAll()).thenReturn(largeNotesList);

		ResponseEntity<List<Note>> response = knoteController.index(model);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(largeNotesList, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
@Tag("valid")
public void saveNotesWithValidUploadRequest() throws Exception {
    when(file.getOriginalFilename()).thenReturn("validImage.jpg");
    String description = "Valid description";
    String upload = "Upload";
    ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, null, upload, model);
    assertEquals(HttpStatus.CREATED, response.getBody());
    verify(file, times(1)).getOriginalFilename();
}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
@Tag("invalid")
public void saveNotesWithInvalidUploadFile() throws Exception {

    when(file.getOriginalFilename()).thenReturn("");
    String description = "Valid description";
    String upload = "Upload";
    ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, null, upload, model);
    assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
    verify(file, times(1)).getOriginalFilename();
}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("valid")
	public void saveNotesWithValidPublishRequest() throws Exception {
		String description = "Valid description";
		String publish = "Publish";
		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, description, publish, null, model);
		assertEquals(HttpStatus.CREATED, response.getBody());
		verify(model, times(1)).addAttribute(anyString(), any());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("invalid")
	public void saveNotesWithNeitherUploadNorPublish() throws Exception {
		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, null, null, null, model);
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
		verifyNoInteractions(model);
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
@Tag("boundary")
public void saveNotesWithValidFileAndMissingDescription() throws Exception {
    when(file.getOriginalFilename()).thenReturn("validImage.jpg");

    String description = "";
    String upload = "Upload";
    ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, null, upload, model);
    assertEquals(HttpStatus.CREATED, response.getBody());
    verify(file, times(1)).getOriginalFilename();
}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("boundary")
	public void saveNotesWithMissingPublishDescription() throws Exception {

		String description = "";
		String publish = "Publish";
		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, description, publish, null, model);
		assertEquals(HttpStatus.CREATED, response.getBody());
		verify(model, times(1)).addAttribute(anyString(), any());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("invalid")
	public void saveNotesWithInvalidUploadAndPublishValues() throws Exception {

		String upload = "Invalid";

		String publish = "Invalid";
		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, null, publish, upload, model);
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
		verifyNoInteractions(model);
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
@Tag("integration")
public void saveNotesWithValidPublishAndRepositoryMock() throws Exception {
    when(model.addAttribute(anyString(), any())).thenReturn(null);
    String description = "Valid publish description";
    String publish = "Publish";
    ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, description, publish, null, model);
    assertEquals(HttpStatus.CREATED, response.getBody());
    verify(model, times(1)).addAttribute(anyString(), any());
}

}