package com.bilgeadam.banket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum ErrorType {

    USER_NOT_FOUND(2001, "User not found!", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS(2002, "This email address is already in use by someone else!", HttpStatus.CONFLICT),
    PASSWORD_MISMATCH(2003, "Password mismatch!", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_DELETED(2004, "User already deleted!", HttpStatus.GONE),
    ANSWER_ALREADY_EXISTS(2005, "This question has already been answered!", HttpStatus.CONFLICT),
    ANSWER_ALREADY_DELETED(2006, "This answer has already been deleted!", HttpStatus.GONE),
    GROUP_ALREADY_EXISTS(2007, "This group name has already exists.", HttpStatus.CONFLICT),
    GROUP_NOT_FOUND(2008, "Group name not found!", HttpStatus.NOT_FOUND),
    GROUP_ALREADY_DELETED(2009, "This group has already been deleted.", HttpStatus.GONE),
    STUDENT_NOT_FOUND(2010,"Student not found!",HttpStatus.NOT_FOUND),
    STUDENT_ALREADY_DELETED(2011, "This student has already been deleted!", HttpStatus.GONE),
    ANSWER_NOT_FOUND(2012, "Answer not found!", HttpStatus.NOT_FOUND),
    BAD_LOGIN_CREDENTIALS(2100, "The email or password is incorrect!", HttpStatus.UNAUTHORIZED),
    SURVEY_ALREADY_EXISTS(2101, "This survey name has already exists.",HttpStatus.CONFLICT),
    SURVEY_NOT_FOUND(2102, "Survey name not found!", HttpStatus.NOT_FOUND),
    SURVEY_ALREADY_DELETED(2103, "This survey has already been deleted.", HttpStatus.GONE),
    QUESTION_NOT_FOUND(2201, "Question not found!", HttpStatus.NOT_FOUND),
    QUESTION_ALREADY_DELETED(2202, "This question has already been deleted.", HttpStatus.GONE),
    INVALID_TOKEN(3001, "Invalid token!", HttpStatus.UNAUTHORIZED),
    TOKEN_HAS_EXPIRED(3002, "Token has expired!", HttpStatus.UNAUTHORIZED),
    INVALID_HEADER(3003, "A request header field is invalid!", HttpStatus.BAD_REQUEST),
    FILLED_SURVEY_NOT_FOUND(3004, "Filled survey not found!", HttpStatus.NOT_FOUND),
    FILLED_SURVEY_ALREADY_DELETED(3005, "This filled survey has already been deleted!", HttpStatus.GONE),
    TOKEN_CANNOT_CREATED(4001,"An error has been occurred while creating the token",HttpStatus.BAD_REQUEST),
    PARAMETER_NOT_VALID(5001, "Parameter is incorrect", HttpStatus.BAD_REQUEST);

    private int code;

    private String message;

    private HttpStatus httpStatus;

}
