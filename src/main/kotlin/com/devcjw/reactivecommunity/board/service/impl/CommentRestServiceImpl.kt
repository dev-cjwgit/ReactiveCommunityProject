package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.dao.CommentDAO
import com.devcjw.reactivecommunity.board.model.domain.RepCommentListVO
import com.devcjw.reactivecommunity.board.model.domain.ReqCommentInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.ReqCommentUpdateVO
import com.devcjw.reactivecommunity.board.model.entity.InCommentUpdateVO
import com.devcjw.reactivecommunity.board.model.entity.InCommentInsertVO
import com.devcjw.reactivecommunity.board.service.CommentRestService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class CommentRestServiceImpl(
    private val boardDAO: BoardDAO,
    private val commentDAO: CommentDAO,
) : CommentRestService {
    override fun list(rcUser: RcUserJwtClaims, boardUid: Long): Flux<RestResponseVO<RepCommentListVO>> {
        /**
         * 1. 게시글 존재 확인
         * 2. 댓글 목록 불러오기
         */
        return boardDAO.isBoardUid(boardUid)
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
            .flatMapMany {
                commentDAO.selectList(boardUid)
            }
            .map {
                RepCommentListVO(it.uid, it.contents, it.createdUtcAt, it.updatedUtcAt)
            }
            .map {
                RestResponseVO(
                    result = true,
                    data = it
                )
            }
    }

    override fun insert(rcUser: RcUserJwtClaims, reqCommentInsertDTO: ReqCommentInsertDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. Board 존재 여부
         * 2. 엔티티 생성
         * 3. 댓글 등록
         */
        return boardDAO.isBoardUid(reqCommentInsertDTO.boardUid)
            // 1
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
            // 2
            .map {
                InCommentInsertVO(
                    reqCommentInsertDTO.boardUid,
                    rcUser.uid,
                    reqCommentInsertDTO.contents
                )
            }
            // 3
            .flatMap { commentDAO.insert(it) }
            .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun update(rcUser: RcUserJwtClaims, reqCommentUpdateVO: ReqCommentUpdateVO): Mono<RestResponseVO<Void>> {
        /**
         * 1. 댓글 존재 확인
         * 2. 작성자가 맞는지 확인
         * 3. 업데이트 수행
         */
        return commentDAO.isCommentUid(reqCommentUpdateVO.uid)
            // 1
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_COMMENT_EXCEPTION)))
            // 2
            .filterWhen { commentDAO.isWriterComment(reqCommentUpdateVO.uid, rcUser.uid) }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))

            .map {
                InCommentUpdateVO(
                    reqCommentUpdateVO.uid,
                    reqCommentUpdateVO.contents
                )
            }
            // 4
            .flatMap {
                commentDAO.update(it)
            }

            .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun delete(rcUser: RcUserJwtClaims, uid: Long): Mono<RestResponseVO<Void>> {
        /**
         * 1. 댓글 존재 확인
         * 2. 작성자가 맞는지 확인
         * 3. 삭제 수행
         */
        return commentDAO.isCommentUid(uid)
            // 1
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_COMMENT_EXCEPTION)))
            // 2
            .filterWhen { commentDAO.isWriterComment(uid, rcUser.uid) }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))

            // 3
            .flatMap {
                commentDAO.delete(uid)
            }

            .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }
}