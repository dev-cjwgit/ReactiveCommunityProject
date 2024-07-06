package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.dao.BbsDAO
import com.devcjw.reactivecommunity.board.model.domain.RepBbsListVO
import com.devcjw.reactivecommunity.board.service.BbsService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
@RequiredArgsConstructor
class BbsServiceImpl(
    private val bbsDAO: BbsDAO
) : BbsService {
    private val logger = KotlinLogging.logger {}
    override fun list(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepBbsListVO>> {
        return bbsDAO.list()
            .map { RepBbsListVO(it.uid, it.path, it.title) }
            .map { RestResponseVO(result = true, data = it) }
    }

}