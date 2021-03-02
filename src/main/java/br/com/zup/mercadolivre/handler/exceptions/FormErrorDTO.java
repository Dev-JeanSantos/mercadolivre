package br.com.zup.mercadolivre.handler.exceptions;

import java.time.LocalDateTime;

public class FormErrorDTO {
	
    private String field;
    private String error;
    private int status;
    private LocalDateTime timeStamp;

    public FormErrorDTO(String field, String message, int status, LocalDateTime timeStamp) {
        this.field = field;
        this.error = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

}
