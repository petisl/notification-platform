package com.candileasing.notificationservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:21 PM
 */
@Getter
@AllArgsConstructor
public enum AppStatus {
    ACTIVE('0'),
    INACTIVE('1'),
    DELETED('2'),
    PENDING('3'),
    APPROVED('4'),
    REJECT('5'),
    CANCEL('6'),
    LOCKED('7');

    private Character status;
}
