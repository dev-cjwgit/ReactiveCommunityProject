package com.cjw.reactivecommunityproject.server.mail.service.impl;

import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.util.EnvCodeUtils;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.server.mail.exception.MailException;
import com.cjw.reactivecommunityproject.server.mail.model.MailConnectConfigVO;
import com.cjw.reactivecommunityproject.server.mail.model.MailSendVO;
import com.cjw.reactivecommunityproject.server.mail.service.MailService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final CacheCustomService cacheCustomService;

    private <T> T getConfigValueByCode(List<CacheCustomEnvCodeVO> configEnvCodeList, String envId, Class<T> clazz) {
        return CollectionUtils.emptyIfNull(configEnvCodeList).stream()
                .filter(o -> Strings.CI.equals(o.getId(), envId))
                .map(o -> EnvCodeUtils.convertEnvCodeByValue(o, clazz))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new MailException(RcCommonErrorMessage.NOT_FOUND_ENV_CODE));
    }

    private MailConnectConfigVO getMailConfig() {
        var configEnvCodeList = cacheCustomService.getCommonEnvCodeByCategoryList("mail.config");
        var hostUri = getConfigValueByCode(configEnvCodeList, "host.uri", String.class);
        var port = getConfigValueByCode(configEnvCodeList, "port", Integer.class);
        var name = getConfigValueByCode(configEnvCodeList, "name", String.class);
        var password = getConfigValueByCode(configEnvCodeList, "password", String.class);

        var auth = getConfigValueByCode(configEnvCodeList, "auth", String.class);
        var startTlsEnable = getConfigValueByCode(configEnvCodeList, "start.tls.enable", String.class);
        var startTlsRequired = getConfigValueByCode(configEnvCodeList, "start.tls.required", String.class);

        return MailConnectConfigVO.builder()
                .hostUri(hostUri)
                .port(port)
                .name(name)
                .password(password)
                .auth(auth)
                .startTlsEnable(startTlsEnable)
                .startTleRequired(startTlsRequired)
                .build();
    }

    @Override
    public void sendMail(MailSendVO mailSendVO) {
        var mail = new JavaMailSenderImpl();
        var config = getMailConfig();

        mail.setHost(config.hostUri());
        mail.setPort(config.port());

        mail.setUsername(config.name());
        mail.setPassword(config.password());

        var props = mail.getJavaMailProperties();
        props.put("mail.smtp.auth", config.auth());
        props.put("mail.smtp.starttls.enable", config.startTlsEnable());
        props.put("mail.smtp.starttls.required", config.startTleRequired());

        var mailTemplate = new SimpleMailMessage();
        mailTemplate.setFrom(mailSendVO.from());
        mailTemplate.setTo(mailSendVO.to());
        mailTemplate.setSubject(mailSendVO.title());
        mailTemplate.setText(mailSendVO.body());

        mail.send(mailTemplate);
    }
}
