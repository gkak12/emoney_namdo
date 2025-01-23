package com.emoney.service;

import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.dto.EmoneyExtendDto;
import com.emoney.domain.dto.EmoneyUpdateDto;
import com.emoney.domain.vo.EmoneyVo;

import java.util.List;

public interface EmoneyService {

    List<EmoneyVo> findAllEmoneys();                        // 적립금 전체 조회
    void createEmoney(EmoneyCreateDto emoneyCreateDto);     // 적립금 지금
    void deductEmoney(Long emoneySeq);                      // 적립금 차감
    void useEmoney(EmoneyUpdateDto emoneyUpdateDto);        // 적립금 사용
    void useCancelEmoney(EmoneyUpdateDto emoneyUpdateDto);  // 적립금 사용 취소
    void approveEmoney(Long emoneySeq);                     // 적립금 승인
    void refuseEmoney(Long emoneySeq);                      // 적립금 반려
    void extendEmoney(EmoneyExtendDto emoneyExtendDto);     // 적립금 연장
    void expireEmoney();                                    // 적립금 만료
}
