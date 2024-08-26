package com.bilgeadam.banket.service;

import com.bilgeadam.banket.dto.response.SendedSurveyResponseDto;
import com.bilgeadam.banket.entity.SendedSurvey;
import com.bilgeadam.banket.repository.GroupRepository;
import com.bilgeadam.banket.repository.SendedSurveyRepository;
import com.bilgeadam.banket.repository.SurveyRepository;
import com.bilgeadam.banket.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SendedSurveyService extends ServiceManager<SendedSurvey, String> {

    private final SendedSurveyRepository sendedSurveyRepository;
    private final GroupRepository groupRepository;
    private final SurveyRepository surveyRepository;

    public SendedSurveyService(MongoRepository<SendedSurvey, String> mongoRepository, SendedSurveyRepository sendedSurveyRepository, GroupRepository groupRepository, SurveyRepository surveyRepository) {
        super(mongoRepository);
        this.sendedSurveyRepository = sendedSurveyRepository;
        this.groupRepository = groupRepository;
        this.surveyRepository = surveyRepository;
    }

    public void saveSendedSurvey(UUID groupUuid, UUID surveyUuid) {
        SendedSurvey sendedSurvey = SendedSurvey.builder()
                .groupUuid(groupUuid)
                .surveyUuid(surveyUuid)
                .sendedAt(LocalDateTime.now())
                .build();
        save(sendedSurvey);
    }

    public List<SendedSurveyResponseDto> getAllSendedSurveys() {
        List<SendedSurvey> sendedSurveys = sendedSurveyRepository.findAll();
        return sendedSurveys.stream().map(sendedSurvey -> {
            SendedSurveyResponseDto sendedSurveyResponseDto = new SendedSurveyResponseDto();
            sendedSurveyResponseDto.setUuid(sendedSurvey.getUuid());
            sendedSurveyResponseDto.setGroupUuid(sendedSurvey.getGroupUuid());
            sendedSurveyResponseDto.setGroupName(groupRepository.findByUuid(sendedSurvey.getGroupUuid()).get().getName());
            sendedSurveyResponseDto.setSurveyId(surveyRepository.findByUuid(sendedSurvey.getSurveyUuid()).get().getId());
            sendedSurveyResponseDto.setSurveyName(surveyRepository.findByUuid(sendedSurvey.getSurveyUuid()).get().getName());
            sendedSurveyResponseDto.setSendedAt(sendedSurvey.getSendedAt());
            return sendedSurveyResponseDto;
        }).toList();
    }

    public List<SendedSurveyResponseDto> getAllSendedSurveysByGroup(UUID groupUuid) {
        List<SendedSurvey> sendedSurveys = sendedSurveyRepository.findAllByGroupUuid(groupUuid);
        return sendedSurveys.stream().map(sendedSurvey -> {
            SendedSurveyResponseDto sendedSurveyResponseDto = new SendedSurveyResponseDto();
            sendedSurveyResponseDto.setGroupName(groupRepository.findByUuid(sendedSurvey.getGroupUuid()).get().getName());
            sendedSurveyResponseDto.setSurveyName(surveyRepository.findByUuid(sendedSurvey.getSurveyUuid()).get().getName());
            sendedSurveyResponseDto.setSendedAt(sendedSurvey.getSendedAt());
            return sendedSurveyResponseDto;
        }).toList();
    }

    public List<SendedSurveyResponseDto> getAllSendedSurveysBySurvey(UUID surveyUuid) {
        List<SendedSurvey> sendedSurveys = sendedSurveyRepository.findAllBySurveyUuid(surveyUuid);
        return sendedSurveys.stream().map(sendedSurvey -> {
            SendedSurveyResponseDto sendedSurveyResponseDto = new SendedSurveyResponseDto();
            sendedSurveyResponseDto.setGroupName(groupRepository.findByUuid(sendedSurvey.getGroupUuid()).get().getName());
            sendedSurveyResponseDto.setSurveyName(surveyRepository.findByUuid(sendedSurvey.getSurveyUuid()).get().getName());
            sendedSurveyResponseDto.setSendedAt(sendedSurvey.getSendedAt());
            return sendedSurveyResponseDto;
        }).toList();
    }
}
