package com.cjw.reactivecommunityproject.web.system.environment_management.dao.impl;

import com.cjw.reactivecommunityproject.web.system.environment_management.dao.SystemEnvironmentManagementDao;
import com.cjw.reactivecommunityproject.web.system.environment_management.mapper.SystemEnvironmentManagementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemEnvironmentManagementDaoImpl implements SystemEnvironmentManagementDao {
    private final SystemEnvironmentManagementMapper systemEnvironmentManagementMapper;

}
