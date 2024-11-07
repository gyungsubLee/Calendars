package com.schduler.controller;

import com.schduler.dto.ScheduleRequestDto;
import com.schduler.dto.ScheduleResponseDto;
import com.schduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleControllerImp implements ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 API
     * @param : {@link ScheduleRequestDto} 일정 생성 요청 DTO
     * @return : {@link ResponseEntity<Void>} 성공 시 Data 없이 201 Created 상태 코드만 응답
     */
    @Override
    @PostMapping
    public ResponseEntity<Void> addSchedule(@RequestBody ScheduleRequestDto requestDto) {
        scheduleService.addSchedule(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 일정 전체 조회 API
     * @return : {@link List<ScheduleResponseDto>} JSON 응답
     */
    @Override
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }


    /**
     * 일정 단건 조회 API
     * @param id 식별자
     * @return : {@link ResponseEntity<ScheduleResponseDto>} JSON 응답
     * @exception : ResponseStatusException 식별자로 조회된 Schedule이 없는 경우 404 Not Found
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.getScheduleById(id), HttpStatus.OK);
    }

    /**
     * 일정 전체 수정 API
     * @param id 식별자
     * @param password 작성자 인증을 위한 비밀번호
     * @param : {@link ScheduleRequestDto} 메모 수정 요청 DTO
     * @return : {@link ResponseEntity<ScheduleResponseDto>} JSON 응답
     * @exception ResponseStatusException 요청 필수 값이 없는 경우 400 Bad Request, 식별자로 조회된 Schedule이 없는 경우 404 Not Found
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestParam String password, @RequestBody ScheduleRequestDto requestDto) {

        return new ResponseEntity<>(scheduleService.updateSchedule(id, password,  requestDto.getWriter(), requestDto.getTitle()), HttpStatus.OK);
    }

    /**
     * 일정 삭제 APU
     * @param id 식별자
     * @param password 작성자 인증을 위한 비밀번호
     * @return : {@link ResponseEntity<Void> 성공 시 Data 없이 200OK 상태 코드만 반환}
     * @exception ResponseStatusException 식별자로 조회된 Schedule이 없는 경우 404 Not Found
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}