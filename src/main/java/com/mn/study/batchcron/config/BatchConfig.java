package com.mn.study.batchcron.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
  private static final String ISOLATION = "ISOLATION_DEFAULT";
  private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

  @Bean
  public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
    return jobRegistryBeanPostProcessor;
  }
/*
  @Bean
  public BatchConfigurer configurer(DataSource dataSource) throws Exception {
    PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
    JobRepository jobRepository = initJobRepository(dataSource, transactionManager);

    return new BatchConfigurer() {
      @Override
      public JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTransactionManager(transactionManager);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
      }

      @Override
      public PlatformTransactionManager getTransactionManager() throws Exception {
        return transactionManager;
      }

      @Override
      public JobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        launcher.afterPropertiesSet();
        return launcher;
      }

      @Override
      public JobExplorer getJobExplorer() throws Exception {
        JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
      }
    };
  }

  private JobRepository initJobRepository(
      DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
    JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
    factoryBean.setValidateTransactionState(true);
    factoryBean.setDataSource(dataSource);
    factoryBean.setTransactionManager(transactionManager);
    factoryBean.setDatabaseType(DatabaseType.POSTGRES.name());
    factoryBean.setIsolationLevelForCreate(ISOLATION);
    return factoryBean.getObject();
  }*/
}
