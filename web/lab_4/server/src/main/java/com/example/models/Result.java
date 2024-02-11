package com.example.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@AllArgsConstructor
//@JsonFormat(shape = JsonFormat.Shape.STRING)
public class Result {
    private Long id;
    private String x;
    private String y;
    private String r;
    private String timestamp;
    private Boolean success;
}