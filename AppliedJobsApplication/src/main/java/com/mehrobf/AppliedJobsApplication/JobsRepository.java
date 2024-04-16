package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        job.setModifiedDate(CurrentDate.getCurrentDate());
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

    public List<Job> searchJobApplications(ApplicationSearchDto applicationSearchDto)
    {
        Query query = new Query();
        if (applicationSearchDto.getTitle() != null)
        {
            query.addCriteria(Criteria.where("Title").is(applicationSearchDto.getTitle()));
        }

        if (applicationSearchDto.getCompany() != null)
        {
            query.addCriteria(Criteria.where("Company").is(applicationSearchDto.getCompany()));
        }

        if (applicationSearchDto.getStartingPay() != null)
        {
            query.addCriteria(Criteria.where("pay").gte(applicationSearchDto.getStartingPay()));
        }

        if (applicationSearchDto.getEndingPay() != null)
        {
            query.addCriteria(Criteria.where("pay").lte(applicationSearchDto.getEndingPay()));
        }

        if (applicationSearchDto.getRemote() != null)
        {
            query.addCriteria(Criteria.where("remote").is(applicationSearchDto.getRemote()));
        }

        if (applicationSearchDto.getStartDate() != null)
        {
            query.addCriteria(Criteria.where("applicationDate").gte(applicationSearchDto.getStartDate()));
        }

        if (applicationSearchDto.getEndDate() != null)
        {
            query.addCriteria(Criteria.where("applicationDate").lte(applicationSearchDto.getEndDate()));
        }

        if (applicationSearchDto.getApplicationStatus() != null)
        {
            query.addCriteria(Criteria.where("applicationStatus").is(applicationSearchDto.getApplicationStatus()));
        }

        if (applicationSearchDto.getJobBoard() != null)
        {
            query.addCriteria(Criteria.where("jobBoard").is(applicationSearchDto.getJobBoard()));
        }

        return mongoTemplate.find(query, Job.class);
    }
}
