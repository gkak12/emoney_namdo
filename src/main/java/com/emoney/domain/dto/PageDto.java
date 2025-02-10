package com.emoney.domain.dto;

import lombok.Data;

@Data
public class PageDto {

    private Integer pageNumber;     // 현재 페이지 수
    private Integer pageSize;       // 현재 페이지 항목 수
    private Integer pageOffset;     // 현재 페이지 offset

    public int getPageNumber() {
        return pageNumber == null ? 0 : pageNumber-1;
    }
}
