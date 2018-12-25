package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class TeacherEntity {
    private Long id;
    private String account;//是否需要待定
    private String password;
    private String teacher_name;
    private int is_active;
    private String email;

}
