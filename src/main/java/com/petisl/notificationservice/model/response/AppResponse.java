package com.candileasing.notificationservice.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 9:56 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AppResponse<T> implements Serializable {

    private T data;
    private String message;
    private int status;
    @Builder.Default
    private Object meta = new HashMap<>();
}
