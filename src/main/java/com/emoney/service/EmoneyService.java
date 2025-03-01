package com.emoney.service;

import com.emoney.domain.dto.request.*;
import com.emoney.domain.dto.response.ResponseEmoneyResponseDetailDto;
import com.emoney.domain.dto.response.ResponseEmoneyResponseListDto;
import com.emoney.domain.dto.response.ResponseEmoneyDto;

import java.util.List;

public interface EmoneyService {

    /**
     * 적립금 전체 조회
     *
     * @return
     */
    List<ResponseEmoneyDto> findAllEmoneys();

    /**
     * 적립금 페이징 조회
     *
     * @param emoneyRequestSearchDto
     * @return
     */
    ResponseEmoneyResponseListDto findPageEmoneys(RequestEmoneySearchDto emoneyRequestSearchDto);

    /**
     * 적립금 상세 조회
     *
     * @param emoneySeq
     * @return
     */
    ResponseEmoneyResponseDetailDto findEmoneyDetail(Long emoneySeq);

    /**
     * 적립금 지급
     *
     * @param requestEmoneyCreateDto
     */
    void createEmoney(RequestEmoneyCreateDto requestEmoneyCreateDto);

    /**
     * 적립금 사용 및 차감
     *
     * @param requestEmoneyDeductDto
     */
    void deductEmoney(RequestEmoneyDeductDto requestEmoneyDeductDto);

    /**
     * 적립금 사용 취소
     *
     * @param requestEmoneyCancelDto
     */
    void useCancelEmoney(RequestEmoneyCancelDto requestEmoneyCancelDto);

    /**
     * 적립금 승인
     *
     * @param emoneySeq
     */
    void approveEmoney(Long emoneySeq);

    /**
     * 적립금 반려
     * @param emoneySeq
     */
    void rejectEmoney(Long emoneySeq);

    /**
     * 적립금 연장
     * @param requestEmoneyExtendDto
     */
    void extendEmoney(RequestEmoneyExtendDto requestEmoneyExtendDto);

    /**
     * 적립금 만료
     * @param emoneySeq
     */
    void expireEmoney(Long emoneySeq);
}
