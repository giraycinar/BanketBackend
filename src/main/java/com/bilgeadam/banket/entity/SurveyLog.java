package com.bilgeadam.banket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@Document(collection = "surveylogs")
public class SurveyLog {
    @Id
    private String id;
    private String studentEmail;
    private String groupName;
    private String surveyId;
    private Date sentDate;
}
