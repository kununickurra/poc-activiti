package org.activiti.examples.web.controller.dto;

/**
 * Created by nico on 14.02.16.
 */
public class PhdEngagementResponse {

    private String processId;
    private String message;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PhdEngagementResponse{" +
                "processId=" + processId +
                ", message='" + message + '\'' +
                '}';
    }
}
