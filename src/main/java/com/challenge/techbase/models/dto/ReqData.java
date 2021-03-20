package com.challenge.techbase.models.dto;

import com.challenge.techbase.util.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqData {
    int code;
    String message;
    Object data;

    public ReqData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ReqData(Object data) {
        this(Constants.SUCCESS_CODE, null, data);
    }

    public ReqData() {
        this(Constants.SUCCESS_CODE, null, null);
    }
}
