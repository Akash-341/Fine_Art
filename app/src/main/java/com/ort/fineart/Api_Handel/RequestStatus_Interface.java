package com.ort.fineart.Api_Handel;

public interface RequestStatus_Interface <T> {

    void onSuccessResponse(T data);
    void onFailureResponse(String message);
}

