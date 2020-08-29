package com.mn.study.batchcron.config.meta;

import com.mn.study.batchcron.config.batch.enumbatch.BatchJobs;
import com.mn.study.batchcron.config.scheduling.JobLauncherDetails;

public enum BatchJob {
  GET_CLIENTS(BatchJobs.GET_CLIENTS, JobLauncherDetails.class, true);

  private String name;
  private String group;
  private Class jobClass;
  private boolean durable;

  BatchJob(String name, Class jobClass, boolean durable) {
    this.name = name;
    this.jobClass = jobClass;
    this.durable = durable;
  }

  BatchJob(String name, String group, Class jobClass, boolean durable) {
    this.name = name;
    this.group = group;
    this.jobClass = jobClass;
    this.durable = durable;
  }

  public String getName() {
    return name;
  }

  public String getGroup() {
    return group;
  }

  public Class getJobClass() {
    return jobClass;
  }

  public boolean isDurable() {
    return durable;
  }
}
