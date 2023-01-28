package com.epam.Per1.exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.servlet.RequestDispatcher.*;

@WebServlet("/AppExceptionHandler")
public class AppExceptionHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Logger log = LogManager.getLogger(AppExceptionHandler.class);


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) request
                .getAttribute("jakarta.servlet.error.exception");
        log.error("ERROR: " + throwable);
        request.getSession().setAttribute("throwable", throwable);
        Integer statusCode = (Integer) request
                .getAttribute("jakarta.servlet.error.status_code");
        request.getSession().setAttribute("statusCode", statusCode);
        String servletName = (String) request
                .getAttribute("jakarta.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        request.getSession().setAttribute("servletName", servletName);
        String requestUri = (String) request
                .getAttribute("jakarta.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        request.getSession().setAttribute("requestUri", requestUri);

        List<String> errors = new ArrayList<>();
        Arrays.asList(
                ERROR_STATUS_CODE,
                ERROR_EXCEPTION_TYPE,
                ERROR_MESSAGE)
                .forEach(e ->
                        errors.add(e+"="+request.getAttribute(e))
                );
        log.error(errors);

        response.sendRedirect(request.getContextPath() + "/error.jsp");


//        // Set response content type
//        response.setContentType("text/html");
//
//        PrintWriter out = response.getWriter();
//        out.write("<html><head><title>Exception/Error Details</title></head><body>");
//        log.debug("statusCode = " + statusCode);
//        if(statusCode != 500){
//            out.write("<h3>Error Details</h3>");
//            out.write("<strong>Status Code</strong>:"+statusCode+"<br>");
//            out.write("<strong>Requested URI</strong>:"+requestUri);
//        }else{
//            out.write("<h3>Exception Details</h3>");
//            out.write("<ul><li>Servlet Name:"+servletName+"</li>");
//            out.write("<li>Exception Name:"+throwable.getClass().getName()+"</li>");
//            out.write("<li>Requested URI:"+requestUri+"</li>");
//            out.write("<li>Exception Message:"+throwable.getMessage()+"</li>");
//            out.write("</ul>");
//        }
//
//        out.write("<br><br>");
//        out.write("<a href=\"/\">Home Page</a>");
//        out.write("</body></html>");
    }
}