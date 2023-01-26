package com.epam.Per1.filter;

import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.dto.UserRoleDTO;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Mapper;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class AuthFilterTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    private final UserService userService = mock(UserService.class);
    private final FilterChain chain = mock(FilterChain.class);
    private final HttpSession session = mock(HttpSession.class);
    private static Logger log = mock(Logger.class);

    @Test
    public void ifNotAuthorizedThenGoToLoginPage_true() throws IOException, ServletException {
        UserDTO userDTO = UserDTO.builder()
                .id(2)
                .role(new UserRoleDTO(1, "user"))
                .money(BigDecimal.valueOf(0))
                .build();
        User user = Mapper.toUser(userDTO);
        UserDTO userDTONull = null;
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userDTO")).thenReturn(userDTONull);
        when(userService.getById(2)).thenReturn(user);
        when(request.getServletPath()).thenReturn("/account/");
        when(request.getRequestDispatcher(Pages.LOGIN_PAGE)).thenReturn(requestDispatcher);
        doNothing().when(requestDispatcher).forward(request,response);

        AuthFilter authFilter = new AuthFilter(userService, log);
        System.out.println(request.getSession().getAttribute("userDTO"));

        authFilter.doFilter(request,response,chain);



    }

}