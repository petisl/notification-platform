package com.candileasing.notificationservice.model.response;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:59 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotifyResponse implements Serializable {

    private Long id;

    private String title;

    private String message;

    private Long users;

    private Long organization;

    private Timestamp dateCreated;

    @Builder.Default
    private Boolean isRead = false;
}
