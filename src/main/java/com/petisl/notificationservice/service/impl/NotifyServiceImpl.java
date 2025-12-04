package com.candileasing.notificationservice.service.impl;

import com.candileasing.notificationservice.model.response.PaginateResponse;
import com.candileasing.notificationservice.model.response.NotifyResponse;
import com.candileasing.notificationservice.persistence.entity.NotifyModel;
import com.candileasing.notificationservice.persistence.repository.NotifyModelRepository;
import com.candileasing.notificationservice.service.NotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotifyServiceImpl implements NotifyService {

    private final NotifyModelRepository notifyModelRepository;

    @Override
    public NotifyModel createNotify(NotifyResponse request) {
        return notifyModelRepository.save(NotifyModel.builder()
                .title(request.getTitle())
                .message(request.getMessage())
                .users(request.getUsers())
                .organization(request.getOrganization())
                .isRead(false)
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    @Override
    public NotifyModel fetchNotifyModel(Long id, Long user, Long organization) {
        return notifyModelRepository.findByIdAndUsersAndOrganization(id, user, organization);
    }

    @Override
    public List<NotifyModel> fetchNotifyModel(Long user, Long organization) {
        return notifyModelRepository.findByUsersAndOrganization(user, organization);
    }

    @Override
    public PaginateResponse<NotifyModel> fetchNotifyModel(int start, int limit, String search) {
        /*Flux<NotifyModel> response = template.select(NotifyModel.class)
                .matching(query(where("user").like("%" + search + "%")
                        .or("organization").is(Long.valueOf(search))
                        .or("isRead").is(Boolean.valueOf(search)))
                        .limit(limit)
                        .offset(start))
                .all();*/
        return null;
    }
}
