package com.learnk8s.knote.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import com.learnk8s.knote.Note.Note;
import com.learnk8s.knote.Repository.NotesRepository;
import com.learnk8s.knote.UploadConfig.KnoteProperties;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.experimental.categories.Category;

@Category({ Categories.saveNotes.class, Categories.uploadImage.class, Categories.saveNote.class })
@RunWith(MockitoJUnitRunner.class)
public class KnoteControllerSaveNotesTest {

	@InjectMocks
	private KnoteController knoteController;

	@Mock
	private NotesRepository notesRepository;

	@Mock
	private KnoteProperties properties;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validUploadRequestWithFileAndDescription() throws Exception {
		MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png",
				"test image content".getBytes());
		String description = "A valid description";
		ResponseEntity<HttpStatus> response = knoteController.saveNotes(file, description, null, "Upload", model);
		assertEquals(HttpStatus.CREATED, response.getBody());
	}

	@Test
	public void validPublishRequestWithDescription() throws Exception {
		String description = "A valid description";
		ResponseEntity<HttpStatus> response = knoteController.saveNotes(null, description, "Publish", null, model);
		assertEquals(HttpStatus.CREATED, response.getBody());
	}

	@Test
	public void missingUploadAndPublishParameters() throws Exception {
		String description = "A valid description";
		ResponseEntity<HttpStatus> response = knoteController.saveNotes(null, description, null, null, model);
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	public void emptyFileOnUpload() throws Exception {
		MockMultipartFile file = new MockMultipartFile("image", "", "image/png", "".getBytes());
		String description = "A valid description";
		ResponseEntity<HttpStatus> response = knoteController.saveNotes(file, description, null, "Upload", model);
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	public void incorrectActionParameter() throws Exception {
		MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png",
				"test image content".getBytes());
		String description = "A valid description";
		ResponseEntity<HttpStatus> response = knoteController.saveNotes(file, description, null, "InvalidAction",
				model);
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	public void publishWithEmptyDescription() throws Exception {
		String description = "";
		ResponseEntity<HttpStatus> response = knoteController.saveNotes(null, description, "Publish", null, model);
		// TODO: Adjust the assertion once the expected behavior is defined for empty
		// descriptions in publish action
	}

	@Test
	public void uploadWithNullFileObject() throws Exception {
		MultipartFile file = null;
		String description = "A valid description";
		ResponseEntity<HttpStatus> response = knoteController.saveNotes(file, description, null, "Upload", model);
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

}