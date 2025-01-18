package com.cjw.reactivecommunityproject.web.template.service.impl;

import com.cjw.reactivecommunityproject.web.template.mapper.TemplateMapper;
import com.cjw.reactivecommunityproject.web.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateMapper templateMapper;
}
