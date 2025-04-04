package com.candileasing.notificationservice.model.response;

import lombok.Getter;
import lombok.Setter;

import java.security.Principal;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:50 PM
 */
@Getter
@Setter
public class StompPrincipal implements Principal {

    private String name;

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


}
