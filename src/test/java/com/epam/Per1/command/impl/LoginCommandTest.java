package com.epam.Per1.command.impl;

import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.UserDao;
import com.epam.Per1.entity.User;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Commands;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class LoginCommandTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    public LoginCommandTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_LoginAndPasswordEmpty() throws Exception {
        LoginCommand command = new LoginCommand();
        String expectedPage = Pages.LOGIN_PAGE;

        when(request.getParameter("login")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");
        when(request.getSession()).thenReturn(session);

        CommandResult result = command.execute(request, response);

        assertEquals(expectedPage, result.getPage());
        verify(session).setAttribute(eq("err"), eq("login.must.be.not.empty"));
        verifyNoMoreInteractions(session, userDao, userService);
    }

    @Test
    public void testExecute_UserNotFound() throws Exception {
        LoginCommand command = new LoginCommand();
        command.setUserService(userService);
        String expectedPage = Pages.LOGIN_PAGE;
        String login = "username";
        char[] password = "password".toCharArray();

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(String.valueOf(password));
        when(userService.login(login, password)).thenReturn(Optional.empty());
        when(request.getSession()).thenReturn(session);

        CommandResult result = command.execute(request, response);

        assertEquals(expectedPage, result.getPage());
        verify(session).setAttribute(eq("err"), eq("login.must.be.valid"));
        verify(userService).login(login, password);
        verifyNoMoreInteractions(session, userService);
    }

    @Test
    public void testExecute_UserIsBlocked() throws Exception {
        LoginCommand command = new LoginCommand();
        command.setUserService(userService);
        String expectedPage = Pages.LOGIN_PAGE;
        String login = "username";
        char[] password = "password".toCharArray();
        UserRole userRole = new UserRole(1,"user");
        User user = new User(1, "asd@asd.com","username","password",userRole,0.0,true);
        user.setBlocked(true);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(String.valueOf(password));
        when(userService.login(login, password)).thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(session);

        CommandResult result = command.execute(request, response);

        assertEquals(expectedPage, result.getPage());
        verify(session).setAttribute(eq("err"), eq("login.account.banned"));
        verify(userService).login(login, password);
        verifyNoMoreInteractions(session, userService);
    }

    @Test
    public void testExecute_UserLoggedIn() throws Exception {
        LoginCommand command = new LoginCommand();
        command.setUserService(userService);
        String expectedPage = Commands.USER_ACCOUNT;
        String login = "username";
        char[] password = "password".toCharArray();
        UserRole userRole = new UserRole(1,"user");
        User user = new User(1, "asd@asd.com","username","password",userRole,0.0,false);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(String.valueOf(password));
        when(request.getSession()).thenReturn(session);
        when(userService.login(login, password)).thenReturn(Optional.of(user));
        when(userService.setUserLogin(user, session)).thenReturn(expectedPage);


        CommandResult result = command.execute(request, response);

        assertEquals(expectedPage, result.getPage());
        assertTrue(result.isRedirect());
//        verifyNoMoreInteractions(session, userService);
    }}