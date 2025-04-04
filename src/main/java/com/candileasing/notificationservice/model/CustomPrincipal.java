package com.candileasing.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Project title: gateway-server
 * Created by john.adeshola
 * Date: 18/02/2021
 * Time: 4:29 PM
 */
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomPrincipal implements Serializable {

    private Long id;
    private String lastName;
    private String firstName;
    private String telephone;
    private String username;
    private String email;
    private Boolean isAdmin;
    private String status;
    private Long organization;
    private String countryCode;
    private Boolean passwordReset;
    private String lastLoginDate;
    private List<String> authorities;
}

