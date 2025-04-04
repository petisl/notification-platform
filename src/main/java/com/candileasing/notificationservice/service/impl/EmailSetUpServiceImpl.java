package com.candileasing.notificationservice.service.impl;

import com.candileasing.notificationservice.core.constants.MailConstant;
import com.candileasing.notificationservice.core.exceptions.CustomException;
import com.candileasing.notificationservice.core.messaging.rabbitmq.RabbitmqProducerService;
import com.candileasing.notificationservice.model.request.CustomEmailSetUp;
import com.candileasing.notificationservice.model.request.MailRequest;
import com.candileasing.notificationservice.model.response.PaginateResponse;
import com.candileasing.notificationservice.config.MailConfig;
import com.candileasing.notificationservice.persistence.entity.EmailSetUp;
import com.candileasing.notificationservice.persistence.repository.EmailSetUpRepository;
import com.candileasing.notificationservice.service.EmailSetUpService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailSetUpServiceImpl implements EmailSetUpService {

    private final Environment env;
    private final EmailSetUpRepository setUpRepository;
    private final RabbitmqProducerService producerService;

    @Override
    public EmailSetUp createEmailSetup(CustomEmailSetUp request) {

        setUpRepository.findByUsernameAndOrganization(request.getUsername(), request.getOrganization()).ifPresent(data -> {
            if (data != null) {
                throw new CustomException("Email has been previously configured");
            }
        });
        return setUpRepository.save(EmailSetUp.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .host(request.getHost())
                .port(request.getPort())
                .protocol(request.getProtocol())
                .smtpAuth(request.getSmtpAuth())
                .starttlsEnabled(request.getStarttlsEnabled())
                .starttlsRequired(request.getStarttlsRequired())
                .primarySender(request.getPrimarySender())
                .secondarySender(request.getSecondarySender())
                .organization(request.getOrganization())
                .build());
    }

    @Override
    public EmailSetUp updateEmailSetup(CustomEmailSetUp request) {

        setUpRepository.findByUsernameAndOrganization(request.getUsername(), request.getOrganization()).map(data -> {
            if (data == null) {
                throw new CustomException("Email setup cannot be found on the system");
            }
            if (!StringUtils.isBlank(request.getUsername())) {
                data.setUsername(request.getUsername());
            }
            if (!StringUtils.isBlank(request.getPassword())) {
                data.setPassword(request.getPassword());
            }
            if (!StringUtils.isBlank(request.getHost())) {
                data.setHost(request.getHost());
            }
            if (Objects.nonNull(request.getPort())) {
                data.setPort(request.getPort());
            }
            if (Objects.nonNull(request.getProtocol())) {
                data.setProtocol(request.getProtocol());
            }
            if (Objects.nonNull(request.getStarttlsEnabled())) {
                data.setStarttlsEnabled(request.getStarttlsEnabled());
            }
            if (Objects.nonNull(request.getStarttlsRequired())) {
                data.setStarttlsRequired(request.getStarttlsRequired());
            }
            if (Objects.nonNull(request.getSmtpAuth())) {
                data.setSmtpAuth(request.getSmtpAuth());
            }
            if (Objects.nonNull(request.getPrimarySender())) {
                data.setPrimarySender(request.getPrimarySender());
            }
            if (Objects.nonNull(request.getSecondarySender())) {
                data.setSecondarySender(request.getSecondarySender());
            }
            return setUpRepository.save(data);
        });
        return null;
    }

    @Override
    public EmailSetUp fetchEmailSetup(Long organization) {
        return setUpRepository.findByOrganization(organization).orElseThrow(() -> new CustomException("Email setup cannot be found"));
    }

    @Override
    public PaginateResponse<EmailSetUp> fetchEmailSetup(int start, int limit, String search) {
        return null;
    }

    @Override
    public JavaMailSender runtimeJavaMailService() {
        MailConfig mailConfig = new MailConfig();
        return mailConfig.defaultJavaMailSender(env);
    }

//    @Override
//    public Boolean testEmail(String templateName, String[] recipients, Long orgId) {
//
//        Set<String> a_to_j = Sets.newHashSet("hydrogen_hr_add_admin_absence_succession",
////                "add_admin_birthday_anniversary",
//                "hydrogen_hr_add_admin_challenge",
//                "hydrogen_hr_add_admin_challenge_comment_resolved",
//                "hydrogen_hr_add_admin_challenge_resolved",
//                "hydrogen_hr_add_admin_complaint",
//                "hydrogen_hr_add_admin_complaint_comment",
//                "hydrogen_hr_add_admin_deactivation_comment",
//                "hydrogen_hr_add_admin_employee_absence",
//                "hydrogen_hr_add_admin_employee_deactivation",
//                "hydrogen_hr_add_admin_employee_leave",
//                "hydrogen_hr_add_admin_employee_reactivation",
//                "hydrogen_hr_add_admin_event",
//                "hydrogen_hr_add_admin_event_comment",
//                "hydrogen_hr_add_admin_expense_reimbursement",
//                "hydrogen_hr_add_admin_expense_reimbursement_approval",
////                "hydrogen_hr_add_admin_hire_date_anniversary",
//                "hydrogen_hr_add_admin_information",
//                "hydrogen_hr_add_admin_information_comment",
//                "hydrogen_hr_add_admin_job_requisition",
//                "hydrogen_hr_add_admin_job_requisition_comment",
//                "hydrogen_hr_add_admin_last_day_leave_schedule",
//                "hydrogen_hr_add_admin_leave_approval",
//                "hydrogen_hr_add_admin_leave_extra_day",
//                "hydrogen_hr_add_admin_leave_rollover_day",
//                "hydrogen_hr_add_admin_leave_schedule",
//                "hydrogen_hr_add_admin_leave_succession",
//                "hydrogen_hr_add_admin_main_absence",
//                "hydrogen_hr_add_admin_main_leave",
//                "hydrogen_hr_add_admin_main_resignation",
//                "hydrogen_hr_add_admin_memo",
//                "hydrogen_hr_add_admin_memo_comment",
//                "hydrogen_hr_add_admin_reactivation_comment",
//                "hydrogen_hr_add_admin_resignation_approval",
//                "hydrogen_hr_add_admin_resignation_request",
//                "hydrogen_hr_add_admin_resignation_succession",
////                "hydrogen_hr_add_admin_weekly_leave_schedule",
//                "hydrogen_hr_add_complaint_comment",
//                "hydrogen_hr_add_creator_complaint_comment",
//                "hydrogen_hr_add_creator_deactivation_comment",
//                "hydrogen_hr_add_creator_event_comment",
//                "hydrogen_hr_add_creator_information_comment",
//                "hydrogen_hr_add_creator_job_requisition_comment",
//                "hydrogen_hr_add_creator_memo_comment",
//                "hydrogen_hr_add_creator_reactivation_comment",
//                "hydrogen_hr_add_employee_absence",
//                "hydrogen_hr_add_employee_absence_succession",
////                "hydrogen_hr_add_employee_birthday_anniversary",
//                "hydrogen_hr_add_employee_birthday_state",
//                "hydrogen_hr_add_employee_challenge",
//                "hydrogen_hr_add_employee_challenge_comment_resolved",
//                "hydrogen_hr_add_employee_commendation",
//                "hydrogen_hr_add_employee_complaint",
//                "hydrogen_hr_add_employee_created_meeting",
//                "hydrogen_hr_add_employee_deactivation",
//                "hydrogen_hr_add_employee_deactivation_comment",
//                "hydrogen_hr_add_employee_expense_reimbursement",
//                "hydrogen_hr_add_employee_expense_reimbursement_approval",
////                "hydrogen_hr_add_employee_hire_date_anniversary",
//                "hydrogen_hr_add_employee_last_day_leave_schedule",
//                "add_employee_leave",
//                "hydrogen_hr_add_employee_leave_approval",
//                "hydrogen_hr_add_employee_leave_extra_day",
//                "hydrogen_hr_add_employee_leave_rollover_day",
//                "hydrogen_hr_add_employee_leave_succession",
//                "hydrogen_hr_add_employee_main_absence",
//                "hydrogen_hr_add_employee_meeting",
//                "hydrogen_hr_add_employee_reactivation",
//                "hydrogen_hr_add_employee_reactivation_comment",
//                "hydrogen_hr_add_employee_recommendation",
//                "hydrogen_hr_add_employee_reminder",
//                "hydrogen_hr_add_employee_resignation",
//                "hydrogen_hr_add_employee_resignation_approval",
//                "hydrogen_hr_add_employee_resignation_succession",
//                "hydrogen_hr_add_employee_self_created_meeting",
//                "hydrogen_hr_add_employee_self_update_meeting",
//                "hydrogen_hr_add_employee_support",
//                "hydrogen_hr_add_employee_updated_meeting",
//                "hydrogen_hr_add_employee_weekly_leave_schedule",
//                "hydrogen_hr_add_event",
//                "hydrogen_hr_add_event_comment",
//                "hydrogen_hr_add_guarantor_checked",
//                "hydrogen_hr_add_information",
//                "hydrogen_hr_add_information_comment",
//                "hydrogen_hr_add_job_requisition",
//                "hydrogen_hr_add_job_requisition_comment",
//                "hydrogen_hr_add_leave_schedule",
//                "hydrogen_hr_add_memo_comment",
//                "hydrogen_hr_add_memo_email",
//                "hydrogen_hr_add_previous_employer",
//                "hydrogen_hr_add_recipient_commendation",
//                "hydrogen_hr_add_recipient_leave_extra_day",
//                "hydrogen_hr_add_recipient_leave_rollover_day",
//                "hydrogen_hr_add_recipient_recommendation",
//                "hydrogen_hr_add_reference_checked",
//                "hydrogen_hr_add_self_employee_meeting",
//                "hydrogen_hr_add_self_employee_updated_meeting",
//                "hydrogen_hr_add_support_hydrogen",
//                "hydrogen_hr_add_tagged_challenge",
//                "hydrogen_hr_add_team_member",
//                "hydrogen_hr_add_whistleblow",
//                "hydrogen_hr_admin_info_comment",
//                "hydrogen_hr_demo_request_customer",
//                "hydrogen_hr_demo_request_hydrogen",
//                "hydrogen_hr_demo_resend_activation_code_hydrogen",
//                "hydrogen_hr_demo_verify_activation_code_hydrogen",
//                "hydrogen_hr_employee_onboarding",
//                "hydrogen_hr_employment_status",
//                "hydrogen_hr_forgot_password",
//                "hydrogen_hr_guarantor_check",
//                "hydrogen_hr_info_comment");
//
//        System.out.println("A to J size: " + a_to_j.size());
//
//
//        Map<String, Object> messageMap = new HashMap<>();
//
//        messageMap.put("ticketNumber","${ticketNumber}");
//        messageMap.put("employeeFirstName","${employeeFirstName}");
//        messageMap.put("employeeSurname","${employeeSurname}");
//        messageMap.put("startDate","${startDate}");
//        messageMap.put("endDate","${endDate}");
//        messageMap.put("url","${url}");
//        messageMap.put("employeeEmail","${employeeEmail}");
//        messageMap.put("companyName","${companyName}");
//        messageMap.put("companyCode","${companyCode}");
//        messageMap.put("firstName","${firstName}");
//        messageMap.put("eventGroup","${eventGroup}");
//        messageMap.put("status","${status}");
//        messageMap.put("creatorFirstName","${creatorFirstName}");
//        messageMap.put("creatorSurname","${creatorSurname}");
//        messageMap.put("informationGroup","${informationGroup}");
//        messageMap.put("commendedEmployeeFirstName","${commendedEmployeeFirstName}");
//        messageMap.put("commendedEmployeeSurname","${commendedEmployeeSurname}");
//        messageMap.put("contactPersonName","${contactPersonName}");
//        messageMap.put("startTime","${startTime}");
//        messageMap.put("expectedEndDate","${expectedEndDate}");
//        messageMap.put("deactivatedEmployeeFirstName","${deactivatedEmployeeFirstName}");
//        messageMap.put("deactivatedEmployeeSurname","${deactivatedEmployeeSurname}");
//        messageMap.put("recommendedEmployeeFirstName","${recommendedEmployeeFirstName}");
//        messageMap.put("recommendedEmployeeSurname","${recommendedEmployeeSurname}");
//        messageMap.put("employees","${employees}");
//        messageMap.put("employeeLeaveSchedule","${employeeLeaveSchedule}");
//        messageMap.put("reactivatedEmployeeFirstName","${reactivatedEmployeeFirstName}");
//        messageMap.put("reactivatedEmployeeSurname","${reactivatedEmployeeSurname}");
//        messageMap.put("reminderHint","${reminderHint}");
//        messageMap.put("reminderTime","${reminderTime}");
//        messageMap.put("lastDateOfEngagement","${lastDateOfEngagement}");
//        messageMap.put("meetingType","${meetingType}");
//        messageMap.put("title","${title}");
//        messageMap.put("creatorLastName","${creatorLastName}");
//        messageMap.put("meetingStartDate","${meetingStartDate}");
//        messageMap.put("meetingStartTime","${meetingStartTime}");
//        messageMap.put("meetingEndDate","${meetingEndDate}");
//        messageMap.put("meetingEndTime","${meetingEndTime}");
//        messageMap.put("numberOfDays","${numberOfDays}");
//        messageMap.put("leaveType","${leaveType}");
//        messageMap.put("employeeLeaveSchedules","${employeeLeaveSchedules}");
//
//        for (String t : a_to_j) {
//
//            producerService.sendEmail(
//
//                    MailRequest.builder()
//                    .from("support@hydrogenhr.com")
//                            .subject(t.toLowerCase().replaceAll("_", " "))
//                    .useTemplate(true)
//                    .messageMap(messageMap)
//                            .to(recipients)
//                    .templateName(t)
//                    .type(MailConstant.EmailTemplate.HTML)
//                    .build()
//            );
//        }
//
//        return true;
//    }

    @Override
    public Boolean testEmail(String templateName, String[] recipients, Long orgId) {
        producerService.sendEmail(MailRequest.builder()
                .from("support@hydrogenhr.com")
                .to(recipients)
                .subject(templateName.toLowerCase().replaceAll("_", " "))
                .useTemplate(true)
                .templateName(templateName.toLowerCase())
                .type(MailConstant.EmailTemplate.HTML)
                .build());
        return true;
    }

    @Override
    public Boolean sendEmailNotification(String[] to, String templateName) {

        producerService.sendEmail(MailRequest.builder()
                .from("support@hydrogenhr.com")
                .to(to)
                .subject(templateName.toLowerCase().replaceAll("_", " "))
                .useTemplate(true)
                .messageMap(new HashMap<>())
                .templateName(templateName.toLowerCase())
                .type(MailConstant.EmailTemplate.HTML)
                .build());
        return true;
    }
}
