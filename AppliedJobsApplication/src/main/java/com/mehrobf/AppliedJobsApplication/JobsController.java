package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobsController
{
    @Autowired
    private JobsService jobsService;

    @GetMapping
    public ResponseEntity<List<Job>> getAppliedJobs()
    {
        List<Job> appliedJobs = jobsService.getAppliedJobs();

        return new ResponseEntity<>(appliedJobs, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Job> createJobApplication(@RequestBody JobRequest jobRequest)
    {
        Job createdJob = jobsService.createJobApplication(jobRequest);
        return new ResponseEntity<>(createdJob, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{jobId}/delete")
    public ResponseEntity<Job> deleteJobApplication(@PathVariable("jobId") String jobId)
    {
        Job deletedJob = jobsService.deleteJobApplication(jobId);

        if (deletedJob == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deletedJob, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getApplicationById(@PathVariable String jobId)
    {
        Job result = jobsService.getApplicationById(jobId);

        if (result == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{jobId}/status")
    public ResponseEntity<Job> updateApplicationStatus(@PathVariable String jobId,
                                                       @RequestParam() ApplicationStatus status)
    {
        Job updatedJob = jobsService.updateApplicationStatus(jobId, status);

        if (updatedJob == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }
}
