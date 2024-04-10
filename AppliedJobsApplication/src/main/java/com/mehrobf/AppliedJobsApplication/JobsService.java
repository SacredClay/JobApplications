package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsService
{
    @Autowired
    private JobsRepository jobsRepository;

    public List<Job> getAppliedJobs()
    {
        return jobsRepository.findAll();
    }

    public Job createJobApplication(JobRequest newJobRequest)
    {
        Job newjob = new Job(newJobRequest);
        return jobsRepository.save(newjob);
    }

    public Job deleteJobApplication(String jobId)
    {
        return jobsRepository.deleteById(jobId);
    }

    public Job getApplicationById(String jobId)
    {
        return jobsRepository.findById(jobId);
    }

    public Job updateApplicationStatus(String jobId, ApplicationStatus status)
    {
        Job job = getApplicationById(jobId);

        if (job == null)
        {
            return null;
        }

        job.setApplicationStatus(status);

        job.setModifiedDate(CurrentDate.getCurrentDate());

        return jobsRepository.save(job);
    }

}
