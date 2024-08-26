package com.bilgeadam.banket.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "answers")
public class Answer extends BaseEntity{
    private Question question;
    private String answerText;
    private String questionUuid;
    private String filledSurveyUuid;



}
