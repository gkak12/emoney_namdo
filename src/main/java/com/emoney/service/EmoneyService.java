package com.emoney.service;

import com.emoney.domain.dto.request.*;
import com.emoney.domain.dto.response.ResponseEmoneyResponseDetailDto;
import com.emoney.domain.dto.response.ResponseEmoneyResponseListDto;
import com.emoney.domain.dto.response.ResponseEmoneyDto;

import java.util.List;

public interface EmoneyService {

    List<ResponseEmoneyDto> findAllEmoneys();                                    // 적립금 전체 조회
    ResponseEmoneyResponseListDto findPageEmoneys(RequestEmoneySearchDto emoneyRequestSearchDto);      // 적립금 페이징 조회
    ResponseEmoneyResponseDetailDto findEmoneyDetail(Long emoneySeq);                    // 적립금 상세 조회
    void createEmoney(RequestEmoneyCreateDto requestEmoneyCreateDto);                 // 적립금 지급
    void deductEmoney(RequestEmoneyDeductDto requestEmoneyDeductDto);                 // 적립금 사용 및 차감
    void useCancelEmoney(RequestEmoneyCancelDto requestEmoneyCancelDto);              // 적립금 사용 취소
    void approveEmoney(Long emoneySeq);                                 // 적립금 승인
    void rejectEmoney(Long emoneySeq);                                  // 적립금 반려
    void extendEmoney(RequestEmoneyExtendDto requestEmoneyExtendDto);                 // 적립금 연장
    void expireEmoney(Long emoneySeq);                                  // 적립금 만료
}
