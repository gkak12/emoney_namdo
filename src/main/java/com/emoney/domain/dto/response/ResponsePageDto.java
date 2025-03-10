package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePageDto {

    private int totalPages;     // 전체 페이지 수
    private long totalItems;    // 전체 항목 수
}
