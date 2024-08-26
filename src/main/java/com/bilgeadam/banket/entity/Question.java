package com.bilgeadam.banket.entity;

import com.bilgeadam.banket.entity.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Document
public class Question extends BaseEntity {
  /*  description: String
    questionType: QuestionType
    open-ended
            likert,
            matrix,
    multiple-selection
    single-selection
    multiple-selection-other*/

    private String description;
    private QuestionType questionType;
    private String options;

}
