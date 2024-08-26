package com.bilgeadam.banket.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Document
public class SendedSurvey extends BaseEntity {
    private UUID groupUuid;
    private UUID surveyUuid;
    private LocalDateTime sendedAt;
}
