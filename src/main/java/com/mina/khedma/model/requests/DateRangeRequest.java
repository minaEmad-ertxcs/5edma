package com.mina.khedma.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateRangeRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
