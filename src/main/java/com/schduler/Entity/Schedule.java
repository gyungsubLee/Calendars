package com.schduler.Entity;


import com.schduler.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String writer;
    private String password;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.writer = scheduleRequestDto.getWriter();
        this.password = scheduleRequestDto.getPassword();
        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getPassword();
    }

    public void update(String writer, String title) {
        this.writer = writer;
        this.title = title;
    }
}
