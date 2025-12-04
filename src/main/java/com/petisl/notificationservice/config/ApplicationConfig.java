package com.candileasing.notificationservice.config;

import brave.sampler.Sampler;
import com.candileasing.notificationservice.core.constants.AppConstant;
import com.candileasing.notificationservice.model.config.MediaConfig;
import com.candileasing.notificationservice.model.config.RabbitMqProperty;
import com.candileasing.notificationservice.model.config.WebSocketProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Project title: gateway-server
 * Created by john.adeshola
 * Date: 18/02/2021
 * Time: 4:39 PM
 */
@Configuration
@EnableConfigurationProperties({RabbitMqProperty.class, WebSocketProperties.class, MediaConfig.class})
@EnableAsync
public class ApplicationConfig implements WebMvcConfigurer {

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "PUT", "DELETE", "POST", "OPTIONS", "PATCH")
                .allowedHeaders("Access-Control-Allow-Origin", "Authorization", "if-non-match", "Content-Type", "Accept", "Accept-Language", "Device")
                .allowCredentials(true)
                .maxAge(3600L)
                .allowedOrigins("*");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jsonMessageConverter.getObjectMapper();
                objectMapper.findAndRegisterModules();
                objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
                objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                objectMapper.setDateFormat(AppConstant.DateFormatters.defaultDateFormat);
                break;
            }
        }
    }
}

