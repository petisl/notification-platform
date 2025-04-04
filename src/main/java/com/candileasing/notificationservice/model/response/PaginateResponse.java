package com.candileasing.notificationservice.model.response;

import lombok.*;

import java.io.Serializable;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 9:57 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaginateResponse<T> implements Serializable {
    private T content;
    private long totalElements;
}
