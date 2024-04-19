package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    public List<Job> searchJobApplications(Map<String,String> params) throws ParseException
    {
        return jobsRepository.searchJobApplications(params);
    }

}
