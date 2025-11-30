package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MissionRepository missionRepository;

    public MissionResDTO.MissionPreviewListDTO findMissionsByStore(Long storeId, Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Mission> result = missionRepository.findAllByStoreId(storeId, pageRequest);

        return MissionConverter.toMissionPreviewListDTO(result);
    }
}
