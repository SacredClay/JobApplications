package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/jobsByLink")
public class JobsScrapingController
{
    @Autowired
    private JobsScrapingService jobsScrapingService;

    @PostMapping()
    public ResponseEntity<Job> createJobByLink(@ModelAttribute JobByLinkRequest request) throws IOException
    {
        Job createdJob = jobsScrapingService.createLinkedInJob(request.getWebLink(), false);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }
}
