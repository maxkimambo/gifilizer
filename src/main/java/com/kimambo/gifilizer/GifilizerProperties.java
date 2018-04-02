package com.kimambo.gifilizer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gifilizer")
public class GifilizerProperties {
    private String FileUploadLocation;
}
