package com.candileasing.notificationservice.resource;

import com.candileasing.notificationservice.core.messaging.rabbitmq.RabbitmqProducerService;
import com.candileasing.notificationservice.model.request.MailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationResource {

    private final RabbitmqProducerService producer;

    private static final String platform = "CAndILeasing HR";
    @Value("${spring.mail.username}")
    private String sender;

    private String baseUrl= "https://hydrogenhr.com";

    @PostMapping()
    public boolean send(@RequestParam String emailTo){
        String email = "ekundayorotimi@gmail.com";
        email = "mickyp74@aol.com";
        // String email = "michael@ashermanprice.com";
        sendVerificationMail(emailTo);
        return true;
    }

    public void sendVerificationMail(String email) {

        String url = baseUrl + "/user/" ;
        MailRequest model = new MailRequest();
        model.setFrom(sender);
        model.setTo(new String[]{email});
        model.setSubject("Verification Mail");
        model.setTemplateName("account_verification");
        model.setType("html");
        model.setUseTemplate(true);

        Map<String, Object> mapper = new HashMap<>();
        mapper.put("url", url);
        mapper.put("platform", platform);
        model.setMessageMap(mapper);

        // This is your logging statement
        log.info("MailModel prepared: {}", model);

        // simulate sending email
        log.info("about to send email message for: {}", email);

        try{
            producer.sendEmail(model);
        }catch (Exception e){
            e.printStackTrace();
        }
        producer.sendEmail(model);

    }
}
