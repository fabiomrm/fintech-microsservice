package com.fmrm.credit.assessment.service.exception;

public class MicroserviceCommunicationException extends Exception {


    private Integer status;
    public MicroserviceCommunicationException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }

    private Integer getStatus() {
        return status;
    }

    private void setStatus(Integer status) {
        this.status = status;
    }
}
