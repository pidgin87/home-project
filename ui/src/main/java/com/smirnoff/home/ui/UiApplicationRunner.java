package com.smirnoff.home.ui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@Theme(value = "home-project-ui", variant = Lumo.DARK)
@ComponentScan(value = {"com.smirnoff.home.platform", "com.smirnoff.home.ui"})
public class UiApplicationRunner implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(UiApplicationRunner.class, args);
    }

}
