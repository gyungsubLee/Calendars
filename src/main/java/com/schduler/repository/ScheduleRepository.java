package com.schduler.repository;

import com.schduler.Entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    void addSchedule(Schedule schedule);
    Schedule getSchedule(Long id);
    List<Schedule> getAllSchedules();
    void updateSchedule(Long id, String password, Schedule schedule);
    void deleteSchedule(Long id, String password);
}