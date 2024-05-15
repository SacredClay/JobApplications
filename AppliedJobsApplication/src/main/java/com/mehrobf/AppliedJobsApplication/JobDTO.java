package com.mehrobf.AppliedJobsApplication;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobDTO
{
    private String webLink;

    private String title;

    private String company;

    private float pay;

    private boolean remote;

    private boolean qualified;

    private boolean customResume;

    private Date applicationDate;

    private ApplicationStatus applicationStatus;

    private JobBoard jobBoard;
}
