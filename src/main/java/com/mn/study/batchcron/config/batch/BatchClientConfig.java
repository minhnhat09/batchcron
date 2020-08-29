package com.mn.study.batchcron.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mn.study.batchcron.config.batch.enumbatch.BatchJobs.GET_CLIENTS;
import static com.mn.study.batchcron.config.batch.enumbatch.BatchJobs.STEP_TASLET_GET_CLIENTS;
@Configuration
public class BatchClientConfig {

  @Bean
  public Job clientBatchJob(JobBuilderFactory jobs, Step batchClientStep) {
    return jobs.get(GET_CLIENTS)
        .preventRestart()
        .incrementer(new RunIdIncrementer())
        .start(batchClientStep)
        .build();
  }

  @Bean
  public Step batchClientStep(StepBuilderFactory steps, BatchClientTasklet batchClientTasklet) {
    return steps.get(STEP_TASLET_GET_CLIENTS).tasklet(batchClientTasklet).build();
  }
}

