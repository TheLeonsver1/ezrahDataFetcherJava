package com.ezrah.datafetcher.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoAuditing;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.ezrah.datafetcher.repositories"})
@EnableArangoAuditing
public class ArangoConfig implements ArangoConfiguration {
    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host("127.0.0.1", 8529)
                .user("root")
                .password("openSesame");
    }

    @Override
    public String database() {
        return "ezrah";
    }
}
