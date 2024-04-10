package com.mehrobf.AppliedJobsApplication;

import java.time.LocalDate;
import java.util.Date;

public class CurrentDate
{
    public static Date getCurrentDate()
    {
        LocalDate currentDate = LocalDate.now();
        return java.sql.Date.valueOf(currentDate);
    }
}
