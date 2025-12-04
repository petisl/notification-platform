package com.candileasing.notificationservice.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 7:11 PM
 */
@Entity
@Table(name = "notify_model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class NotifyModel extends BaseEntity implements Serializable {

    @Column(name = "title", length = 120)
    private String title;

    @Column(name = "message", length = 2000)
    private String message;

    @Column(name = "users", length = 12)
    private Long users;

    @Column(name = "organization_fk", length = 12)
    private Long organization;

    @Column(name = "is_read", length = 1)
    private Boolean isRead;
}
