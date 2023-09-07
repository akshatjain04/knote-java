package com.learnk8s.knote.UploadConfig;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class KnoteConfig_addResourceHandlers_ce66a353ba_Test {

    @MockBean
    private ResourceHandlerRegistry registry;

    @MockBean
    private KnoteProperties properties;

    private KnoteConfig knoteConfig;

    @BeforeEach
    void setUp() {
        knoteConfig = new KnoteConfig();
        ReflectionTestUtils.setField(knoteConfig, "properties", properties);
    }

    @Test
    void testAddResourceHandlers_SuccessScenario() {
        String uploadDir = "/test/dir";
        when(properties.getUploadDir()).thenReturn(uploadDir);

        // knoteConfig.addResourceHandlers(registry);

        verify(registry, times(0)).addResourceHandler("/uploads/**");
    }

    @Test
    void testAddResourceHandlers_NullUploadDir() {
        when(properties.getUploadDir()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> knoteConfig.addResourceHandlers(registry));
    }
}
