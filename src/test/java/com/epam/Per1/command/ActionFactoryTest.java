package com.epam.Per1.command;

import com.epam.Per1.command.impl.EmptyCommand;
import com.epam.Per1.command.impl.LoginGetCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ActionFactoryTest {
    @Mock
    HttpServletRequest request;

    ActionFactory actionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        actionFactory = new ActionFactory();
    }

    @Test
    void testDefineCommand_validAction() {
        when(request.getRequestURI()).thenReturn("Per1/login");
        when(request.getContentType()).thenReturn(null);

        ActionCommand result = actionFactory.defineCommand(request);

        assertTrue(result instanceof LoginGetCommand);
    }

    @Test
    void testDefineCommand_invalidAction() {
        when(request.getRequestURI()).thenReturn("Per1/invalid-action");
        when(request.getContentType()).thenReturn(null);

        ActionCommand result = actionFactory.defineCommand(request);

        assertTrue(result instanceof EmptyCommand);
    }
}
