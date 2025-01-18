package com.cjw.reactivecommunityproject.web.template.controller;

import com.cjw.reactivecommunityproject.web.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/template")
public class TemplateController {
    private final TemplateService templateService;
}
