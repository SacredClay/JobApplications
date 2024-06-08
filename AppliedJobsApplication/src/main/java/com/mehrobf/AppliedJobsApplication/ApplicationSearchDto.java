package com.mehrobf.AppliedJobsApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSearchDto
{
    private String title;

    private String company;

    private Float startingPay;

    private Float endingPay;

    private Boolean remote;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date startDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date endDate;

    private ApplicationStatus applicationStatus;

    private JobBoard jobBoard;
}


