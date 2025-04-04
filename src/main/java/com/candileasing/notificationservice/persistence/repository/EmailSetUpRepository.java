package com.candileasing.notificationservice.persistence.repository;

import com.candileasing.notificationservice.persistence.entity.EmailSetUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 28/02/2021
 * Time: 12:30 PM
 */
public interface EmailSetUpRepository extends JpaRepository<EmailSetUp, Long> {

    Optional<EmailSetUp> findByUsernameAndOrganization(String username, Long organization);

    Optional<EmailSetUp> findByOrganization(Long organization);
}
