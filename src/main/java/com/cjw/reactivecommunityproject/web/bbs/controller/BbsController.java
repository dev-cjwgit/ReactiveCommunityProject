package com.cjw.reactivecommunityproject.web.bbs.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.bbs.model.request.BbsCreateVO;
import com.cjw.reactivecommunityproject.web.bbs.model.request.BbsModifyVO;
import com.cjw.reactivecommunityproject.web.bbs.service.BbsService;
import com.cjw.reactivecommunityproject.web.bbs.validation.BbsValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/bbs")
public class BbsController {
    private final BbsService bbsService;

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(@RequestBody @Validated(BbsValidationGroup.Create.class) BbsCreateVO bbsContent) {
        return ResponseEntity.ok(bbsService.create(bbsContent));
    }


    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(@RequestBody @Validated(BbsValidationGroup.Modify.class) BbsModifyVO bbsModifyVO) {
        return ResponseEntity.ok(bbsService.modify(bbsModifyVO));
    }


    @DeleteMapping("/{uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(bbsService.remove(uid));
    }
}
