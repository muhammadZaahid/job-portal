package com.lawencon.admin.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class GlobalConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "initTable")
	public SpringLiquibase initTable(DataSource dataSource) {
		final SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/db/migration/script/init_table_v1.sql");
		liquibase.setDataSource(dataSource);

		return liquibase;
	}

	@Bean(name = "initData")
	@DependsOn("initTable")
	public SpringLiquibase initData(DataSource dataSource) {
		final SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/db/migration/script/init_data_v1.sql");
		liquibase.setDataSource(dataSource);

		return liquibase;
	}

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setReadTimeout(10000);

		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}
}
