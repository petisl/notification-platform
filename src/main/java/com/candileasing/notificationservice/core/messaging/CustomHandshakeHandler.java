package com.candileasing.notificationservice.core.messaging;

import com.candileasing.notificationservice.model.response.StompPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:28 PM
 */
@Service
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
//        return new StompPrincipal(AppUtils.getPrincipal().getUsername());
        return new StompPrincipal("timadeshola");
    }
}
