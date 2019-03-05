package com.wonhui.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 30)
    private String password;

    private String signUpKey;

    private String token;
}
