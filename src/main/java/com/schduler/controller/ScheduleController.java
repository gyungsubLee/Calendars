package com.schduler.controller;

import com.schduler.dto.ScheduleRequestDto;
import com.schduler.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ScheduleController {
    ResponseEntity<Void> addSchedule(ScheduleRequestDto requestDto);
    ResponseEntity<ScheduleResponseDto> getSchedule(Long id);
    List<ScheduleResponseDto> getAllSchedules();
    ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, String password, ScheduleRequestDto requestDto);
    ResponseEntity<Void> deleteSchedule(Long id, String password);
}