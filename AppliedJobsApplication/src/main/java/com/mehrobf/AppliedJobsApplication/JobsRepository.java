package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobsRepository
{
    private final MongoTemplate mongoTemplate;

    @Autowired
    public JobsRepository(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public Job createJob(Job job)
    {
        return mongoTemplate.save(job);
    }

    public Job updateJob(Job job)
    {
        return mongoTemplate.save(job);
    }

    public List<Job> getAllJobs()
    {
        return mongoTemplate.findAll(Job.class);
    }

    public Job deleteJobById(String jobId)
    {
        Job deletedJob = findJobById(jobId);
        if (deletedJob != null)
        {
            mongoTemplate.remove(deletedJob);
        }
        return deletedJob;
    }

    public Job findJobById(String jobId)
    {
        Job job = mongoTemplate.findById(jobId, Job.class);
        return job;
    }

}
