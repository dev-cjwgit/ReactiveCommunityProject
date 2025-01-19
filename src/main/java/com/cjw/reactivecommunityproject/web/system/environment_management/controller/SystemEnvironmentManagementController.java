package com.cjw.reactivecommunityproject.web.system.environment_management.controller;

import com.cjw.reactivecommunityproject.web.system.environment_management.service.SystemEnvironmentManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/system/environment-management")
public class SystemEnvironmentManagementController {
    private final SystemEnvironmentManagementService systemEnvironmentManagementService;


}
