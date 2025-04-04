package com.candileasing.notificationservice.model.request;

import lombok.*;

import java.io.Serializable;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 9:00 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomEmailSetUp implements Serializable {

    private String username;

    private String password;

    private String host;

    private Integer port;

    private String protocol;

    private Boolean smtpAuth;

    private Boolean starttlsEnabled;

    private Boolean starttlsRequired;

    private String primarySender;

    private String secondarySender;

    private Character status;

    private Boolean enabled;

    private Long organization;

}
