package com.wonhui.board.service;

import com.wonhui.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wonhuiryu on 2018-05-12.
 */
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

}
