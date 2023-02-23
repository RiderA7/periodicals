package com.epam.Per1.command;

/**
 * The {@code CommandResult} class
 * contains two fields -
 * page and isRedirect,
 * that are used with a controller to find out where and how
 * a request and response should be processed after the controller.
 *
 * @author Alexander Bukhalenkov
 */
public class CommandResult {
    private String page;
    private boolean redirect;

    public CommandResult() {
    }

    public CommandResult(String page) {
        this.page = page;
        redirect = false;
    }

    public CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect(boolean redirect){
        this.redirect = redirect;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
