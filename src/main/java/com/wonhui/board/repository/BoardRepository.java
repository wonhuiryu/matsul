package com.wonhui.board.repository;

import com.wonhui.entity.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wonhuiryu on 2018-05-12.
 */
public interface BoardRepository extends MongoRepository<Board, String> {
}
