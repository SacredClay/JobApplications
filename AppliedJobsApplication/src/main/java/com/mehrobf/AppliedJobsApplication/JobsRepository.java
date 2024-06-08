package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

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
        return mongoTemplate.findById(jobId, Job.class);
    }

    public List<Job> searchJobApplications(ApplicationSearchDto params)
    {
        Query query = buildQueryFromDto(params);

        return mongoTemplate.find(query, Job.class);
    }

    private Query buildQueryFromDto(ApplicationSearchDto searchDto)
    {
        Query query = new Query();

        if (searchDto.getTitle() != null)
        {
            query.addCriteria(Criteria.where("title").regex(searchDto.getTitle(), "i"));
        }
        if (searchDto.getCompany() != null)
        {
            query.addCriteria(Criteria.where("company").regex(searchDto.getCompany(), "i"));
        }

        if (searchDto.getStartingPay() != null && searchDto.getEndingPay() != null)
        {
            query.addCriteria(Criteria.where("pay").in(searchDto.getStartingPay(), searchDto.getEndingPay()));
        }

        else if (searchDto.getStartingPay() != null)
        {
            query.addCriteria(Criteria.where("pay").gte(searchDto.getStartingPay()));
        }

        else if (searchDto.getEndingPay() != null)
        {
            query.addCriteria(Criteria.where("pay").lte(searchDto.getEndingPay()));
        }

        if (searchDto.getRemote() != null)
        {
            query.addCriteria(Criteria.where("remote").is(searchDto.getRemote()));
        }

        if (searchDto.getStartDate() != null && searchDto.getEndDate() != null)
        {
            query.addCriteria(Criteria.where("applicationDate").gte(searchDto.getStartDate()).lte(searchDto.getEndDate()));
        }

        else if (searchDto.getStartDate() != null)
        {
            query.addCriteria(Criteria.where("applicationDate").gte(searchDto.getStartDate()));
        }

        else if (searchDto.getEndDate() != null)
        {
            query.addCriteria(Criteria.where("applicationDate").lte(searchDto.getEndDate()));
        }

        if (searchDto.getApplicationStatus() != null)
        {
            query.addCriteria(Criteria.where("applicationStatus").is(searchDto.getApplicationStatus()));
        }
        if (searchDto.getJobBoard() != null)
        {
            query.addCriteria(Criteria.where("jobBoard").is(searchDto.getJobBoard()));
        }

        return query;
    }
}
