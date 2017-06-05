package com.example.simple_soul.mylearningdemo.domain;

/**
 * Created by hp on 2017/6/4.
 */

public class RoadStatus {

    /**
     * RESULT : S
     * ERRMSG : 成功
     * Status : 1
     */

    private String RESULT;
    private String ERRMSG;
    private int Status;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
}
