package com.mina.khedma.controller;

import com.mina.khedma.model.DateDTO;
import com.mina.khedma.model.UserAttendanceDTO;
import com.mina.khedma.model.requests.DateRangeRequest;
import com.mina.khedma.service.AttendanceService;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/attendance")
@PreAuthorize("hasRole('ADMIN')")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public ResponseEntity<Page<UserAttendanceDTO>> getAllAttendances(Pageable pageable, DateDTO dateDTO) throws BadRequestException {
        return ok(attendanceService.getAllAttendancesByYearMonth(pageable, dateDTO));
    }

    @PostMapping("/by-range")
    public ResponseEntity<Page<UserAttendanceDTO>> getAllAttendancesByRange(Pageable pageable, @RequestBody DateRangeRequest dateRangeRequest) throws BadRequestException {
        return ok(attendanceService.getAllAttendancesByRange(pageable, dateRangeRequest));
    }
}
