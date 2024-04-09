package com.mehrobf.AppliedJobsApplication;

public enum ApplicationStatus
{
    APPLIED("Applied"),

    GHOSTED("Ghosted"),

    REJECTED("Rejected"),

    INPROGRESS("InProgress"),

    REJECTEDAFTERINTERVIEW("RejectedAfterInterview"),

    ACCEPTED("Accepted");

    private final String applicationStatus;

    ApplicationStatus(String applicationStatus)
    {
        this.applicationStatus = applicationStatus;
    }
}
