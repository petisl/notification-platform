package com.candileasing.notificationservice.core.utils;

import java.util.Base64;

/**
 * Project title: gateway-server
 * Created by john.adeshola
 * Date: 18/02/2021
 * Time: 4:37 PM
 */
public class AppInterceptor {

    public static String authHeader() {
        String plainAuth = "hydrogenhr-auth-service" + ":" + "password";
        byte[] encodedAuth = Base64.getEncoder().encode(plainAuth.getBytes());
        return "Basic " + new String(encodedAuth);
    }
}

