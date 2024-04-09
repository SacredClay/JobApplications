package com.mehrobf.AppliedJobsApplication;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends MongoRepository<Job, ObjectId>
{
    public Job deleteById(String id);

    public Job findById(String id);
}
