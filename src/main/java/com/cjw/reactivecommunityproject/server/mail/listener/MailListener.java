package com.cjw.reactivecommunityproject.server.mail.listener;

import com.cjw.reactivecommunityproject.server.mail.service.MailService;
import com.cjw.reactivecommunityproject.server.mail.model.MailSendVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailListener {
    private final MailService mailService;

    @Async
    @EventListener(MailSendVO.class)
    public void listener(MailSendVO mailSendVO) {
        log.info("MailListener.listener() : start");
        mailService.sendMail(mailSendVO);
        log.info("MailListener.listener() : end");
    }
}
