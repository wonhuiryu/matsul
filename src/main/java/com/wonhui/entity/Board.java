package com.wonhui.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Created by wonhuiryu on 2018-05-12.
 */
@Data
@NoArgsConstructor
@Document(collection = "board")
public class Board extends BaseEntity {

    @Id
    private String id;

    private String subject;
    private String content;
}
