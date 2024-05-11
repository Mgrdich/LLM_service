package com.llm_service.llm_service.exception;

public class UnAuthorizedException extends Exception {
    public UnAuthorizedException() {
        super("UnAuthorized");
    }
}
