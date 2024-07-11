package com.learnk8s.knote.Note;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class NoteToStringTest {

	private Note note;

	@Before
	public void setUp() {
		// common setup if needed
	}

	@Test
	public void toStringReturnsValidDescription() {
		// Arrange
		String expectedDescription = "This is a test description.";
		note = new Note("1", expectedDescription);
		// Act
		String actualDescription = note.toString();
		// Assert
		assertEquals("The toString method should return the description", expectedDescription, actualDescription);
	}

	@Test
	public void toStringHandlesNullDescription() {
		// Arrange
		note = new Note("1", null);
		// Act
		String actualDescription = note.toString();
		// Assert
		assertNull("The toString method should handle null description", actualDescription);
	}

	@Test
	public void toStringReflectsUpdatedDescription() {
		// Arrange
		String initialDescription = "Initial description";
		String updatedDescription = "Updated description";
		note = new Note("1", initialDescription);
		note.setDescription(updatedDescription);
		// Act
		String actualDescription = note.toString();
		// Assert
		assertEquals("The toString method should reflect the updated description", updatedDescription,
				actualDescription);
	}

	@Test
	public void toStringWithPersistedEntity() {
		// Arrange
		String expectedDescription = "Persisted entity description";
		note = new Note("1", expectedDescription);
		// Simulate the entity being persisted by setting an ID value
		// Act
		String actualDescription = note.toString();
		// Assert
		assertEquals("The toString method should work the same for persisted entities", expectedDescription,
				actualDescription);
	}

	@Test
	public void toStringHandlesSpecialCharacters() {
		// Arrange
		String expectedDescription = "Special characters: \n\t\u00A9";
		note = new Note("1", expectedDescription);
		// Act
		String actualDescription = note.toString();
		// Assert
		assertEquals("The toString method should accurately represent special characters", expectedDescription,
				actualDescription);
	}

}