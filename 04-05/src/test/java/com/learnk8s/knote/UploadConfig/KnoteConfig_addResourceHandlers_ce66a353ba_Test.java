// ********RoostGPT********
/*
Test generated by RoostGPT for test MiniProjects using AI Type Azure Open AI and AI Model roost-gpt4-32k

1. Scenario: Test if the method addResourceHandlers correctly adds a resource handler for the path "/uploads/**".
2. Scenario: Test if the method addResourceHandlers correctly adds a resource location from properties.getUploadDir().
3. Scenario: Check if the function setCachePeriod correctly sets the cache period to 3600 seconds (1 hour).
4. Scenario: Test whether or not the resource chain argument is set to true.
5. Scenario: Verify if new PathResourceResolver is being added correctly in addResolver method.
6. Scenario: Validate if the "/uploads/**" path is working correctly after being set up as a resource handler.
7. Scenario: Validate the case when properties.getUploadDir() returns a null or empty string. The method should handle such cases appropriately.
8. Scenario: Check how the function behaves when an invalid cache period value (for example, negative value) is set.
9. Scenario: Validate the situation where the file or directory addressed by properties.getUploadDir() does not exist.
10. Scenario: Validate the case where there is no available disk space in the upload directory path returned by properties.getUploadDir().
11. Scenario: Test how the method behaves when there are multiple file request hits at the same time.
12. Scenario: In the event of an exception thrown by other methods, test if it is appropriately caught and handled in addResourceHandlers.
*/

// ********RoostGPT********
package com.learnk8s.knote;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

public class KnoteConfig_addResourceHandlers_ce66a353ba_Test {

    @Mock private ResourceHandlerRegistry registry;
    @Mock private KnoteProperties properties;
    @Mock private ResourceHandlerRegistration registration;

    @InjectMocks private KnoteConfig objectUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addResourceHandlersTestPathPattern() {
        String expectedPattern = "/uploads/**";
        Mockito.when(registry.addResourceHandler(expectedPattern)).thenReturn(registration);

        objectUnderTest.addResourceHandlers(registry);

        Mockito.verify(registry).addResourceHandler(expectedPattern);
    }

    @Test
    public void addResourceHandlersTestResourceLocation() {
        String expectedLocation = "file:uploadDir";
        Mockito.when(properties.getUploadDir()).thenReturn(expectedLocation);
        Mockito.when(registration.addResourceLocations(expectedLocation)).thenReturn(registration);
        Mockito.when(registry.addResourceHandler("/uploads/**")).thenReturn(registration);

        objectUnderTest.addResourceHandlers(registry);

        Mockito.verify(properties).getUploadDir();
        Mockito.verify(registration).addResourceLocations(expectedLocation);
    }

    @Test
    public void addResourceHandlersTestCachePeriod() {
        int expectedCachePeriod = 3600;
        Mockito.when(registry.addResourceHandler("/uploads/**")).thenReturn(registration);
        Mockito.when(registration.setCachePeriod(expectedCachePeriod)).thenReturn(registration);

        objectUnderTest.addResourceHandlers(registry);

        Mockito.verify(registration).setCachePeriod(expectedCachePeriod);
    }

    // TODO: Add more test methods for the remaining scenarios.
}
