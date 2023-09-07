package com.learnk8s.knote.Controller;

import com.learnk8s.knote.Note.Note;
import com.learnk8s.knote.Repository.NotesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class KnoteController_getAllNotes_589da55d95_Test {

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
    public void testGetAllNotes_success() {
        Note note1 = new Note();
        note1.setTitle("Title 1");
        note1.setContent("Content 1");

        Note note2 = new Note();
        note2.setTitle("Title 2");
        note2.setContent("Content 2");

        List<Note> notes = Arrays.asList(note1, note2);

        when(notesRepository.findAll()).thenReturn(notes);

        List<Note> result = knoteController.getAllNotes(model);

        Collections.reverse(notes);
        assertEquals(notes, result);
    }

    @Test
    public void testGetAllNotes_empty() {
        List<Note> notes = Collections.emptyList();

        when(notesRepository.findAll()).thenReturn(notes);

        List<Note> result = knoteController.getAllNotes(model);

        assertEquals(notes, result);
    }
}
