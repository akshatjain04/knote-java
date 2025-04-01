
package com.learnk8s.knote.Note;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "notes")
public class NoteToStringTest {

	@Id
	private String id;

	private String description;

	@BeforeEach
	public void setUp() {
		this.id = "1";
	}

	@AfterEach
	public void tearDown() {
		this.id = null;
		this.description = null;
	}

	@Test
	@Tag("boundary")
	public void testToStringWhenDescriptionIsEmpty() {
		this.description = "";
		assertEquals("", this.toString(), "Expected empty string because description was not provided");
	}

	@Test
	@Tag("valid")
	public void testToStringWhenDescriptionIsProvided() {
		this.description = "This is a Note object description";
		assertEquals(this.description, this.toString(), "Expected description to be equal to the provided description");
	}

	@Test
	@Tag("boundary")
	public void testToStringWhenDescriptionHasWhiteSpace() {
		this.description = " ";
		assertEquals(" ", this.toString(), "Expected whitespace because the provided description has only whitespace");
	}

	@Override
	public String toString() {
		return description;
	}

}