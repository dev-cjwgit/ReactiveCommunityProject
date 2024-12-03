package com.cjw.reactivecommunityproject.server.mail.model;

import lombok.Builder;

@Builder
public record MailSendVO(
        String from, // 보내는 사람
        String to, // 받는 사람
        String title, // 제목
        String body // 내용
) {
}
