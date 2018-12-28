package com.example.demo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTeacherDTO {
    private String account;
    private String password;
    private String name;
    private String email;

}
