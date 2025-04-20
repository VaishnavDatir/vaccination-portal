package com.school.vaccineportal.vaccination_portal.model;

import java.util.List;

public class BulkUploadResponse {
    private int successCount;
    private int failureCount;
    private List<String> failedRecords;

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public List<String> getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(List<String> failedRecords) {
        this.failedRecords = failedRecords;
    }

    @Override
    public String toString() {
        return "BulkUploadResponse [successCount=" + successCount + ", failureCount=" + failureCount
                + ", failedRecords=" + failedRecords + "]";
    }

}
