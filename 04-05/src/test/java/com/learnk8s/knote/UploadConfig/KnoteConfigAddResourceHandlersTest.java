package com.learnk8s.knote.UploadConfig;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class KnoteConfigAddResourceHandlersTest {

	@Mock
	private ResourceHandlerRegistry resourceHandlerRegistry;

	@Mock
	private KnoteProperties properties;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void ensureResourceHandlerIsAddedCorrectly() {
		// Arrange
		String uploadDir = "test/uploads/";
		when(properties.getUploadDir()).thenReturn(uploadDir);
		// Act
		new KnoteConfig(properties).addResourceHandlers(resourceHandlerRegistry);
		// Assert
		verify(resourceHandlerRegistry).addResourceHandler("/uploads/**");
		verify(resourceHandlerRegistry).addResourceLocations("file:" + uploadDir);
		verify(resourceHandlerRegistry).setCachePeriod(3600);
		verify(resourceHandlerRegistry).resourceChain(true);
		verify(resourceHandlerRegistry).addResolver(any(PathResourceResolver.class));
	}

	@Test
    public void handleMissingUploadDirectory() {
        // Arrange
        when(properties.getUploadDir()).thenReturn(null);
        // Act
        new KnoteConfig(properties).addResourceHandlers(resourceHandlerRegistry);
        // Assert
        verify(resourceHandlerRegistry, never()).addResourceHandler("/uploads/**");
    }

	@Test
	public void verifyCachePeriodConfiguration() {
		// Arrange
		String uploadDir = "test/uploads/";
		when(properties.getUploadDir()).thenReturn(uploadDir);
		// Act
		new KnoteConfig(properties).addResourceHandlers(resourceHandlerRegistry);
		// Assert
		verify(resourceHandlerRegistry).setCachePeriod(3600);
	}

	@Test
	public void ensureResourceChainIsEnabled() {
		// Arrange
		String uploadDir = "test/uploads/";
		when(properties.getUploadDir()).thenReturn(uploadDir);
		// Act
		new KnoteConfig(properties).addResourceHandlers(resourceHandlerRegistry);
		// Assert
		verify(resourceHandlerRegistry).resourceChain(true);
	}

	@Test
	public void validatePathResourceResolverAddition() {
		// Arrange
		String uploadDir = "test/uploads/";
		when(properties.getUploadDir()).thenReturn(uploadDir);
		// Act
		new KnoteConfig(properties).addResourceHandlers(resourceHandlerRegistry);
		// Assert
		verify(resourceHandlerRegistry).addResolver(any(PathResourceResolver.class));
	}

}