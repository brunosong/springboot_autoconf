package com.brunosong.datasource.config;

import com.brunosong.config.BrunoConditionalOnClass;
import com.brunosong.config.EnableBrunoConfigurationProperties;
import com.brunosong.datasource.BrunoDataSourceAutoConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@BrunoDataSourceAutoConfiguration
@BrunoConditionalOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableBrunoConfigurationProperties( classes = DataSourceProperties.class )
public class DataSourceConfig {

    /* @ConditionalOnMissingBean은 순서도 중요하다 */
    @Bean
    @BrunoConditionalOnClass("com.zaxxer.hikari.HikariDataSource")
    public DataSource hikariDataSource( DataSourceProperties dataSourceProperties ) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setJdbcUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUserName());
        dataSource.setPassword(dataSourceProperties.getPassword());

        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource( DataSourceProperties dataSourceProperties ) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(dataSourceProperties.getDriverClassName()));
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUserName());
        dataSource.setPassword(dataSourceProperties.getPassword());

        return dataSource;
    }

}
