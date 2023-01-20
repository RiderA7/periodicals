package com.epam.Per1.command;

import com.epam.Per1.command.impl.*;

public enum CommandType {

    ADMINUSERSGET(new AdminUsersGetCommand()),
    ADMINUSERSPOST(new AdminUsersPostCommand()),
    LOGINPOST(new LoginCommand()),
    LOGINGET(new LoginGetCommand()),
    ACCOUNTGET(new AccountCommand()),
    ADMINGET(new AdminCommand()),
    ACCOUNTLOGOUTGET(new LogoutCommand()),
    ADMINLOGOUTGET(new LogoutCommand()),
    REGISTERPOST(new RegisterCommand()),
    REGISTERGET(new RegisterGetCommand()),
    ACCOUNTPROFILEGET(new AccountProfileGetCommand()),
    ACCOUNTUPDATEGET(new AccountUpdateGetCommand()),
    ACCOUNTUPDATEPOST(new AccountUpdatePostCommand()),
    ACCOUNTREPLENISHGET(new AccountReplenishGetCommand()),
    ACCOUNTREPLENISHPOST(new AccountReplenishPostCommand()),
    ACCOUNTUPDATEPASSGET(new AccountChangePassGetCommand()),
    ACCOUNTUPDATEPASSPOST(new AccountChangePassPostCommand()),
    ACCOUNTSUBSCRIPTIONSGET(new AccountSubscribesGetCommand()),
    ACCOUNTSUBSCRIBEPOST(new AccountSubscribesPostCommand()),
    TOPICSGET(new TopicsGetCommand()),
    TOPICSPOST(new TopicsPostCommand()),
    PUBLICATIONSGET(new PublicationsGetCommand()),
    PUBLICATIONSPOST(new PublicationsPostCommand()); //,

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
