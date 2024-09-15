package me.quyen.servlet_components;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Slf4j
public class MyJakartaServet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        log.info("ORDER:{},In method:`{}` at `{}`"
                ,++Counter.servletCount
                , "public void me.quyen.servlet_components.MyJakartaServet.init() throws ServletException"
                , LocalDateTime.now());
        MyJakartaServet.super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("ORDER:{},In method:`{}` at `{}`"
                ,++Counter.servletCount
                , "public void me.quyen.servlet_components.MyJakartaServet.doGet(HttpServletRequest," +
                        "HttpServletResponse) throws ServletException, IOException"
                , LocalDateTime.now());
        PrintWriter writer = resp.getWriter();
        String requestURI = req.getRequestURI();
        writer.println(String.format(
                "<h1 style=\"color:blue;text-align:center;\">Request-URL:%s</h1>",requestURI
        ));
        writer.println("<h1 style=\"color:blue;text-align:center;\">Response From Servlet</h1>");

    }


    @Override
    public void destroy() {
        log.info("ORDER:{},In method:`{}` at `{}`"
                ,++Counter.servletCount
                , "public void me.quyen.servlet_components.MyJakartaServet.destroy()"
                , LocalDateTime.now());
        MyJakartaServet.super.destroy();
    }



}
