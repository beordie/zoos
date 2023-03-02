package com.beordie.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.StringReader;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/2 11:22
 * @User beordie
 */
@Configuration
@Data
public class CommonConfig {
    @Value("${image.visitor}")
    private String visitorDirectory;

    @Value("${image.upload}")
    private String uploadDirectory;
}
