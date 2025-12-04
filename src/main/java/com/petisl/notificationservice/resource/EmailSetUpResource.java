package com.candileasing.notificationservice.resource;

import com.candileasing.notificationservice.core.constants.AppConstant;
import com.candileasing.notificationservice.model.request.CustomEmailSetUp;
import com.candileasing.notificationservice.model.response.AppResponse;
import com.candileasing.notificationservice.model.response.PaginateResponse;
import com.candileasing.notificationservice.service.EmailSetUpService;
import com.candileasing.notificationservice.persistence.entity.EmailSetUp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 9:54 PM
 */
@RestController
@RequestMapping("email-setup")
@RequiredArgsConstructor
public class EmailSetUpResource {

    private final EmailSetUpService emailSetUpService;

    @PostMapping("create")
    public ResponseEntity<AppResponse<EmailSetUp>> createEmailSetup(@RequestBody CustomEmailSetUp request) {
        EmailSetUp response = emailSetUpService.createEmailSetup(request);
        return ResponseEntity.ok().body(AppResponse.<EmailSetUp>builder()
                .message(AppConstant.RestMessage.success)
                .status(201)
                .data(response)
                .meta("")
                .build());
    }

    @PutMapping("update")
    public ResponseEntity<AppResponse<EmailSetUp>> updateEmailSetup(@RequestBody CustomEmailSetUp request) {
        EmailSetUp response = emailSetUpService.updateEmailSetup(request);
        return ResponseEntity.ok().body(AppResponse.<EmailSetUp>builder()
                .message(AppConstant.RestMessage.success)
                .status(200)
                .data(response)
                .meta("")
                .build());
    }

    @GetMapping("view/{organization}")
    public ResponseEntity<AppResponse<EmailSetUp>> fetchEmailSetup(@PathVariable Long organization) {
        EmailSetUp response = emailSetUpService.fetchEmailSetup(organization);
        return ResponseEntity.ok().body(AppResponse.<EmailSetUp>builder()
                .message(AppConstant.RestMessage.success)
                .status(200)
                .data(response)
                .meta("")
                .build());
    }

    @GetMapping("view-all")
    public ResponseEntity<AppResponse<PaginateResponse<EmailSetUp>>> fetchEmailSetup(@RequestParam(defaultValue = "0") int start,
                                                                                     @RequestParam(defaultValue = "10") int limit,
                                                                                     @RequestParam(required = false) String search) {
        PaginateResponse<EmailSetUp> response = emailSetUpService.fetchEmailSetup(start, limit, search);
        return ResponseEntity.ok().body(AppResponse.<PaginateResponse<EmailSetUp>>builder()
                .message(AppConstant.RestMessage.success)
                .status(200)
                .data(response)
                .meta("")
                .build());
    }

    @PostMapping("test")
    public ResponseEntity<AppResponse<Boolean>> testMailSetUp(
            @RequestParam(name = "template-name")String templateName,
            @RequestParam(name="to")String[] to) {

        Boolean response = emailSetUpService.testEmail(templateName, to,0L);
        return ResponseEntity.ok().body(AppResponse.<Boolean>builder()
                .message(AppConstant.RestMessage.success)
                .status(200)
                .data(response)
                .meta("")
                .build());
    }

    @PostMapping("send-email-notification")
    public ResponseEntity<AppResponse<Boolean>> sendEmailNotification(
            @RequestParam(name = "template-name") String templateName,
            @RequestParam(name="to") String[] to) {

        Boolean response = emailSetUpService.sendEmailNotification(to, templateName);
        return ResponseEntity.ok().body(AppResponse.<Boolean>builder()
                .message(AppConstant.RestMessage.success)
                .status(200)
                .data(response)
                .meta("")
                .build());
    }
}
