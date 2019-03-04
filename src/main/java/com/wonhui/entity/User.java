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
@Document(collection = "user")
public class User extends BaseEntity{

/*    @Id
    private String id;*/

    @Id
    private String email;
    private String password;

    private String signUpKey;
}
