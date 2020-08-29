package com.mn.study.batchcron.config.scheduling;

import com.mn.study.batchcron.config.jobs.AddFeedJobs;
import com.mn.study.batchcron.config.jobs.AlwaysAutowiringSpringBeanJobFactory;
import com.mn.study.batchcron.config.meta.BatchJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

@Configuration
@AddFeedJobs(forJobs = {BatchJob.GET_CLIENTS})
public class ScheduleJobConfiguration {

  @Bean(name = "springQuartzJobFactory")
  public JobFactory springQuartzJobFactory() {
    return new AlwaysAutowiringSpringBeanJobFactory();
  }

  @Bean(name = "feedScheduler")
  public SchedulerFactoryBean feedScheduler(
      ApplicationContext applicationContext, List<JobDetail> jobDetails, List<Trigger> triggers) {
    SchedulerFactoryBean toReturn = new SchedulerFactoryBean();
    toReturn.setApplicationContext(applicationContext);
    toReturn.setTriggers(triggers.toArray(new Trigger[0]));
    toReturn.setConfigLocation(new ClassPathResource("scheduling/quartz.properties"));
    toReturn.setJobFactory(springQuartzJobFactory());
    toReturn.setApplicationContextSchedulerContextKey("applicationContext");
    toReturn.setJobDetails(jobDetails.toArray(new JobDetail[0]));
    return toReturn;
  }
}
