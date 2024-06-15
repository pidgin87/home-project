package com.smirnoff.home.ui.components;


import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * Adds a link that the user has to click to login.
 * <p>
 * This view is marked with {@code @AnonymousAllowed} to allow all users access
 * to the login page.
 */
@Route("/login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    public LoginView(Environment environment) {
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        String clientKey = environment.getProperty("spring.security.oauth2.client.registration.google.client-id");
        if (clientKey == null || clientKey.length() < 32) {
            add(new Paragraph("Could not find OAuth client key in application.properties. "
                    + "Please double-check the key and refer to the README.md file for instructions."));
            return;
        }
        String oauthUri = environment.getProperty("ui.oauth.uri");
        add(new H1("Access to this app via Google"));

        Anchor loginLink = new Anchor(oauthUri, getGoogleImage());
        loginLink.addClassName(LumoUtility.FontSize.XLARGE);
        loginLink.setRouterIgnore(true); // actually navigate away from this app
        add(loginLink);
    }

    private Div getGoogleImage() {
        StreamResource imageResource = new StreamResource("logo.google.png",
                () -> getClass().getResourceAsStream("/images/logo.google.png"));

        Image image = new Image(imageResource, "");
        image.setWidth("70px");
        image.setHeight("70px");
        Div imageDiv = new Div(image);
        imageDiv.setClassName("google-button");
        return imageDiv;
    }
}