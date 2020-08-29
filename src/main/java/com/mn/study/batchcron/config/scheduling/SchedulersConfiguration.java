package com.mn.study.batchcron.config.scheduling;

import com.mn.study.batchcron.config.batch.enumbatch.BatchJobs;
import com.mn.study.batchcron.config.jobs.AddTriggers;
import com.mn.study.batchcron.config.jobs.Trigger;
import org.springframework.context.annotation.Configuration;

@Configuration
@AddTriggers({
        @Trigger(name = "GET_CLIENTS", job= BatchJobs.GET_CLIENTS)
})
public class SchedulersConfiguration {
}
