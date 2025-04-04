package com.candileasing.notificationservice.core.exceptions;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 26/02/2021
 * Time: 9:46 AM
 */
public class CustomException extends RuntimeException {

    private String message;
    private int code;
    private String desc;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, String desc) {
        super(message);
        this.desc = desc;
    }

    public CustomException(String message, String desc, int code) {
        super("Message" + message.concat(" Description: " + desc.concat(" Status Code: " + code)));
        this.desc = desc;
        this.code = code;
    }

}
