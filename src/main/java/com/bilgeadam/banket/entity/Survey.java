package com.bilgeadam.banket.entity;

import com.bilgeadam.banket.dto.request.AddQuestionToSurveyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Document
public class Survey extends BaseEntity {

    private String name;
    private List<AddQuestionToSurveyDto> questions;


}
