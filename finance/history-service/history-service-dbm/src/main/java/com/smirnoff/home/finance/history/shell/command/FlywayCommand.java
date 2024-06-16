package com.smirnoff.home.finance.history.shell.command;

import com.smirnoff.home.finance.history.shell.command.flyway.FlywayFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class FlywayCommand {

    private final FlywayFactory flywayFactory;

    @ShellMethod(key = "migrate", value = "Run script migrations that placed in the offline catalog")
    public void offlineMigrate() {
        flywayFactory.createFlyway()
                .locations("/migration")
                .load()
                .migrate();
    }

    @ShellMethod(key = "clean", value = "Database should be cleaned")
    public void clean() {
        flywayFactory.createFlyway()
                .load()
                .clean();
    }
}
