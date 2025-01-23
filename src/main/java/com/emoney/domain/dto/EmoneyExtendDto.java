package com.emoney.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmoneyExtendDto {

    private Long emoneySeq;
    private LocalDateTime expirationDateTime;
}
