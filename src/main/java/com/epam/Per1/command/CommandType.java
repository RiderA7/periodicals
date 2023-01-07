package com.epam.Per1.command;

import com.epam.Per1.command.impl.*;

public enum CommandType {

    ADMINUSERSGET(new AdminUsersGetCommand()),
    ADMINUSERSPOST(new AdminUsersPostCommand()),
    ACCOUNTLOGINPOST(new LoginCommand()),
    ACCOUNTLOGINGET(new LoginGetCommand()),
    ACCOUNTGET(new EmptyCommand()),
    ACCOUNTLOGOUTGET(new LogoutCommand()),
    ADMINLOGOUTGET(new LogoutCommand()),
    ACCOUNTREGISTERPOST(new RegisterCommand()),
    ACCOUNTREGISTERGET(new RegisterGetCommand()),
    ACCOUNTPROFILEGET(new AccountProfileGetCommand()),
    ACCOUNTUPDATEGET(new AccountUpdateGetCommand()),
    ACCOUNTUPDATEPOST(new AccountUpdatePostCommand()),
    ACCOUNTREPLENISHGET(new AccountReplenishGetCommand()),
    ACCOUNTREPLENISHPOST(new AccountReplenishPostCommand()),
    ACCOUNTUPDATEPASSGET(new AccountChangePassGetCommand()),
    ACCOUNTUPDATEPASSPOST(new AccountChangePassPostCommand()),
    TOPICSGET(new TopicsGetCommand()),
    TOPICSPOST(new TopicsPostCommand()); //,

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
