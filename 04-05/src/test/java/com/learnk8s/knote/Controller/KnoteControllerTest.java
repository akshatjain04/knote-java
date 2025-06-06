package com.learnk8s.knote.Controller;

import com.learnk8s.knote.Note.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.multipart.MultipartFile;
import com.learnk8s.knote.Repository.NotesRepository;
import com.learnk8s.knote.UploadConfig.KnoteProperties;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.io.File;
import java.util.Collections;
import java.util.UUID;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.mockito.Mockito.doNothing;
import org.mockito.Mockito.mock;
import org.mockito.Mockito.when;
import org.junit.jupiter.api;

public class KnoteControllerTest {

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@BeforeEach
	public void setup() {

		knoteController = Mockito.mock(KnoteController.class);
		mockModel = Mockito.mock(Model.class);
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("valid")
	public void indexReturnsNotesSuccessfully() {
		List<Note> mockedNotes = new ArrayList<>();

		mockedNotes.add(new Note());

		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(mockedNotes));
		ResponseEntity<List<Note>> response = knoteController.index(mockModel);
		assertEquals(mockedNotes, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("valid")
	public void indexReturnsEmptyNotesSuccessfully() {
		List<Note> mockedNotes = new ArrayList<>();

		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(mockedNotes));
		ResponseEntity<List<Note>> response = knoteController.index(mockModel);
		assertEquals(mockedNotes, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("integration")
	public void indexInvokesIndexMethodProperly() {
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(new ArrayList<>()));
		knoteController.index(mockModel);

		verify(knoteController, times(1)).index(mockModel);
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("boundary")
	public void indexHandlesModelGracefully() {
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(new ArrayList<>()));
		ResponseEntity<List<Note>> response = knoteController.index(mockModel);
		assertEquals(new ArrayList<>(), response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("valid")
	public void indexReturnsResponseEntityOfCorrectType() {
		List<Note> mockedNotes = new ArrayList<>();
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(mockedNotes));
		ResponseEntity<List<Note>> responseValidData = knoteController.index(mockModel);
		assertEquals(ResponseEntity.class, responseValidData.getClass());
		List<Note> emptyNotesList = new ArrayList<>();
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(emptyNotesList));
		ResponseEntity<List<Note>> responseEmptyData = knoteController.index(mockModel);
		assertEquals(ResponseEntity.class, responseEmptyData.getClass());
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(null));
		ResponseEntity<List<Note>> responseNullData = knoteController.index(mockModel);
		assertEquals(ResponseEntity.class, responseNullData.getClass());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("invalid")
	public void indexHandlesNotesReturningNull() {
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(null));
		ResponseEntity<List<Note>> response = knoteController.index(mockModel);
		assertEquals(new ArrayList<>(), response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("valid")
	public void indexReturnsMultipleNotesSuccessfully() {
		List<Note> mockedNotes = new ArrayList<>();
		mockedNotes.add(new Note());
		mockedNotes.add(new Note());
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(mockedNotes));
		ResponseEntity<List<Note>> response = knoteController.index(mockModel);
		assertEquals(mockedNotes, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Tag("boundary")
	public void indexHandlesConcurrentAccessSafely() throws InterruptedException {
		List<Note> mockedNotes = new ArrayList<>();
		mockedNotes.add(new Note());
		Mockito.when(knoteController.index(mockModel)).thenReturn(ResponseEntity.ok(mockedNotes));
		Runnable task = () -> {
			ResponseEntity<List<Note>> response = knoteController.index(mockModel);
			assertEquals(mockedNotes, response.getBody());
			assertEquals(200, response.getStatusCodeValue());
		};
		Thread thread1 = new Thread(task);
		Thread thread2 = new Thread(task);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("valid")
	public void validateNoteCreationWithPublish() throws Exception {

		Model model = mock(Model.class);

		String description = "Valid Note Description";
		String publish = "Publish";

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, description, publish, null, model);

		assertEquals(HttpStatus.CREATED, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("invalid")
	public void validateBadRequestWithNullUploadAndPublish() throws Exception {

		Model model = mock(Model.class);

		String description = "Valid Description";

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, description, null, null, model);

		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("valid")
	public void validateImageUpload() throws Exception {

		Model model = mock(Model.class);
		MultipartFile file = mock(MultipartFile.class);

		when(file.getOriginalFilename()).thenReturn("validImage.jpg");

		String description = "Valid Description";
		String upload = "Upload";
		doNothing().when(model).addAttribute(Mockito.anyString(), Mockito.any());

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, null, upload, model);

		assertEquals(HttpStatus.CREATED, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("invalid")
	public void validateNoFileUploadError() throws Exception {

		Model model = mock(Model.class);

		MultipartFile file = null;

		String description = "Valid Description";
		String upload = "Upload";

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, null, upload, model);

		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("invalid")
	public void validateEmptyFileNameUploadError() throws Exception {

		Model model = mock(Model.class);
		MultipartFile file = mock(MultipartFile.class);

		when(file.getOriginalFilename()).thenReturn("");

		String description = "Valid Description";
		String upload = "Upload";

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, null, upload, model);

		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("invalid")
	public void validateInvalidDescriptionForPublish() throws Exception {

		Model model = mock(Model.class);

		String description = "";
		String publish = "Publish";

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, description, publish, null, model);

		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("integration")
	public void validateBothUploadAndPublishParameters() throws Exception {

		Model model = mock(Model.class);
		MultipartFile file = mock(MultipartFile.class);

		when(file.getOriginalFilename()).thenReturn("validImage.jpg");

		String description = "Valid Description";
		String upload = "Upload";
		String publish = "Publish";
		doNothing().when(model).addAttribute(Mockito.anyString(), Mockito.any());

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, publish, upload, model);

		assertEquals(HttpStatus.CREATED, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("invalid")
	public void validateAbsenceOfOptionalParameters() throws Exception {

		Model model = mock(Model.class);

		String description = "Valid Description";

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(null, description, null, null, model);

		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Tag("boundary")
	public void validateNullDescriptionWithUpload() throws Exception {

		Model model = mock(Model.class);
		MultipartFile file = mock(MultipartFile.class);

		when(file.getOriginalFilename()).thenReturn("validImage.jpg");

		String description = null;
		String upload = "Upload";
		doNothing().when(model).addAttribute(Mockito.anyString(), Mockito.any());

		ResponseEntity<HttpStatusCode> response = knoteController.saveNotes(file, description, null, upload, model);

		assertEquals(HttpStatus.CREATED, response.getBody());
	}

}