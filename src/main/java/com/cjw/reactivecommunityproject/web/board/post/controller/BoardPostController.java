package com.cjw.reactivecommunityproject.web.board.post.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.board.post.model.entity.BoardPostListEntity;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostCreateVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostListVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostModifyVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostRecommendVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostReplyVO;
import com.cjw.reactivecommunityproject.web.board.post.service.BoardPostService;
import com.cjw.reactivecommunityproject.web.board.post.validation.BoardPostValidationGroup;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/board/{bbs}")
public class BoardPostController {
    private final BoardPostService boardPostService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<BoardPostListEntity>>> list(
            @PathVariable("bbs") String bbs
            , @RequestParam(value = "title", required = false) String title
            , @RequestParam(value = "contents", required = false) String contents
            , @RequestParam(value = "hit", required = false) int hit
            , @RequestParam(value = "hit-operator", required = false) String hitOperator
            , @RequestParam(value = "start-date", required = false) String startDate
            , @RequestParam(value = "end-date", required = false) String endDate
            , @RequestParam("page-number") Integer pageNumber
            , @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(boardPostService.list(BoardPostListVO.builder()
                        .bbs(bbs)
                        .title(title)
                        .contents(contents)
                        .hitOperator(hitOperator)
                        .hit(hit)
                        .startDate(DateUtils.convertStringDateToZonedDateTime(startDate, DateUtils.YYYY_MM_DD))
                        .endDate(DateUtils.convertStringDateToZonedDateTime(endDate, DateUtils.YYYY_MM_DD))
                        .build()
                , PaginationOffsetRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("{uid}")
    public ResponseEntity<RestResponseVO<BoardPostListEntity>> detail(
            @PathVariable("bbs") String bbs
            , @PathVariable("uid") long uid
    ) {
        return ResponseEntity.ok(boardPostService.detail(bbs, uid));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @PathVariable("bbs") String bbs,
            @RequestBody @Validated(BoardPostValidationGroup.Create.class) BoardPostCreateVO boardPostCreateVO
    ) {
        return ResponseEntity.ok(boardPostService.create(bbs, boardPostCreateVO));
    }

    @PostMapping("/reply")
    public ResponseEntity<RestResponseVO<Void>> reply(
        @PathVariable("bbs") String bbs,
        @RequestBody @Validated(BoardPostValidationGroup.Reply.class) BoardPostReplyVO boardPostReplyVO
    ){
        return ResponseEntity.ok(boardPostService.reply(bbs, boardPostReplyVO));
    }

    @DeleteMapping("/{board_uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("bbs") String bbs
            , @PathVariable("board_uid") long boardUid
    ) {
        return ResponseEntity.ok(boardPostService.remove(bbs, boardUid));
    }

    @DeleteMapping("/perfect/{board_uid}")
    public ResponseEntity<RestResponseVO<Void>> perfectRemove(
            @PathVariable("bbs") String bbs
            , @PathVariable("board_uid") long boardUid
    ) {
        return ResponseEntity.ok(boardPostService.perfectRemove(bbs, boardUid));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @PathVariable("bbs") String bbs
            , @RequestBody @Validated(BoardPostValidationGroup.Modify.class) BoardPostModifyVO boardPostModifyVO
    ) {
        return ResponseEntity.ok(boardPostService.modify(bbs, boardPostModifyVO));
    }

    @PostMapping("/recommand")
    public ResponseEntity<RestResponseVO<Void>> recommend(
            @PathVariable("bbs") String bbs
            , @RequestBody @Validated(BoardPostValidationGroup.Recommend.class) BoardPostRecommendVO boardPostRecommendVO
    ) {
        return ResponseEntity.ok(boardPostService.recommend(bbs, boardPostRecommendVO));
    }
}
