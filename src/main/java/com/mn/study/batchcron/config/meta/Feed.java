package com.mn.study.batchcron.config.meta;

import java.util.Date;

public interface Feed {
  String SEPARATOR = ",";

  FeedType getFeedType();

  Date getWindowStart();

  Date getWindowEnd();

  boolean isSchedule();
}
