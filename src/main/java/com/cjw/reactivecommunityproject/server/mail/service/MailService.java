package com.cjw.reactivecommunityproject.server.mail.service;

import com.cjw.reactivecommunityproject.server.mail.model.MailSendVO;

public interface MailService {
    void sendMail(MailSendVO mailSendVO);
}
