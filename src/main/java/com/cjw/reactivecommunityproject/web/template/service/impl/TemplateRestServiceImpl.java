package com.cjw.reactivecommunityproject.web.template.service.impl;

import com.cjw.reactivecommunityproject.web.template.mapper.TemplateRestMapper;
import com.cjw.reactivecommunityproject.web.template.service.TemplateRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateRestServiceImpl implements TemplateRestService {
    private final TemplateRestMapper templateRestMapper;
}
