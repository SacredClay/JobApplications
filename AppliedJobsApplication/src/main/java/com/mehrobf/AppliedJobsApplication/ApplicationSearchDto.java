package com.mehrobf.AppliedJobsApplication;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ApplicationSearchDto
{
    private String title;

    private String company;

    private Float startingPay;

    private Float endingPay;

    private Boolean remote;

    private Date startDate;

    private Date endDate;

    private ApplicationStatus applicationStatus;

    private JobBoard jobBoard;
}


