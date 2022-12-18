package com.epam.Per1.Command;

import com.epam.Per1.Command.impl.LoginCommand;

public enum CommandType {

    SIGNINPOST(new LoginCommand()); //,

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
