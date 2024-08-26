package com.bilgeadam.banket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Document(collection = "filledSurveys")
public class FilledSurvey extends BaseEntity {

    private String studentEmail;

    private String surveyId;

    private List<Answer> answers;



}
