package com.learnk8s.knote.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.learnk8s.knote.note.Note;
import com.learnk8s.knote.service.NoteService;

public class KnoteController_index_97608f2907_Test {

    @Test
    public void testIndexMethodWithValidModel() {
        // Mock the NoteService and Note objects
        NoteService noteService = mock(NoteService.class);
        Note note1 = new Note();
        Note note2 = new Note();
        List<Note> mockNotes = Arrays.asList(note1, note2);

        // Mock the getAllNotes method to return our mockNotes
        when(noteService.getAllNotes()).thenReturn(mockNotes);

        KnoteController controller = new KnoteController(noteService);
        ResponseEntity<List<Note>> response = controller.index();

        // Assert the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockNotes, response.getBody());
    }

    @Test
    public void testIndexMethodWithEmptyModel() {
        // Mock the NoteService object
        NoteService noteService = mock(NoteService.class);

        // Mock the getAllNotes method to return an empty list
        when(noteService.getAllNotes()).thenReturn(Collections.emptyList());

        KnoteController controller = new KnoteController(noteService);
        ResponseEntity<List<Note>> response = controller.index();

        // Assert the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}
