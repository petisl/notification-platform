package com.candileasing.notificationservice.service;

import com.candileasing.notificationservice.model.response.NotifyResponse;
import com.candileasing.notificationservice.model.response.PaginateResponse;
import com.candileasing.notificationservice.persistence.entity.NotifyModel;

import java.util.List;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 7:09 PM
 */
public interface NotifyService {

    public NotifyModel createNotify(NotifyResponse response);

    public NotifyModel fetchNotifyModel(Long id, Long user, Long organization);

    public List<NotifyModel> fetchNotifyModel(Long user, Long organization);

    public PaginateResponse<NotifyModel> fetchNotifyModel(int start, int limit, String search);
}
