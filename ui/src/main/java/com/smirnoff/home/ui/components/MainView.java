package com.smirnoff.home.ui.components;

import com.smirnoff.home.ui.components.finance.fund.FundListView;
import com.smirnoff.home.ui.components.finance.history.HistoryListView;
import com.smirnoff.home.ui.components.finance.product.ProductListView;
import com.smirnoff.home.ui.configuration.security.model.User;
import com.smirnoff.home.ui.configuration.security.model.UserSession;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainView extends AppLayout {

    private H2 viewTitle;

    private static final String LOGOUT_SUCCESS_URL = "/";

    public MainView(UserSession userSession) {
        User user = userSession.getUser();

        setPrimarySection(Section.DRAWER);
        addDrawerContent(user);
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent(User user) {
        H1 appName = new H1("home-project");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        header.add(new H1("Hello %s!".formatted(user.getFirstName())));
        header.add(new Paragraph("Your email is %s".formatted(user.getEmail())));

        header.add(new Image(user.getPicture(), "User Image"));

        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });
        header.add(logoutButton);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        SideNavItem financeModule = new SideNavItem("Finance", "finance", VaadinIcon.PIGGY_BANK.create());
        financeModule.addItem(new SideNavItem("Funds", FundListView.class, VaadinIcon.SCISSORS.create()));
        financeModule.addItem(new SideNavItem("Products", ProductListView.class, VaadinIcon.MONEY.create()));
        financeModule.addItem(new SideNavItem("History", HistoryListView.class, VaadinIcon.ARCHIVES.create()));

        nav.addItem(financeModule);

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
