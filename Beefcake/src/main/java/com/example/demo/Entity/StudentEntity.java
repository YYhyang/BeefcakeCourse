package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class StudentEntity {
    private Long id;
    private String account;
    private String password;
    private String student_name;
    private int is_active;
    private String email;

}
