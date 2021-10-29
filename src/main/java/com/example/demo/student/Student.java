package com.example.demo.student;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Student {

    private final Integer id;
    private final String studentName;
}
