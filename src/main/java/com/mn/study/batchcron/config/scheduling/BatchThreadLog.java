package com.mn.study.batchcron.config.scheduling;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;

@Component
public class BatchThreadLog {
  

  public void afterRun(JobParameters jobParameters) {
    
  }

  public void beforeRun(JobParameters jobParameters, Job job) {
  }
}
