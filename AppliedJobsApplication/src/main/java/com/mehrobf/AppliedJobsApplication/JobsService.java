package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class JobsService
{

    @Autowired
    private JobsRepository jobsRepository;

    public List<Job> getAppliedJobs()
    {
        return jobsRepository.getAllJobs();
    }

    public Job createJobApplication(JobRequest newJobRequest)
    {
        Job newjob = new Job(newJobRequest);
        return jobsRepository.createJob(newjob);
    }

    public Job deleteJobApplication(String jobId)
    {
        return jobsRepository.deleteJobById(jobId);
    }

    public Job getApplicationById(String jobId)
    {
        return jobsRepository.findJobById(jobId);
    }

    public Job updateApplicationStatus(String jobId, ApplicationStatus status)
    {
        Job job = getApplicationById(jobId);

        if (job == null)
        {
            return null;
        }

        job.setApplicationStatus(status);

        return jobsRepository.updateJob(job);
    }

    public List<Job> searchJobApplications(Map<String,String> params)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (params.containsKey("startDate") && params.containsKey("endDate"))
        {
            LocalDate startDate = LocalDate.parse(params.get("startDate"), formatter);
            LocalDate endDate = LocalDate.parse(params.get("startDate"), formatter);

            if (startDate.isAfter(endDate))
            {
                throw new IllegalArgumentException("End date must be after the start date.");
            }
        }

        if (params.containsKey("startingPay") && params.containsKey("endingPay"))
        {
            float startingPay = Float.parseFloat(params.get("startingPay"));
            float endingPay = Float.parseFloat(params.get("endingPay"));

            if (startingPay > endingPay)
            {
                throw new IllegalArgumentException("Starting pay must be less than ending pay.");
            }
        }
        return jobsRepository.searchJobApplications(params);
    }
}
