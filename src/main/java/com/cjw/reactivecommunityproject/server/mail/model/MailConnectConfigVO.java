package com.cjw.reactivecommunityproject.server.mail.model;

import lombok.Builder;

@Builder
public record MailConnectConfigVO(
        String hostUri,
        Integer port,
        String name,
        String password,
        String auth,
        String startTlsEnable,
        String startTleRequired

) {
}
