package com.mehrobf.AppliedJobsApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "job_applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job
{

    @Id
    private String id;

    private String webLink;

    private String title;

    private String company;

    private float pay;

    private boolean remote;

    private boolean qualified;

    private boolean customResume;

    private Date applicationDate;

    private ApplicationStatus applicationStatus;

    private JobBoard jobBoard;

    public Job(JobRequest jobRequest)
    {
        this.title = jobRequest.getTitle();
        this.company = jobRequest.getCompany();
        this.pay = jobRequest.getPay();
        this.remote = jobRequest.isRemote();
        this.qualified = jobRequest.isQualified();
        this.webLink = jobRequest.getWebLink();
        this.customResume = jobRequest.isCustomResume();

        LocalDate currentDate = LocalDate.now();
        if (jobRequest.getApplicationDate() != null)
        {
            this.applicationDate = jobRequest.getApplicationDate();
        }
        else
        {
            this.applicationDate = java.sql.Date.valueOf(currentDate);
        }
        this.applicationStatus = ApplicationStatus.APPLIED;
        this.jobBoard = jobRequest.getJobBoard();
    }
}
