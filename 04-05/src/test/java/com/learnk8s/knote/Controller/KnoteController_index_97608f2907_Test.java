package com.learnk8s.knote.Controller;

import com.learnk8s.knote.Note.Note;
import com.learnk8s.knote.Repository.NotesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class KnoteController_index_97608f2907_Test {

    @InjectMocks
    KnoteController knoteController;

    @Mock
    NotesRepository notesRepository;

    @Mock
    Model model;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIndexMethodWithValidModel() {
        Note note1 = new Note();
        Note note2 = new Note();

        List<Note> mockNotes = Arrays.asList(note1, note2);

        when(notesRepository.findAll()).thenReturn(mockNotes);

        ResponseEntity<List<Note>> response = knoteController.index(model);

        Collections.reverse(mockNotes);
        // Assert the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockNotes, response.getBody());
    }

    @Test
    public void testIndexMethodWithEmptyModel() {


        when(notesRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Note>>  response = knoteController.index(model);

        // Assert the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}
