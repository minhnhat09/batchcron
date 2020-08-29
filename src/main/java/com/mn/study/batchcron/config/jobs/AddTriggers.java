package com.mn.study.batchcron.config.jobs;

import com.mn.study.batchcron.config.meta.FeedType;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({AddTriggers.TriggersBeanDefinitionRegistrar.class})
public @interface AddTriggers {
  Trigger[] value() default {};

  class TriggersBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(
        AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
      AnnotationAttributes[] triggers =
          (AnnotationAttributes[])
              Objects.requireNonNull(
                      importingClassMetadata.getAnnotationAttributes(AddTriggers.class.getName()))
                  .get("value");
      for (AnnotationAttributes triggerAnnotation : triggers) {
        Map<String, Object> jobData = new HashMap<>();
        // FeedType feedType = triggerAnnotation.getEnum("feedType");
        // jobData.put("feedTypeId", Integer.toString(feedType.getId()));
        String runCheck = triggerAnnotation.getString("runCheck");
        if (!runCheck.isEmpty()) {
          jobData.put("runCheck", runCheck);
        }
        String repeat = triggerAnnotation.getString("repeat");
        if (!repeat.isEmpty()) {
          jobData.put("repeat", repeat);
        }
        String beanName = String.format("cronTrigger-%s", triggerAnnotation.getString("name"));
        registry.registerBeanDefinition(
            beanName,
            BeanDefinitionBuilder.rootBeanDefinition(CronTriggerFactoryBean.class)
                .addPropertyReference("jobDetail", triggerAnnotation.getString("job"))
                .addPropertyValue("cronExpression", String.format("${%s}", beanName))
                .addPropertyValue("jobDataAsMap", jobData)
                .getBeanDefinition());
      }
    }
  }
}
