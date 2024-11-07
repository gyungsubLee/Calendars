package com.schduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ScheduleRequestDto {
    private String writer;
    private String password;
    private String title;
    private String content;
}