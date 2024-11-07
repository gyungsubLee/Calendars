package com.schduler.service;

import com.schduler.dto.ScheduleRequestDto;
import com.schduler.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    void addSchedule(ScheduleRequestDto requestDto);
    List<ScheduleResponseDto> getAllSchedules();
    ScheduleResponseDto getScheduleById(Long id);
    ScheduleResponseDto updateSchedule(Long id, String password,  String writer, String title);
    void deleteSchedule(Long id, String password);
}