package com.smirnoff.home.finance.history.shell.command.flyway;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class FlywayFactory {

    private final DataSource dataSource;

    public FluentConfiguration createFlyway() {
        return Flyway.configure()
                .createSchemas(false)
                .dataSource(dataSource);
    }
}
