package com.application.flatrack.Models.Resident;

import com.application.flatrack.Models.Flat.FlatInfo;

public class ResidentResponse {

    Resident resident;
    String responseMessage;

    public ResidentResponse(Resident resident, String responseMessage) {
        this.resident = resident;
        this.responseMessage = responseMessage;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
