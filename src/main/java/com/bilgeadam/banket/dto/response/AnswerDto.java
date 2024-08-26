package com.bilgeadam.banket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnswerDto {
    private String questionId;
    private String answerText;


}
