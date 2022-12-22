package com.epam.Per1.command;

import com.epam.Per1.command.impl.EmptyCommand;
import com.epam.Per1.command.impl.LoginCommand;
import com.epam.Per1.command.impl.LoginGetCommand;
import com.epam.Per1.command.impl.LogoutCommand;

public enum CommandType {

    ACCOUNTLOGINPOST(new LoginCommand()),
    ACCOUNTLOGINGET(new LoginGetCommand()),
    ACCOUNTGET(new EmptyCommand()),
    ACCOUNTLOGOUTGET(new LogoutCommand()),
    ADMINLOGOUTGET(new LogoutCommand()); //,

//    INFOCONTACTGET(new ContactsPageCommand());

    private ActionCommand command;

    CommandType(ActionCommand command){
        this.command = command;
    }

    public ActionCommand getCommand(){
        return command;
    }

    public static ActionCommand getCurrentCommand(String action) {
        return CommandType.valueOf(action.toUpperCase()).command;
    }
}
