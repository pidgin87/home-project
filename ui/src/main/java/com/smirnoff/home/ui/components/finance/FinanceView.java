package com.smirnoff.home.ui.components.finance;

import com.smirnoff.home.ui.components.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Finance module")
@Route(value = "finance", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class FinanceView extends HorizontalLayout {
}
