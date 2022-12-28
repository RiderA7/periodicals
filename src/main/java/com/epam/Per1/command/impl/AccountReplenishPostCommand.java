package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AccountReplenishPostCommand implements ActionCommand {
    private final UserService userService = new UserService();
    private static Logger log = LogManager.getLogger(AccountReplenishPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int deposit = 0;
        try {
            deposit = Integer.parseInt(req.getParameter("replenish"));
            if (!Validator.validateDeposit(deposit)) {
                log.info("invalid deposit format was received:" + deposit);
                req.setAttribute("err", "Deposit not valid (must be positive)");
                return new CommandResult(Pages.USER_REPLENISH);
            }
            boolean updated = false;
            User newUser = null;
            try {
                newUser = (User) req.getSession().getAttribute("user");
                log.info("User " + newUser.getName() + " try to replenish account by " + deposit);
                log.info("Balance before: " + newUser.getMoney());
                newUser = buildUser(newUser, deposit);
                updated = userService.updateUser(newUser);
            } catch (NullPointerException e) {
                log.error("No user logged in for replenish!");
            }
            if (updated) {
                req.getSession().setAttribute("user", newUser);
                log.info("User " + newUser.getName() + " replenished account by " + deposit);
                log.info("Balance after: " + newUser.getMoney());
                return new CommandResult(Pages.USER_PROFILE);
            } else return new CommandResult(Pages.USER_REPLENISH);
        } catch (NullPointerException e){
            log.error("Error during replenishing account");
            return new CommandResult(Pages.USER_UPDATE);
        }
    }

    private User buildUser(User user, int deposit) throws NullPointerException{
        return new User.Builder()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setRoleId(user.getRoleId())
                .setMoney((int) (user.getMoney() + deposit) * 100)
                .setBlocked(user.isBlocked() ? 1 : 0)
                .getUser();
    }
}
