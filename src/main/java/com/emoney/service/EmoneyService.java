package com.emoney.service;

import com.emoney.domain.dto.*;
import com.emoney.domain.vo.EmoneyDetailVo;
import com.emoney.domain.vo.EmoneyListVo;
import com.emoney.domain.vo.EmoneyVo;

import java.util.List;

public interface EmoneyService {

    List<EmoneyVo> findAllEmoneys();                                    // 적립금 전체 조회
    EmoneyListVo findPageEmoneys(EmoneySearchDto emoneySearchDto);      // 적립금 페이징 조회
    EmoneyDetailVo findEmoneyDetail(Long emoneySeq);                    // 적립금 상세 조회
    void createEmoney(EmoneyCreateDto emoneyCreateDto);                 // 적립금 지급
    void deductEmoney(EmoneyDeductDto emoneyDeductDto);                 // 적립금 사용 및 차감
    void useCancelEmoney(EmoneyCancelDto emoneyCancelDto);              // 적립금 사용 취소
    void approveEmoney(Long emoneySeq);                                 // 적립금 승인
    void rejectEmoney(Long emoneySeq);                                  // 적립금 반려
    void extendEmoney(EmoneyExtendDto emoneyExtendDto);                 // 적립금 연장
    void expireEmoney(Long emoneySeq);                                  // 적립금 만료
}
