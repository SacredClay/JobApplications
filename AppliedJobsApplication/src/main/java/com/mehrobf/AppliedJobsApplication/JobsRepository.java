package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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

    public List<Job> searchJobApplications(Map<String, String> params)
    {
        Query query = new Query();
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            query.addCriteria(createCriteriaParser(entry.getKey(), entry.getValue()));
        }
        return mongoTemplate.find(query, Job.class);
    }

    private Criteria createCriteriaParser(String fieldName, String value)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            // Parse input string into LocalDate using defined format
            LocalDate date = LocalDate.parse(value, formatter);
            return Criteria.where("appliedDate").is(date);
        } catch (DateTimeParseException ignored)
        {}

        try {
            if (value.equals("true") || value.equals("false"))
            {
                return Criteria.where(fieldName).is(Boolean.parseBoolean(value));
            }
        } catch (Exception ignored)
        {}

        try {
            if (fieldName.equals("startingPay"))
            {
                return Criteria.where("pay").gte(Float.parseFloat(value));
            }
            if (fieldName.equals("endingPay"))
            {
                return Criteria.where("pay").lte(Float.parseFloat(value));
            }
        } catch (Exception ignored)
        {}

        return Criteria.where(fieldName).is(value);
    }
}
