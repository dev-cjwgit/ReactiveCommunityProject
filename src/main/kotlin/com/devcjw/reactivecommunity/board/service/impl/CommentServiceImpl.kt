package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.dao.CommentDAO
import com.devcjw.reactivecommunity.board.model.domain.CommentRepListVO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqUpdateDTO
import com.devcjw.reactivecommunity.board.model.entity.CommentInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.CommentUpdateDTO
import com.devcjw.reactivecommunity.board.service.CommentService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class CommentServiceImpl(
    private val boardDAO: BoardDAO,
    private val commentDAO: CommentDAO,
) : CommentService {
    override fun list(rcUser: RcUserJwtClaims, boardUid: Long): Flux<RestResponseVO<CommentRepListVO>> {
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
                CommentRepListVO(it.uid, it.contents, it.createdAt, it.updatedAt)
            }
            .map {
                RestResponseVO(
                    result = true,
                    data = it
                )
            }
    }

    override fun insert(rcUser: RcUserJwtClaims, commentReqInsertDTO: CommentReqInsertDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. Board 존재 여부
         * 2. 엔티티 생성
         * 3. 댓글 등록
         */
        return boardDAO.isBoardUid(commentReqInsertDTO.boardUid)
            // 1
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
            // 2
            .map {
                CommentInsertDTO(
                    commentReqInsertDTO.boardUid,
                    rcUser.uid,
                    commentReqInsertDTO.contents
                )
            }
            // 3
            .flatMap { commentDAO.insert(it) }
            .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun update(rcUser: RcUserJwtClaims, commentReqUpdateDTO: CommentReqUpdateDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. 댓글 존재 확인
         * 2. 작성자가 맞는지 확인
         * 3. 업데이트 수행
         */
        return commentDAO.isCommentUid(commentReqUpdateDTO.uid)
            // 1
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_COMMENT_EXCEPTION)))
            // 2
            .filterWhen { commentDAO.isWriterComment(commentReqUpdateDTO.uid, rcUser.uid) }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))

            .map {
                CommentUpdateDTO(
                    commentReqUpdateDTO.uid,
                    commentReqUpdateDTO.contents
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