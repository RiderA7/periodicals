package com.epam.Per1.entity;

public class Subscription extends Entity{

    private User user;
    private Publication publication;
    private int status;  // 1 - ACTIVE   2 - EXPIRED

    public User getUser() {
        return user;
    }
    public Publication getPublication() {
        return publication;
    }
    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + getId() +
                ", user=" + user +
                ", publication=" + publication +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        Subscription subscription = new Subscription();

        public Subscription getSubscription(){
            return subscription;
        }

        public Builder setId(int id){
            subscription.setId(id);
            return this;
        }
        public Builder setUser(User user){
            subscription.user = user;
            return this;
        }
        public Builder setPublication(Publication publication){
            subscription.publication = publication;
            return this;
        }
        public Builder setStatus(int status){
            subscription.status = status;
            return this;
        }
    }
}
