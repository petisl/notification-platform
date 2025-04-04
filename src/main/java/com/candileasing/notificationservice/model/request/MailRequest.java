package com.candileasing.notificationservice.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:56 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MailRequest implements Serializable {

    private String subject;

    private String from;

    private String to[] = new String[0];

    private String bcc[] = new String[0];

    private String cc[] = new String[0];

    private String message;

    @Builder.Default
    private String type = "html";

    private Map<String, Object> attachedFiles;

    @Builder.Default
    private Locale locale = Locale.ENGLISH;

    private String templateName;

    @Builder.Default
    private boolean hasAttachment = false;

    @Builder.Default
    private boolean useTemplate = false;

    @Builder.Default
    private Boolean hasUrlLocation = false;

    private Map<String, Object> messageMap;

    private byte[] attachmentBytes;

    private String attachmentContentType;

    private String attachmentFileName;

    private String description;

    private String url;

}
