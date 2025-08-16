package com.cjw.reactivecommunityproject.web.board.controller;

import com.cjw.reactivecommunityproject.web.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/board/{bbs}")
public class BoardController {
    private final BoardService boardService;
}
