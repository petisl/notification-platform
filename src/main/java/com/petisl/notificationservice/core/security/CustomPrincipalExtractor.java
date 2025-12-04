package com.candileasing.notificationservice.core.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

/**
 * Project title: gateway-server
 * Created by john.adeshola
 * Date: 22/02/2021
 * Time: 8:38 PM
 */
public class CustomPrincipalExtractor implements PrincipalExtractor {

    private static final String[] PRINCIPAL = new String[]{"id", "firstName", "lastName", "username", "telephone", "email", "isAdmin", "status", "organization", "countryCode", "passwordReset", "lastLoginDate"};

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        for (String key : PRINCIPAL) {
            if (map.containsKey(key)) {
                System.out.println("key --> " + map.toString());
                return map.get(key);
            }
        }
        return null;
    }
}

