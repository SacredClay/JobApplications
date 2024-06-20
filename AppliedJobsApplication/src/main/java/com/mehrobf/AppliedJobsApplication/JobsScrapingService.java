package com.mehrobf.AppliedJobsApplication;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

@Service
public class JobsScrapingService
{
    public Job createLinkedInJob(String webLink, boolean qualified) throws IOException
    {
        String title = "g";
        String company = "g";

        try {
            Document document = Jsoup.connect("https://www.linkedin.com/jobs/view/3947153090")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();

            String jobTitle = ".top-card-layout__title.font-sans.text-lg.papabear\\:text-xl.font-bold.leading-open.text-color-text.mb-0.topcard__title";

            String jobCompany = "a.topcard__org-name-link.topcard__flavor--black-link[data-tracking-control-name=public_jobs_topcard-org-name]";

            title = getElementValue(document, jobTitle);
            company = getElementValue(document, jobCompany);
        }
        catch (Exception e)
        {

        }

        Job job = new Job();
        job.setTitle(title);
        job.setCompany(company);
        return job;
    }

    private String getElementValue(Document document, String jobField)
    {
        Elements elements = document.select(jobField);

        return elements.get(0).childNode(0).toString();
    }
}
