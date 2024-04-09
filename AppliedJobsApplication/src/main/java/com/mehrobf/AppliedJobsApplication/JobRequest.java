package com.mehrobf.AppliedJobsApplication;

import lombok.Data;

import java.util.Date;


@Data
public class JobRequest
{
    private int id;

    private String webLink;

    private String title;

    private String company;

    private float pay;

    private boolean remote;

    private boolean qualified;

    private boolean customResume;

    private Date applicationDate;

    private JobBoard jobBoard;
}