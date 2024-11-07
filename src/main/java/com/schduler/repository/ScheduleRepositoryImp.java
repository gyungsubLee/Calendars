package com.schduler.repository;

import com.schduler.Entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImp implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    // 일정 추가
    @Override
    public void addSchedule(Schedule schedule) {
        String sql = "INSERT INTO Schedule (writer, password, title, content, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, schedule.getWriter(), schedule.getPassword(), schedule.getTitle(),
                schedule.getContent(), LocalDateTime.now(), LocalDateTime.now());
    }

    // 일정 조회
    @Override
    public Schedule getSchedule(Long id) {
        String sql = "SELECT * FROM Schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, scheduleRowMapper());
    }

    // 일정 전체 조회
    @Override
    public List<Schedule> getAllSchedules() {
        String sql = "SELECT * FROM Schedule ORDER BY updatedAt DESC";
        return jdbcTemplate.query(sql, scheduleRowMapper());
    }

    // 일정 수정
    @Override
    public void updateSchedule(Long id, String password, Schedule schedule) {
        String sql = "UPDATE Schedule SET title = ?, content = ?, updatedAt = ? WHERE id = ? AND password = ?";
        jdbcTemplate.update(sql, schedule.getTitle(), schedule.getContent(), LocalDateTime.now(), id, password);
    }

    // 일정 삭제
    @Override
    public void deleteSchedule(Long id, String password) {
        String sql = "DELETE FROM Schedule WHERE id = ? AND password = ?";
        jdbcTemplate.update(sql, id, password);
    }

    // RowMapper 정의
    private RowMapper<Schedule> scheduleRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("id"));
            schedule.setWriter(rs.getString("writer"));
            schedule.setPassword(rs.getString("password"));
            schedule.setTitle(rs.getString("title"));
            schedule.setContent(rs.getString("content"));
            schedule.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            schedule.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
            return schedule;
        };
    }
}