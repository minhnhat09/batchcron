package com.mn.study.batchcron.config.jobs;

import com.mn.study.batchcron.config.meta.BatchJob;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.lang.annotation.*;
import java.util.Objects;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(AddFeedJobs.FeedJobBeanDefinitionRegistrar.class)
public @interface AddFeedJobs {
  BatchJob[] forJobs() default {};

  class FeedJobBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(
        AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
      for (BatchJob job :
          ((BatchJob[])
              Objects.requireNonNull(
                      importingClassMetadata.getAnnotationAttributes(AddFeedJobs.class.getName()))
                  .get("forJobs"))) {
        registry.registerBeanDefinition(
            job.getName(),
            BeanDefinitionBuilder.genericBeanDefinition(JobDetailFactoryBean.class)
                .addPropertyValue("jobClass", job.getJobClass())
                .addPropertyValue("durability", job.isDurable())
                .addPropertyValue("name", job.getName())
                .addPropertyValue("group", job.getGroup())
                .getBeanDefinition());
      }
    }
  }
}
