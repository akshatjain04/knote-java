package com.learnk8s.knote.UploadConfig;

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

    @BeforeEach
    public void setup() {
        knoteProperties = new KnoteProperties();
    }

    @Test
    public void testGetUploadDir_Success() {
        String actualUploadDir = knoteProperties.getUploadDir();
        assertEquals(null, actualUploadDir);
    }

}
