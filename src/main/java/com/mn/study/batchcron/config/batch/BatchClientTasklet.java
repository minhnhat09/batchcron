package com.mn.study.batchcron.config.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class BatchClientTasklet implements Tasklet {
  private static final Logger LOGGER = LoggerFactory.getLogger(BatchClientTasklet.class);

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
      throws Exception {
    LOGGER.info("Starttttt");
    return RepeatStatus.FINISHED;
  }
}
