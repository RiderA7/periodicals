package com.epam.Per1.command.impl;

import com.epam.Per1.command.CommandResult;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountChangePassGetCommandTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void RightRedirectTest() throws ServletException, IOException {

        CommandResult path = new AccountChangePassGetCommand().execute(request, response);
        assertEquals(Pages.USER_CHANGE_PASSWORD, path.getPage());
    }

}