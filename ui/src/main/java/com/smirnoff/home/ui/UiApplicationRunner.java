package com.smirnoff.home.ui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(value = {
        "com.smirnoff.home.platform",
        "com.smirnoff.home.finance.history",
        "com.smirnoff.home.ui"
})
@ComponentScan(value = {
        "com.smirnoff.home.platform",
        "com.smirnoff.home.ui"
})
@SpringBootApplication
@Theme(value = "home-project-ui", variant = Lumo.DARK)
public class UiApplicationRunner implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(UiApplicationRunner.class, args);
    }

}
