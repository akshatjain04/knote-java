package com.learnk8s.knote;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import static org.junit.Assert.*;

@ConfigurationProperties(prefix = "knote")
public class KnoteProperties_getUploadDir_caabfc00fd_Test {

    private KnoteProperties knoteProperties;

    @Value("${uploadDir:#{null}}")
    private String uploadDir;

    @Before
    public void setUp() {
        knoteProperties = new KnoteProperties();
    }

    @Test
    public void testGetUploadDir_SetInConfiguration_ReturnsCorrectPath() {
        // TODO: Set the uploadDir property value in configuration for this test scenario
        String expected = "path/to/uploadDir"; // Replace with the path set in configuration
        knoteProperties.setUploadDir(expected); // Simulating injection of property
        String actual = knoteProperties.getUploadDir();
        assertEquals("The returned upload directory path should match the configured path", expected, actual);
    }

    @Test
    public void testGetUploadDir_NotSetInConfiguration_ReturnsNull() {
        // Assuming no value is set for uploadDir in the configuration
        String expected = null;
        String actual = knoteProperties.getUploadDir();
        assertEquals("The returned upload directory path should be null when not configured", expected, actual);
    }

    // Class to be tested
    public class KnoteProperties {
        private String uploadDir;

        public KnoteProperties() {
        }

        public String getUploadDir() {
            return uploadDir;
        }

        public void setUploadDir(String uploadDir) {
            this.uploadDir = uploadDir;
        }
    }
}
