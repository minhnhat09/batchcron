package com.mn.study.batchcron.config.jobs;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Trigger {
  String name() default "";

  // FeedType feedType() default FeedType.EMPTY;

  String runCheck() default "";

  String repeat() default "";

  String job();
}
