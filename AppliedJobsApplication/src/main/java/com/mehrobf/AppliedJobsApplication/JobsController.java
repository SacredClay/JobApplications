package com.mehrobf.AppliedJobsApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobApplications(@RequestParam(required = false) String title,
                                                           @RequestParam(required = false) String company,
                                                           @RequestParam(required = false) Float startingPay,
                                                           @RequestParam(required = false) Float endingPay,
                                                           @RequestParam(required = false) Boolean remote,
                                                           @RequestParam(required = false) Date startDate,
                                                           @RequestParam(required = false) Date endDate,
                                                           @RequestParam(required = false) ApplicationStatus applicationStatus,
                                                           @RequestParam(required = false) JobBoard jobBoard)
    {
        ApplicationSearchDto applicationSearchDto = new ApplicationSearchDto(title,
                                                                            company,
                                                                            startingPay,
                                                                            endingPay,
                                                                            remote,
                                                                            startDate,
                                                                            endDate,
                                                                            applicationStatus,
                                                                            jobBoard);

        List<Job> foundJobs = jobsService.searchJobApplications(applicationSearchDto);

        if (foundJobs.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foundJobs, HttpStatus.OK);
    }
}
