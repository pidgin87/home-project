package com.smirnoff.home.ui.components;

import com.smirnoff.home.ui.components.finance.fund.FundListView;
import com.smirnoff.home.ui.components.finance.history.HistoryListView;
import com.smirnoff.home.ui.components.finance.product.ProductListView;
import com.smirnoff.home.ui.model.security.UserModel;
import com.smirnoff.home.ui.service.session.UserSessionService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * The main view is a top-level placeholder for other views.
 */
@CssImport("./styles/global-styles.css")
public class MainView extends AppLayout {

    private H2 viewTitle;

    private static final String LOGOUT_SUCCESS_URL = "/login";

    public MainView(UserSessionService userSessionService) {
        setPrimarySection(Section.DRAWER);
        addDrawerContent(userSessionService.getUser());
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent(UserModel user) {
//        H1 appName = new H1("Renwo");
//        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

//        Header header = new Header(new Paragraph(appName));
//        header.add();
//        header.add(getLogoutButton());

        HorizontalLayout layout = new HorizontalLayout();

        // Configure styling for the header
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        layout.add(getUserHeader(user));

        Scroller scroller = new Scroller(createNavigation());
        addToDrawer(layout, scroller, createFooter());
    }

    private static Component getLogoutButton() {
        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });
        return new Paragraph(logoutButton);
    }

    private Component getUserHeader(UserModel user) {
        HorizontalLayout userInfo = new HorizontalLayout();
        userInfo.setAlignItems(FlexComponent.Alignment.CENTER);

        Image userImage = new Image(user.picture(), "User Image");
        userImage.setClassName("avatar");
        userImage.setWidth(32, Unit.PIXELS);
        userImage.setHeight(32, Unit.PIXELS);
        userImage.getStyle().setPadding("var(--lumo-space-wide-s)");
        userInfo.add(userImage);

        Span span = new Span(user.firstName());
        userInfo.add(span);

        return userInfo;
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
        return new Footer();
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
