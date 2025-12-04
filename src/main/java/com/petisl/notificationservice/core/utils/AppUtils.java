package com.candileasing.notificationservice.core.utils;

import com.candileasing.notificationservice.model.CustomPrincipal;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;

/**
 * Project title: gateway-server
 * Created by john.adeshola
 * Date: 22/02/2021
 * Time: 9:45 PM
 */
@Slf4j
public class AppUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Type type) {
        try {
            return mapper.writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error occurred serializing object to json string, error => " + e.getMessage());
        }
    }

    public static <T> String toJson(T t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error occurred serializing object to json string, error => " + e.getMessage());
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Error occurred deserializing object to json string >> {}", e.getMessage());
        }
        return null;
    }

    public static <T> T fromJson(String json, TypeReference<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Error occurred deserializing object to json string >> {}", e.getMessage());
        }
        return null;
    }

    public static <T, K, V> T fromJson(Map<K, V> json, Class<T> clazz) {
        return mapper.convertValue(json, clazz);
    }

    public static <T, K, V> T fromJson(Map<K, V> json, TypeReference<T> clazz) {
        return mapper.convertValue(json, clazz);
    }

    public static <T> T fromJson(T json, TypeReference<T> clazz) {
        return mapper.convertValue(json, clazz);
    }

    public static Optional<CustomPrincipal> getAuthUser() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, Object> claims = parser.parseMap(JwtHelper.decode(token).getClaims());
            CustomPrincipal principal = fromJson(claims, CustomPrincipal.class);
            return Optional.ofNullable(principal);
        }
        return Optional.empty();
    }

    public static CustomPrincipal getPrincipal() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, Object> claims = parser.parseMap(JwtHelper.decode(token).getClaims());
            return fromJson(claims, CustomPrincipal.class);
        }
        return null;
    }

    public static Map<String, ?> getToken() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
            JsonParser parser = JsonParserFactory.getJsonParser();
            return parser.parseMap(JwtHelper.decode(token).getClaims());
        }
        return null;
    }

    public static boolean hasAuthority(String authority) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
    }

    public static Object convertBytesToObject(byte[] body, String encoding, JavaType targetJavaType) throws JsonParseException, JsonMappingException, IOException {
        String contentAsString = new String(body, encoding);
        return mapper.readValue(contentAsString, targetJavaType);
    }

    public static Object convertBytesToObject(byte[] body, String encoding, Class targetClass) throws JsonParseException, JsonMappingException, IOException {
        String contentAsString = new String(body, encoding);
        return mapper.readValue(contentAsString, mapper.constructType(targetClass));
    }
}

