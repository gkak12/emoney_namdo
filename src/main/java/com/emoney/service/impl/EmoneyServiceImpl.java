package com.emoney.service.impl;

import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.dto.EmoneyExtendDto;
import com.emoney.domain.dto.EmoneyUpdateDto;
import com.emoney.domain.mapper.EmoneyMapper;
import com.emoney.domain.vo.EmoneyVo;
import com.emoney.repository.EmoneyRepository;
import com.emoney.service.EmoneyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmoneyServiceImpl implements EmoneyService {

    private final EmoneyMapper emoneyMapper;
    private final EmoneyRepository emoneyRepository;

    @Override
    public List<EmoneyVo> findAllEmoneys() {
        return emoneyRepository.findAll().stream()
                .map(emoneyMapper::toVo)
                .toList();
    }

    @Override
    public void createEmoney(EmoneyCreateDto emoneyCreateDto) {

    }

    @Override
    public void deductEmoney(Long emoneySeq) {

    }

    @Override
    public void useEmoney(EmoneyUpdateDto emoneyUpdateDto) {

    }

    @Override
    public void useCancelEmoney(EmoneyUpdateDto emoneyUpdateDto) {

    }

    @Override
    public void approveEmoney(Long emoneySeq) {

    }

    @Override
    public void refuseEmoney(Long emoneySeq) {

    }

    @Override
    public void extendEmoney(EmoneyExtendDto emoneyExtendDto) {

    }

    @Override
    public void expireEmoney() {

    }
}
