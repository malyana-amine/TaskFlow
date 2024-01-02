package com.taskflow.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
//    private Integer coins;

}
