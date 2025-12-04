package com.candileasing.notificationservice.resource;

import com.candileasing.notificationservice.core.constants.AppConstant;
import com.candileasing.notificationservice.model.response.AppResponse;
import com.candileasing.notificationservice.model.response.PaginateResponse;
import com.candileasing.notificationservice.model.response.NotifyResponse;
import com.candileasing.notificationservice.service.NotifyService;
import com.candileasing.notificationservice.persistence.entity.NotifyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 9:54 PM
 */

@RestController
@RequestMapping("notify")
@RequiredArgsConstructor
@ComponentScan

public class NotifyResource {

    @Autowired
    private final NotifyService notifyService;

    @PostMapping("create")
    public ResponseEntity<AppResponse<NotifyModel>> createMailModel(@RequestBody NotifyResponse request) {
        NotifyModel response = notifyService.createNotify(request);
        return ResponseEntity.ok().body(AppResponse.<NotifyModel>builder()
                .message(AppConstant.RestMessage.success)
                .status(201)
                .data(response)
                .meta("")
                .build());
    }

    @GetMapping("view/{id}/{user}/{organization}")
    public ResponseEntity<AppResponse<NotifyModel>> fetchNotifyModel(@PathVariable("id") Long id, @PathVariable("user") Long user, @PathVariable Long organization) {
        NotifyModel response = notifyService.fetchNotifyModel(id, user, organization);
        return ResponseEntity.ok().body(AppResponse.<NotifyModel>builder()
                .message(AppConstant.RestMessage.success)
                .status(201)
                .data(response)
                .meta("")
                .build());

    }

    @GetMapping("view-all/{user}/{organization}")
    public ResponseEntity<AppResponse<List<NotifyModel>>> fetchNotifyModel(@PathVariable("user") Long user, @PathVariable Long organization) {
        List<NotifyModel> response = notifyService.fetchNotifyModel(user, organization);
        return ResponseEntity.ok().body(AppResponse.<List<NotifyModel>>builder()
                .message(AppConstant.RestMessage.success)
                .status(201)
                .data(response)
                .meta("")
                .build());

    }

    @GetMapping("view-all")
    public ResponseEntity<AppResponse<PaginateResponse<NotifyModel>>> fetchNotifyModel(@RequestParam(defaultValue = "0") int start,
                                                                                       @RequestParam(defaultValue = "10") int limit,
                                                                                       @RequestParam(required = false) String search) {
        PaginateResponse<NotifyModel> response = notifyService.fetchNotifyModel(start, limit, search);
        return ResponseEntity.ok().body(AppResponse.<PaginateResponse<NotifyModel>>builder()
                .message(AppConstant.RestMessage.success)
                .status(201)
                .data(response)
                .meta("")
                .build());

    }


}
