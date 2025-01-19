package com.cjw.reactivecommunityproject.web.system.environment_management.service.impl;

import com.cjw.reactivecommunityproject.web.system.environment_management.dao.SystemEnvironmentManagementDao;
import com.cjw.reactivecommunityproject.web.system.environment_management.service.SystemEnvironmentManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemEnvironmentManagementServiceImpl implements SystemEnvironmentManagementService {
    private final SystemEnvironmentManagementDao systemEnvironmentManagementDao;
}
