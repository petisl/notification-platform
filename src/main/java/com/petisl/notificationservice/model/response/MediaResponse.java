package com.candileasing.notificationservice.model.response;

import lombok.*;

import java.io.Serializable;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 5:26 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class MediaResponse implements Serializable {

    private Long id;
    private String fileName;
    private String contentType;
    private String fileType;
    private Long fileSize;

    public MediaResponse(Long id, String fileName, String contentType, String fileType, Long fileSize) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public MediaResponse(String fileName, String contentType, String fileType, Long fileSize) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
}
