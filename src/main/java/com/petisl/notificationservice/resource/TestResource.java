package com.candileasing.notificationservice.resource;

import com.candileasing.notificationservice.core.utils.AppUtils;
import com.candileasing.notificationservice.model.CustomPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Project title: gateway-server
 * Created by john.adeshola
 * Date: 24/02/2021
 * Time: 5:02 PM
 */
@RestController
@RequestMapping("test")
public class TestResource {

    @GetMapping("auths")
    public ResponseEntity<?> fetchAuths() {
        Map<String, ?> token = AppUtils.getToken();
        CustomPrincipal principal = AppUtils.fromJson(token, CustomPrincipal.class);
        return ResponseEntity.ok(principal);
    }


    @GetMapping("auth")
    public ResponseEntity<?> fetchAuth() {
        return ResponseEntity.ok(AppUtils.getToken());
    }
}
