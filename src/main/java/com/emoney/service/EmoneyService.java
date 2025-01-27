package com.emoney.service;

import com.emoney.domain.dto.EmoneyCancelDto;
import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.dto.EmoneyExtendDto;
import com.emoney.domain.dto.EmoneyDeductDto;
import com.emoney.domain.vo.EmoneyVo;

import java.util.List;

public interface EmoneyService {

    List<EmoneyVo> findAllEmoneys();                            // 적립금 전체 조회
    void createEmoney(EmoneyCreateDto emoneyCreateDto);         // 적립금 지금
    void deductEmoney(EmoneyDeductDto emoneyDeductDto);         // 적립금 사용 및 차감
    void useCancelEmoney(EmoneyCancelDto emoneyCancelDto);      // 적립금 사용 취소
    void approveEmoney(Long emoneySeq);                         // 적립금 승인
    void rejectEmoney(Long emoneySeq);                          // 적립금 반려
    void extendEmoney(EmoneyExtendDto emoneyExtendDto);         // 적립금 연장
    void expireEmoney(Long emoneySeq);                          // 적립금 만료
}
