package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
public class FileDTO {
    MultipartFile file;
}
