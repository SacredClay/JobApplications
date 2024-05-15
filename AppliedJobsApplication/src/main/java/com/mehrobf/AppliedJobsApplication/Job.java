package com.mehrobf.AppliedJobsApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private Date modifiedDate;

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

        if (jobRequest.getApplicationDate() != null)
        {
            this.applicationDate = jobRequest.getApplicationDate();
        }
        else
        {
            this.applicationDate = CurrentDate.getCurrentDate();
        }
        this.applicationStatus = ApplicationStatus.APPLIED;
        this.jobBoard = jobRequest.getJobBoard();
    }

    public static JobDTO toDTO(Job job)
    {
        if (job == null)
        {
            return null;
        }

        JobDTO jobDTO = new JobDTO();

        jobDTO.setWebLink(job.getWebLink());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setCompany(job.getCompany());
        jobDTO.setPay(job.getPay());
        jobDTO.setRemote(job.isRemote());
        jobDTO.setQualified(job.isQualified());
        jobDTO.setApplicationStatus(job.getApplicationStatus());
        jobDTO.setApplicationDate(job.getApplicationDate());
        jobDTO.setJobBoard(job.getJobBoard());

        return jobDTO;
    }
}
