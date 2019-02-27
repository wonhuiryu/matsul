package com.wonhui.board.web;

import com.wonhui.board.repository.BoardRepository;
import com.wonhui.board.service.BoardService;
import com.wonhui.entity.Board;
import com.wonhui.response.ResponseBaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by wonhuiryu on 2018-05-12.
 */
@Controller
@Slf4j
@RequestMapping("/v1/boards")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    //TODO url restful api 구조에 맞지 않는듯함.. 일단 진행 + sorting 구현
    @GetMapping("/search")
    public String board(@PageableDefault(sort = {"id"}, direction = Direction.DESC) Pageable pageable,
                        Model model) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
/*        PageWrapper<Board> page = new PageWrapper<Board>(boardPage, "/v1/boards/search");*/
        model.addAttribute("boards", boardPage);
/*        model.addAttribute("page", page);*/

        return "board/list";
    }

    //등록, 수정폼 불러오기
    @GetMapping
    public String formBoard(@RequestParam(required = false) String id,
                            Model model) {
        model.addAttribute("board", StringUtils.isEmpty(id) ? new Board() : boardRepository.findById(id).get());
        return "board/form";
    }

    //TODO 추후 비동기 처리 및 클라이언트 측 리다이렉트 구현
    @PostMapping
    public String inputBoard(@Valid @ModelAttribute("board") Board board) {
        board.setId(board.getId() == "" ? null : null);
        //등록로직
        boardRepository.save(board);
        return "redirect:/v1/boards/search";
    }

    @PutMapping
    public String updateBoard(@Valid @ModelAttribute("board") Board board) {
        Board originBoard = boardRepository.findById(board.getId()).get();
        originBoard.setContent(board.getContent());
        originBoard.setSubject(board.getSubject());
        //수정로직
        boardRepository.save(originBoard);
        return "redirect:/v1/boards/search";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteBoard(@PathVariable("id") String id,
                                      Model model) {
        boardRepository.deleteById(id);
        return ResponseBaseDto.ok();
    }
}
