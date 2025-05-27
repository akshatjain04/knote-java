package com.learnk8s.knote.Controller;

import org.junit.Assert.assertEquals;
import org.mockito.Mockito.mock;
import org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.learnk8s.knote.Note.Note;
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
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import Categories;

public class KnoteControllerTest {

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Category(Categories.valid.class)
	public void retrieveAllNotesSuccessfully() {
		Model model = mock(Model.class);
		KnoteController controller = new KnoteController();
		Note note1 = new Note(1, "Note 1");
		Note note2 = new Note(2, "Note 2");
		List<Note> notes = Arrays.asList(note1, note2);
		when(controller.getAllNotes(model)).thenReturn(notes);
		ResponseEntity<List<Note>> response = controller.index(model);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(notes, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Category(Categories.valid.class)
	public void retrieveEmptyNoteListSuccessfully() {
		Model model = mock(Model.class);
		KnoteController controller = new KnoteController();
		List<Note> notes = Arrays.asList();
		when(controller.getAllNotes(model)).thenReturn(notes);
		ResponseEntity<List<Note>> response = controller.index(model);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(notes, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=index_544d09df63 ROOST_METHOD_SIG_HASH=index_5913f4c0f2
	 *
	 */@Test
	@Category(Categories.valid.class)
	public void validateModelObjectWithNoteListSuccessfully() {
		Model model = mock(Model.class);
		KnoteController controller = new KnoteController();
		Note note1 = new Note(1, "Note 1");
		Note note2 = new Note(2, "Note 2");
		List<Note> notes = Arrays.asList(note1, note2);
		when(controller.getAllNotes(model)).thenReturn(notes);
		controller.index(model);
		assertEquals(notes, model.getAttribute("notes"));
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Category(Categories.invalid.class)
	public void testBothUploadAndPublishNull() {
		KnoteController knoteController = new KnoteController();
		MultipartFile file = new MockMultipartFile("image", new byte[0]);
		Model model = mock(Model.class);
		ResponseEntity<HttpStatusCode> result = knoteController.saveNotes(file, "description", null, null, model);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Category(Categories.valid.class)
	public void testValidUpload() throws Exception {
		KnoteController knoteController = spy(new KnoteController());
		MultipartFile file = new MockMultipartFile("image", "filename.jpg", "image/jpg", new byte[10]);
		Model model = mock(Model.class);
		doNothing().when(knoteController).uploadImage(any(MultipartFile.class), anyString(), any(Model.class));
		ResponseEntity<HttpStatusCode> result = knoteController.saveNotes(file, "description", null, "Upload", model);
		verify(knoteController, times(1)).uploadImage(file, "description", model);
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Category(Categories.invalid.class)
	public void testInvalidUpload() {
		KnoteController knoteController = new KnoteController();
		MultipartFile file = new MockMultipartFile("image", "", "image/jpg", new byte[0]);
		Model model = mock(Model.class);
		ResponseEntity<HttpStatusCode> result = knoteController.saveNotes(file, "description", null, "Upload", model);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	/*
	 * ROOST_METHOD_HASH=saveNotes_a7f7d80b71 ROOST_METHOD_SIG_HASH=saveNotes_584e2e5550
	 *
	 */@Test
	@Category(Categories.valid.class)
	public void testValidPublish() throws Exception {
		KnoteController knoteController = spy(new KnoteController());
		Model model = mock(Model.class);
		doNothing().when(knoteController).saveNote(anyString(), any(Model.class));
		ResponseEntity<HttpStatusCode> result = knoteController.saveNotes(null, "description", "Publish", null, model);
		verify(knoteController, times(1)).saveNote("description", model);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

}