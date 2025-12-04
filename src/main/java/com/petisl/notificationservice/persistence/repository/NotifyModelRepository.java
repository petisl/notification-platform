package com.candileasing.notificationservice.persistence.repository;

import com.candileasing.notificationservice.persistence.entity.NotifyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 12:30 PM
 */
public interface NotifyModelRepository extends JpaRepository<NotifyModel, Long> {

    NotifyModel findByIdAndUsersAndOrganization(Long id, Long user, Long organization);

    List<NotifyModel> findByUsersAndOrganization(Long user, Long organization);

    NotifyModel findByOrganization(Long organization);
}
