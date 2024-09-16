package me.quyen.webcomponents;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DemoXmlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURL = req.getRequestURL().toString();
        resp.getWriter().write(
                String.format("<h1>Hi DemoXmlServlet at <i><b>url:%s</b></i></h1>",requestURL)
        );
    }
}