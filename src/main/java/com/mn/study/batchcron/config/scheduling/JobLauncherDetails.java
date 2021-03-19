package com.mn.study.batchcron.config.scheduling;

import com.mn.study.batchcron.config.meta.Feed;
import com.mn.study.batchcron.config.meta.FeedType;
import com.mn.study.batchcron.config.meta.ParamConstants;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class JobLauncherDetails extends QuartzJobBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobLauncherDetails.class);

  @Autowired private JobRegistry jobRegistry;

  @Autowired private JobLauncher jobLauncher;

  @Autowired private BatchThreadLog batchThreadLog;

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext)
      throws JobExecutionException {
    JobDetailImpl jobDetail = (JobDetailImpl) jobExecutionContext.getJobDetail();
    JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
    LOGGER.info(jobDetail.toString());
    print(jobDataMap);

    FeedType feedType = FeedType.findForId((String) jobDataMap.get("feedTypeId"));
    Feed feed = (Feed) jobDataMap.get(ParamConstants.FEED);

    JobParameters jobParameters = getJobParameters(feed, feedType);
    Job job;
    JobExecution jobExecution;
    try {
      job = jobRegistry.getJob(jobDetail.getName());
      batchThreadLog.beforeRun(jobParameters, job);
      jobExecution = jobLauncher.run(job, jobParameters);
    } catch (NoSuchJobException
        | JobExecutionAlreadyRunningException
        | JobRestartException
        | JobParametersInvalidException
        | JobInstanceAlreadyCompleteException e) {
      LOGGER.error("", e);
      batchThreadLog.afterRun(jobParameters);
      throw new JobExecutionException(e.getMessage(), e);
    }
    batchThreadLog.afterRun(jobParameters);
    LOGGER.info("JobExecution" + jobExecution.toString());
  }

  private JobParameters getJobParameters(Feed feed, FeedType feedType) {
    return new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
  }

  private void print(JobDataMap jobDataMap) {}
}
