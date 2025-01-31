package com.cjw.reactivecommunityproject.web.template.dao.impl;

import com.cjw.reactivecommunityproject.web.template.dao.TemplateDao;
import com.cjw.reactivecommunityproject.web.template.mapper.TemplateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateDaoImpl implements TemplateDao {
    private final TemplateMapper templateMapper;
}
