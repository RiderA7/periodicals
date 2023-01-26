package com.epam.Per1.filter;

import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Mapper;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class AuthFilterTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final UserService userService = mock(UserService.class);
    private final FilterChain chain = mock(FilterChain.class);
    private final HttpSession session = mock(HttpSession.class);
    private static Logger log = mock(Logger.class);

    @Test
    public void ifNotAuthorizedThenGoToLoginPage_true() throws IOException, ServletException {
        UserDTO userDTO = UserDTO.builder()
                .id(2)
                .build();
        User user = Mapper.toUser(userDTO);
        UserDTO userDTONull = null;
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("userDTO")).thenReturn(userDTO);
        Mockito.when(userService.getById(2)).thenReturn(user);

        AuthFilter authFilter = new AuthFilter();
        authFilter.setUserService(userService);
        authFilter.setLogger(log);

        new AuthFilter().doFilter(request,response,chain);

        Mockito.verify(request.getRequestDispatcher(Pages.LOGIN_PAGE), times(1));
    }

}