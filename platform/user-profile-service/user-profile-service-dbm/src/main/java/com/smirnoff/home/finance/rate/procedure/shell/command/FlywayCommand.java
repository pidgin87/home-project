package com.smirnoff.home.finance.rate.procedure.shell.command;

import com.smirnoff.home.finance.rate.procedure.shell.command.flyway.FlywayFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class FlywayCommand {

    private final FlywayFactory flywayFactory;
    @ShellMethod(key = "offline-migrate", value = "Run script migrations that placed in the offline catalog")
    public void offlineMigrate() {
        flywayFactory.createFlyway()
                .locations("/offline")
                .load()
                .migrate();
    }

    @ShellMethod(key = "online-migrate", value = "Run script migrations that placed in the online catalog")
    public void onlineMigrate() {
        flywayFactory.createFlyway()
                .locations("/online")
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
