package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamMemberDTO {
    private Long id;
    private Long account;
    private String name;
}
