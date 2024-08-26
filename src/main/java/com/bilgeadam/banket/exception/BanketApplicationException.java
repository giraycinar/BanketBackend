package com.bilgeadam.banket.exception;

import lombok.Getter;

@Getter
public class BanketApplicationException extends RuntimeException{

    private final ErrorType errorType;

    public BanketApplicationException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BanketApplicationException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }

}
