package com.schduler.service;

import com.schduler.Entity.Schedule;
import com.schduler.dto.ScheduleRequestDto;
import com.schduler.dto.ScheduleResponseDto;
import com.schduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImp implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    @Override
    public void addSchedule(ScheduleRequestDto requestDto) {
        scheduleRepository.addSchedule(new Schedule(requestDto));
    }

    @Override
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.getAllSchedules().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto getScheduleById(Long id) {
        Schedule findSchedule = scheduleRepository.getSchedule(id);

        // NPE 방지
        if (findSchedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + "에 해당하는 Schedule 이 존재하지 않습니다.");
        }

        return convertToResponseDto(findSchedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, String writer, String title) {
        // 일정 조회
        Schedule findSchedule = scheduleRepository.getSchedule(id);

        // NPE 방지
        if (findSchedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + "에 해당하는 Schedule 이 존재하지 않습니다.");
        }

        // 필수 값(작성자, 제목) 검증
        if (writer == null || title == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일정 수정 시 제목과 내용은 필수 입력값 입니다.");
        }

        // 비밀번호 검증
        if (!findSchedule.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호 입력입니다.");
        }

        Schedule schedule = new Schedule();
        schedule.update(writer, title);
        scheduleRepository.updateSchedule(id, password, schedule);
        return convertToResponseDto(scheduleRepository.getSchedule(id));
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        // 일정 조회
        Schedule findSchedule = scheduleRepository.getSchedule(id);

        // NPE 방지
        if (findSchedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + "에 해당하는 Schedule 이 존재하지 않습니다.");
        }

        // 비밀번호 검증
        if (!findSchedule.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호 입력입니다.");
        }

        scheduleRepository.deleteSchedule(id, password);
    }

    private ScheduleResponseDto convertToResponseDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule);
    }
}
