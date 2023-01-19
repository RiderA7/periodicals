package com.epam.Per1.utils;

import com.epam.Per1.dto.PublicationDTO;
import com.epam.Per1.dto.SubscriptionDTO;
import com.epam.Per1.dto.TopicDTO;
import com.epam.Per1.entity.*;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Mapper {

    private static Logger log = LogManager.getLogger(Mapper.class);
    private static final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());
    private static final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());

    public static User toUser(UserDTO userDTO){
        User user = userService.getByLogin(userDTO.getLogin());
        return new User.Builder()
                .setId(user.getId())
                .setLogin(userDTO.getLogin())
                .setName(userDTO.getName())
                .setMoney(Double.valueOf(userDTO.getMoney().doubleValue()*100.0).intValue())
                .setIsBlocked(userDTO.isBlocked()?1:0)
                .setRoleId(userDTO.getRoleId())
                .getUser();
    }

    public static UserDTO toUserDTO(User user){
        log.debug("found user = " + user);
        UserRole userRole;
        try {
            userRole = DaoFactory.getInstance().getUserRoleDao().getUserRole(user.getRoleId());
//            log.debug("DAO");
        } catch (DbException e) {
            userRole = new UserRole.Builder().setId(1).setUserRole("USER").getUserRole();
//            log.debug("EXEPT");
        }
//        log.debug("userRole="+userRole);
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .roleId(user.getRoleId())
                .role(userRole.getUserRole())
                .money(BigDecimal.valueOf(user.getMoney()))
                .isBlocked(user.isBlocked())
                .build();
    }

    public static Subscription toSubscription(SubscriptionDTO subscriptionDTO){
        return new Subscription.Builder()
                .setId(subscriptionDTO.getId())
                .setUser(Mapper.toUser(subscriptionDTO.getUser()))
                .setPublication(Mapper.toPublication(subscriptionDTO.getPublication()))
                .setStatus(subscriptionDTO.getStatus().equals("ACTIVE") ? 1 : 2)
                .getSubscription();
    }
    public static SubscriptionDTO toSubscriptionDTO(Subscription subscription){
        return SubscriptionDTO.builder()
                .id(subscription.getId())
                .user(Mapper.toUserDTO(subscription.getUser()))
                .publication(Mapper.toPublicationDTO(subscription.getPublication()))
                .status(subscription.getStatus() == 1 ? "ACTIVE" : "EXPIRED")
                .build();
    }

    public static Publication toPublication(PublicationDTO publicationDTO){
        return new Publication.Builder()
                .setId(publicationDTO.getId())
                .setName(publicationDTO.getName())
                .setTopic(topicService.getByLogin(publicationDTO.getTopic()))
                .setPrice(publicationDTO.getPrice().doubleValue())
                .getPublication();
    }
    public static PublicationDTO toPublicationDTO(Publication publication){
        return PublicationDTO.builder()
                .id(publication.getId())
                .name(publication.getName())
                .topic(publication.getTopic().getName())
                .price(BigDecimal.valueOf(publication.getPrice()))
                .build();
    }

    public static Topic toTopic(TopicDTO topicDTO){
        return new Topic.Builder()
                .setId(topicDTO.getId())
                .setName(topicDTO.getName())
                .getTopic();
    }
    public static TopicDTO toTopicDTO(Topic topic){
        return TopicDTO.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
    }
}
