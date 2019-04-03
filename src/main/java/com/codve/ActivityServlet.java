package com.codve;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

@WebServlet(
        name = "storeServlet",
        urlPatterns = "/do/*"
)
public class ActivityServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        this.recordSessionActivity(request);
        this.viewSessionActivity(request, response);
    }

    private void recordSessionActivity(HttpServletRequest request) {
        // 从请求中获取session
        HttpSession session = request.getSession();
        // 每个session都存储了一个Vector, Vector是线程安全的.
        // Vector存储了多个PageVisit
        if (session.getAttribute("activity") == null) {
            session.setAttribute("activity", new Vector<PageVisit>());
        }
        // 从session中获取数据
        @SuppressWarnings("unchecked")
        Vector<PageVisit> visits =
            (Vector<PageVisit>) session.getAttribute("activity");
        if (!visits.isEmpty()) {
            PageVisit last = visits.lastElement(); // 获取最后一次访问信息
            last.setLeftTimestamp(System.currentTimeMillis());
        }
        PageVisit current = new PageVisit();
        current.setEnteredTimestamp(System.currentTimeMillis());
        if (request.getQueryString() == null) {
            current.setRequest(request.getRequestURL().toString());
        } else {
            current.setRequest(request.getRequestURL() + "?" + request.getQueryString());
        }
        try {
            current.setIpAddress(InetAddress.getByName(request.getRemoteAddr()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        visits.add(current);
    }

    private void viewSessionActivity(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewSessionActivity.jsp")
                .forward(request, response);
    }
}
