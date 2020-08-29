package com.mn.study.batchcron.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

  private static final String FORUM_DATABASE = "FORUM_DATABASE";

  @Bean(name = FORUM_DATABASE)
  @Primary
  public DataSource dataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:postgresql://localhost:5432/forumapi");
    dataSourceBuilder.username("postgres");
    dataSourceBuilder.password("1109");
    return dataSourceBuilder.build();
  }

}
