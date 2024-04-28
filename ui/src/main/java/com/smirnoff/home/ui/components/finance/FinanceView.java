package com.smirnoff.home.ui.components.finance;

import com.smirnoff.home.ui.components.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinServletRequest;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@PermitAll
@PageTitle("Finance module")
@RouteAlias(value = "", layout = MainView.class)
@Route(value = "/finance", layout = MainView.class)
public class FinanceView extends HorizontalLayout {

    public FinanceView() {
        setAlignItems(Alignment.CENTER);
    }
}
