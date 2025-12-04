package com.candileasing.notificationservice.core.interceptors;

import com.candileasing.notificationservice.core.utils.AppInterceptor;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Project title: gateway-server
 * Created by john.adeshola
 * Date: 18/02/2021
 * Time: 4:36 PM
 */
@Service
@Slf4j
public class AuthInterceptor {

    @Bean
    public RequestInterceptor authRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", AppInterceptor.authHeader());
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
            requestTemplate.header("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
            log.info("headers ===> {}", requestTemplate);
        };
    }
}

