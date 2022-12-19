package com.epam.Per1.web.servlet.account;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/account1")
public class AccountServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AccountServlet.class.getName());


}
