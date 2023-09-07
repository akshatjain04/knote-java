package com.learnk8s.knote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Note_toString_ceffa8036e_Test {

    private Note note;

    @BeforeEach
    public void setUp() {
        note = new Note();
    }

    @Test
    public void testToStringWhenDescriptionIsNotNull() {
        String expectedDescription = "This is a test description";
        note.setDescription(expectedDescription);

        String actualDescription = note.toString();

        assertEquals(expectedDescription, actualDescription, "The actual description should match the expected description");
    }

    @Test
    public void testToStringWhenDescriptionIsNull() {
        note.setDescription(null);

        String actualDescription = note.toString();

        assertEquals(null, actualDescription, "The actual description should be null");
    }
}
