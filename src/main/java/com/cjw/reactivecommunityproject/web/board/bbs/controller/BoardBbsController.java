package com.cjw.reactivecommunityproject.web.board.bbs.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsListEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.request.BoardBbsCreateVO;
import com.cjw.reactivecommunityproject.web.board.bbs.model.request.BoardBbsModifyVO;
import com.cjw.reactivecommunityproject.web.board.bbs.service.BoardBbsService;
import com.cjw.reactivecommunityproject.web.board.bbs.validation.BoardBbsValidationGroup;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/bbs")
public class BoardBbsController {
    private final BoardBbsService boardBbsService;

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(@RequestBody @Validated(BoardBbsValidationGroup.Create.class) BoardBbsCreateVO bbsContent) {
        return ResponseEntity.ok(boardBbsService.create(bbsContent));
    }


    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(@RequestBody @Validated(BoardBbsValidationGroup.Modify.class) BoardBbsModifyVO boardBbsModifyVO) {
        return ResponseEntity.ok(boardBbsService.modify(boardBbsModifyVO));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(boardBbsService.remove(uid));
    }

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<BoardBbsListEntity>>> list(){
        return ResponseEntity.ok(boardBbsService.list());
    }
}
