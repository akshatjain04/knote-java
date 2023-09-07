package com.learnk8s.knote.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class KnoteController_uploadImage_e097a52780_Test {

    @InjectMocks
    KnoteController knoteController;

    @Mock
    KnoteProperties properties;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUploadImage() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpg", "test image content".getBytes());
        String description = "Test Image";
        Model model = new ModelMap();

        when(properties.getUploadDir()).thenReturn("uploads/");

        knoteController.uploadImage(file, description, model);

        assertEquals(description + " ![](uploads/" + file.getOriginalFilename() + ")", model.getAttribute("description"));
    }

    @Test
    public void testUploadImage_directoryDoesNotExist() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpg", "test image content".getBytes());
        String description = "Test Image";
        Model model = new ModelMap();

        when(properties.getUploadDir()).thenReturn("nonexistent_directory/");

        knoteController.uploadImage(file, description, model);

        File uploadsDir = new File(properties.getUploadDir());
        assertEquals(true, uploadsDir.exists());
    }
}
