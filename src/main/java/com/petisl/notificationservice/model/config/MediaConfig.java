package com.candileasing.notificationservice.model.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.io.Serializable;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 5:21 PM
 */
@ConfigurationProperties(prefix = "sftp.media")
@ConstructorBinding
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MediaConfig implements Serializable {

    private String host;
    private int port;
    private String username;
    private String password;
    private String uploadDir;
    private String path;
}
