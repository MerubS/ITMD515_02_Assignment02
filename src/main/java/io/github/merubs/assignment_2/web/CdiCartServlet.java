package io.github.merubs.assignment_2.web;
import io.github.merubs.assignment_2.cdi.AppStats;
import io.github.merubs.assignment_2.cdi.CartBean;
import io.github.merubs.assignment_2.cdi.RequestInfo;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cdi/cart")
public class CdiCartServlet extends HttpServlet {
    @Inject CartBean    cart;    // session-scoped
    @Inject RequestInfo reqInfo; // request-scoped
    @Inject AppStats    stats;   // application-scoped

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("add".equalsIgnoreCase(action))    cart.addItem();
        if ("remove".equalsIgnoreCase(action)) cart.removeItem();
        if ("reset".equalsIgnoreCase(action))  cart.reset();

        int hitNumber = stats.hit();

        resp.setContentType("text/plain");
        resp.getWriter().println("CDI Demo: Injection + Scopes");
        resp.getWriter().println("-------------------------");
        resp.getWriter().println("requestId      = " + reqInfo.getRequestId());
        resp.getWriter().println("requestStarted = " + reqInfo.getStartedAt());
        resp.getWriter().println("cart.orderList = " + cart.getOrderList());
        resp.getWriter().println("app.totalHits  = " + hitNumber);
        resp.getWriter().println();
        resp.getWriter().println("Actions: ?action=add  |  ?action=remove  |  ?action=reset");
    }
}
