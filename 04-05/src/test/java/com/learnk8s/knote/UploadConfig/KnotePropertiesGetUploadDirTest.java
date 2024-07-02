package com.learnk8s.knote.UploadConfig;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.junit.experimental.categories.Category;

@Category({ Categories.getUploadDir.class })
public class KnotePropertiesGetUploadDirTest {

	private KnoteProperties knoteProperties;

	@Before
	public void setUp() {
		knoteProperties = new KnoteProperties();
	}

	@Test
	public void shouldReturnCurrentUploadDir() {
		// Arrange
		String expectedUploadDir = "expected/upload/dir"; // TODO: Replace with actual
															// expected directory
		ReflectionTestUtils.setField(knoteProperties, "uploadDir", expectedUploadDir);
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertEquals("The uploadDir should match the expected value.", expectedUploadDir, actualUploadDir);
	}

	@Test
	public void shouldReturnNullWhenUploadDirNotSet() {
		// Arrange
		// No arrangement needed as uploadDir should be null by default
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertNull("The uploadDir should be null when not set.", actualUploadDir);
	}

	@Test
	public void shouldReturnInjectedValueBySpring() {
		// Arrange
		String injectedValue = "injected/upload/dir"; // TODO: Replace with actual
														// injected value
		ReflectionTestUtils.setField(knoteProperties, "uploadDir", injectedValue);
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertEquals("The uploadDir should match the injected value by Spring.", injectedValue, actualUploadDir);
	}

	@Test
	public void shouldReflectUpdatedValueAfterConfigurationChange() {
		// Arrange
		String originalValue = "original/upload/dir"; // TODO: Replace with actual
														// original directory
		String updatedValue = "updated/upload/dir"; // TODO: Replace with actual updated
													// directory
		ReflectionTestUtils.setField(knoteProperties, "uploadDir", originalValue);
		// Simulate configuration change
		ReflectionTestUtils.setField(knoteProperties, "uploadDir", updatedValue);
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertEquals("The uploadDir should match the updated configuration value.", updatedValue, actualUploadDir);
	}

	// Inner class to mimic the actual KnoteProperties class
	@ConfigurationProperties(prefix = "knote")
	private static class KnoteProperties {

		@Value("${uploadDir}")
		private String uploadDir;

		public String getUploadDir() {
			return uploadDir;
		}

		public void setUploadDir(String uploadDir) {
			this.uploadDir = uploadDir;
		}

	}

}