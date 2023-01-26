package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dto.PublicationDTO;
import com.epam.Per1.dto.SubscriptionDTO;
import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.PublicationService;
import com.epam.Per1.service.impl.SubscriptionService;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Commands;
import com.epam.Per1.utils.Mapper;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;

public class AccountSubscribesPostCommand implements ActionCommand {
    private final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());
    private final PublicationService publicationService = new PublicationService(DaoFactory.getInstance().getPublicationDao());
    private final SubscriptionService subscriptionService = new SubscriptionService(DaoFactory.getInstance().getSubscriptionDao());
    private final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());
    private static Logger log = LogManager.getLogger(AccountSubscribesPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message;
        HttpSession session = req.getSession();
        if(session.getAttribute("userDTO") == null){
            message = "authorization.required";
            log.info(message);
            session.setAttribute("err", message);
            return new CommandResult(Pages.WELCOME_PAGE,true);
        }
        UserDTO user = (UserDTO) session.getAttribute("userDTO");
        User userFromDB = userService.getById(user.getId());
        user = Mapper.toUserDTO(userFromDB);
        log.debug("SUBSCRIBE: "+"found user "+user);
        double userMoney = user.getMoney().doubleValue();
        log.debug("SUBSCRIBE: "+"he has "+userMoney);
        if(req.getParameter("pubId") == null) {
            message = "Publication not found";
            log.info(message);
            session.setAttribute("err", message);
            return new CommandResult(Commands.PUBLICATIONS,true);
        }
        PublicationDTO publication =
                Mapper.toPublicationDTO(publicationService.getById(Integer.parseInt(req.getParameter("pubId"))));
        log.debug("SUBSCRIBE: "+"found publication "+publication);
        if(publication == null) {
            message = "Publication not found";
            log.info(message);
            session.setAttribute("err", message);
            return new CommandResult(Commands.PUBLICATIONS,true);
        }
//        Publication publication = optionalPublication.get();
        if(userMoney < publication.getPrice().doubleValue()){
            message = "You have not enough money";
            log.info(message);
            session.setAttribute("err", message);
            return new CommandResult(Commands.PUBLICATIONS,true);
        }
        subscriptionService.create(SubscriptionDTO.builder()
                .user(user)
                .publication(publication)
                .status("ACTIVE")
                .build());
        log.debug("SUBSCRIBE: "+"try to create subscription ");
        user.setMoney(BigDecimal.valueOf(user.getMoney().doubleValue() - publication.getPrice().doubleValue()));
        User updatedUser = Mapper.toUser(user);
        updatedUser.setPassword(userFromDB.getPassword());
        if(userService.update(updatedUser)){
            req.getSession().setAttribute("suc", "subscribe.success");
        } else {
            req.getSession().setAttribute("err", "subscribe.error");
        };
        return new CommandResult(Commands.SUBSCRIPTIONS, true);
    }
}
