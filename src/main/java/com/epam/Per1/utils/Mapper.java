package com.epam.Per1.utils;

import com.epam.Per1.dto.*;
import com.epam.Per1.entity.*;

import java.math.BigDecimal;

public class Mapper {

//    private static Logger log = LogManager.getLogger(Mapper.class);

    public static UserRole toUserRole(UserRoleDTO userRoleDTO){
        return new UserRole(userRoleDTO.getId(), userRoleDTO.getRole());
    }

    public static UserRoleDTO toUserRoleDTO(UserRole userRole){
        return new UserRoleDTO(userRole.getId(), userRole.getUserRole());
    }

    public static User toUser(UserDTO userDTO){
        return new User.Builder()
                .setId(userDTO.getId())
                .setLogin(userDTO.getLogin())
                .setPassword(userDTO.getPassword())
                .setName(userDTO.getName())
                .setMoney(userDTO.getMoney().doubleValue())
                .setIsBlocked(userDTO.isBlocked())
//                .setRoleId(userDTO.getRoleId())
                .setRole(new UserRole(userDTO.getRole().getId(), userDTO.getRole().getRole()))
                .getUser();
    }

    public static UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .password(user.getPassword())
//                .roleId(user.getRole().getId())
                .role(Mapper.toUserRoleDTO(user.getRole()))
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
                .setTopic(toTopic(publicationDTO.getTopic()))
                .setPrice(publicationDTO.getPrice().doubleValue())
                .getPublication();
    }
    public static PublicationDTO toPublicationDTO(Publication publication){
        return PublicationDTO.builder()
                .id(publication.getId())
                .name(publication.getName())
                .topic(toTopicDTO(publication.getTopic()))
                .price(BigDecimal.valueOf(publication.getPrice()))
                .build();
    }

    public static Topic toTopic(TopicDTO topicDTO){
        return new Topic.Builder()
                .setId(topicDTO.getId())
                .setName(topicDTO.getName())
                .setPubs(topicDTO.getPubs())
                .getTopic();
    }
    public static TopicDTO toTopicDTO(Topic topic){
        return TopicDTO.builder()
                .id(topic.getId())
                .name(topic.getName())
                .pubs(topic.getPubs())
                .build();
    }
}
