package com.bilgeadam.banket.constant;

public abstract class Endpoint {


    private static final String VERSION = "/v1";

    private static final String API = "/api";

    public static final String ROOT = API + VERSION;

    public static final String AUTH = "/auth";

    public static final String STUDENT = "/student";
    public static final String ANSWER = "/answer";

    public static final String LOGIN = "/login";

    public static final String USER = "/user";

    public static final String SAVE = "/save";

    public static final String UPDATE = "/update";
    public static final String UPDATE_PASSWORD = "/update-password";

    public static final String GET_BY_UUID = "/get/{uuid}";

    public static final String GET_BY_TOKEN = "/get/{token}";

    public static final String GET_ALL = "/get-all";

    public static final String DELETE_BY_UUID = "/delete/{uuid}";



    public static final String DELETE_BY_TOKEN = "/delete/{token}";

    public static final String REMOVE_BY_UUID = "/remove/{uuid}";

    public static final String REMOVE = "/remove";

    public static final String GROUP = "/group";
    public static final String GET_BY_NAME = "/get-by-name/{groupName}";

    public static final String FILL_SURVEY = "/filled-survey";

    public static final String QUESTION = "/question";

    public static final String SURVEY = "/survey";

    public static final String FIND_BY_NAME = "/find-by-name";

    public static final String MAIL = "/mail";

    public static final String SENDED_SURVEY= "/sended-survey";
    public static final String ADD_MANAGER= "/add-manager";
    public static final String SEND_MAIL_SAVE_INFO= "/send-mail-save-info";
    public static final String GET_ALL_BY_GROUP= "/get-all-by-group";
    public static final String GET_ALL_BY_SURVEY= "/get-all-by-survey";
    public static final String GET_QUESTION_BY_SURVEY= "/{surveyUuid}/questions";
    public static final String SEND_MAIL_BY_STUDENT_LIST = "/send-mail-by-student-list";
    public static final String SEND_MAIL_BY_STUDENT = "/send-mail-by-student";
    public static final String GET_BY_MAIL_AND_SURVEY_ID = "/get-by-mail-and-survey-id/{surveyId}/{mail}";
    public static final String GET_ALL_BY_UNCOMPLETED_SURVEY= "/get-all-by-uncompleted-survey";

    public static final String GET_QUESTIONS_BY_SURVEY= "/get-questions-by-survey";


}
