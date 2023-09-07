package com.learnk8s.knote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KnoteProperties_getUploadDir_caabfc00fd_Test {

    private String uploadDir = "uploads/";

    @InjectMocks
    private KnoteProperties knoteProperties;

    @Mock
    private UploadConfig uploadConfig;

    @BeforeEach
    public void setup() {
        knoteProperties = new KnoteProperties();
        knoteProperties.setUploadDir(uploadDir);
    }

    @Test
    public void testGetUploadDir_Success() {
        when(uploadConfig.getUploadDir()).thenReturn("uploads/");
        String actualUploadDir = knoteProperties.getUploadDir();
        assertEquals("uploads/", actualUploadDir);
    }

    @Test
    public void testGetUploadDir_Failure() {
        when(uploadConfig.getUploadDir()).thenReturn(null);
        String actualUploadDir = knoteProperties.getUploadDir();
        assertEquals(null, actualUploadDir);
    }
}
