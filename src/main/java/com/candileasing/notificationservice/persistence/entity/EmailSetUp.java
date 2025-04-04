package com.candileasing.notificationservice.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 9:00 PM
 */
@Entity
@Table(name = "email_setup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class EmailSetUp extends BaseEntity implements Serializable {

    @Column(name = "username", length = 150)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "host", length = 80)
    private String host;

    @Column(name = "port", length = 5)
    private Integer port;

    @Column(name = "protocol", length = 5)
    private String protocol;

    @Column(name = "smtp_auth", length = 1)
    private Boolean smtpAuth;

    @Column(name = "starttls_enabled", length = 1)
    private Boolean starttlsEnabled;

    @Column(name = "starttls_required", length = 1)
    private Boolean starttlsRequired;

    @Column(name = "primary_sender", length = 120)
    private String primarySender;

    @Column(name = "secondary_sender", length = 120)
    private String secondarySender;

    @Column(name = "organization_fk", length = 12)
    private Long organization;

}
