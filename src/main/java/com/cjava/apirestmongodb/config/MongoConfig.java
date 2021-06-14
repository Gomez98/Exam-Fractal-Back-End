package com.cjava.apirestmongodb.config;
/*
 * Aplicativo desarrollado para la clase de Java Expert
 * Autor: CJavaPeru/Edwin Maravi (emaravi@gmail.com)
 * Version 0.001
 * www.cjavaperu.com
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages="com.cjava.apirestmongodb.repository")
public class MongoConfig extends AbstractMongoConfiguration {
  
    @Override
    protected String getDatabaseName() {
        return "cjavadatabase";
    }
  
    @Bean
    @Override
    public MongoClient mongoClient() {
        return new MongoClient("localhost", 27017);
    }
}