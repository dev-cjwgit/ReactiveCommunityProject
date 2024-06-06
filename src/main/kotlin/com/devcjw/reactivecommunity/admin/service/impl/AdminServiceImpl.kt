package com.devcjw.reactivecommunity.admin.service.impl

import com.devcjw.reactivecommunity.admin.dao.AdminDAO
import com.devcjw.reactivecommunity.admin.service.AdminService
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AdminServiceImpl(
    private val adminDAO: AdminDAO
) : AdminService {
    private val logger = KotlinLogging.logger {}

}