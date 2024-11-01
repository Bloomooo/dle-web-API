package com.backendkiss.backendkiss.entity;

import com.backendkiss.backendkiss.entity.interfaces.OutputEntity;

public class ResponseBody {
    private String message;
    private Boolean success;
    private OutputEntity data;

    public ResponseBody(String message, Boolean success, OutputEntity data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public OutputEntity getData() {
        return data;
    }
}
