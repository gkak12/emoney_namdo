package com.emoney.domain.dto;

import lombok.Data;

@Data
public class PageDto {

    private int pageNumber;     // 현재 페이지 수
    private int pageSize;       // 현재 페이지 항목 수
    private int pageOffset;     // 현재 페이지 offset

    public int getPageOffset() {
        return pageNumber < 1 ? 0 : (pageNumber-1) * pageSize;
    }
}
