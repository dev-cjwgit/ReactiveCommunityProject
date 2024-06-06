package com.devcjw.reactivecommunity.admin.dao.impl

import com.devcjw.reactivecommunity.admin.dao.AdminDAO
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class AdminDAOImpl : AdminDAO {
    private val logger = KotlinLogging.logger {}

}