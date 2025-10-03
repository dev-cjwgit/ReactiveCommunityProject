package com.cjw.reactivecommunityproject.web.board.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.board.model.entity.BoardListEntity;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardCreateVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardListVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardModifyVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardRecommendVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardReplyVO;
import com.cjw.reactivecommunityproject.web.board.service.BoardService;
import com.cjw.reactivecommunityproject.web.board.validation.BoardValidationGroup;
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
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<BoardListEntity>>> list(
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
        return ResponseEntity.ok(boardService.list(BoardListVO.builder()
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
    public ResponseEntity<RestResponseVO<BoardListEntity>> detail(
            @PathVariable("bbs") String bbs
            , @PathVariable("uid") long uid
    ) {
        return ResponseEntity.ok(boardService.detail(bbs, uid));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @PathVariable("bbs") String bbs,
            @RequestBody @Validated(BoardValidationGroup.Create.class) BoardCreateVO boardCreateVO
    ) {
        return ResponseEntity.ok(boardService.create(bbs, boardCreateVO));
    }

    @PostMapping("/reply")
    public ResponseEntity<RestResponseVO<Void>> reply(
        @PathVariable("bbs") String bbs,
        @RequestBody @Validated(BoardValidationGroup.Reply.class) BoardReplyVO boardReplyVO
    ){
        return ResponseEntity.ok(boardService.reply(bbs, boardReplyVO));
    }

    @DeleteMapping("/{board_uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("bbs") String bbs
            , @PathVariable("board_uid") long boardUid
    ) {
        return ResponseEntity.ok(boardService.remove(bbs, boardUid));
    }

    @DeleteMapping("/perfect/{board_uid}")
    public ResponseEntity<RestResponseVO<Void>> perfectRemove(
            @PathVariable("bbs") String bbs
            , @PathVariable("board_uid") long boardUid
    ) {
        return ResponseEntity.ok(boardService.perfectRemove(bbs, boardUid));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @PathVariable("bbs") String bbs
            , @RequestBody @Validated(BoardValidationGroup.Modify.class) BoardModifyVO boardModifyVO
    ) {
        return ResponseEntity.ok(boardService.modify(bbs, boardModifyVO));
    }

    @PostMapping("/recommand")
    public ResponseEntity<RestResponseVO<Void>> recommend(
            @PathVariable("bbs") String bbs
            , @RequestBody @Validated(BoardValidationGroup.Recommend.class) BoardRecommendVO boardRecommendVO
    ) {
        return ResponseEntity.ok(boardService.recommend(bbs, boardRecommendVO));
    }
}
