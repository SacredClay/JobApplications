package com.mehrobf.AppliedJobsApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobByLinkRequest
{
    private String webLink;

    private boolean qualified;

}
