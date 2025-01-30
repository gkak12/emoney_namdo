package com.emoney.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    private int totalPages;     // 전체 페이지 수
    private long totalItems;     // 전체 항목 수
}
