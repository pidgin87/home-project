package com.smirnoff.home.ui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "home-project-ui", variant = Lumo.DARK)
public class UiApplicationRunner implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(UiApplicationRunner.class, args);
    }

}
