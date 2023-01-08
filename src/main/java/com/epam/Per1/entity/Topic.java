package com.epam.Per1.entity;

public class Topic extends Entity{

    String name;
    int pubs = 0;
    boolean pubsUpdated = false;

    public String getName() {
        return name;
    }

    public int getPubs() {
        return pubs;
    }

    public boolean isPubsUpdated(){
        return pubsUpdated;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", pubs='" + pubs + '\'' +
                '}';
    }

    public static class Builder{
        Topic topic = new Topic();

        public Topic getTopic(){
            return topic;
        }

        public Builder setId(int id){
            topic.setId(id);
            return this;
        }
        public Builder setName(String name){
            topic.name = name;
            return this;
        }
        public Builder setPubs(int pubs){
            topic.pubs = pubs;
            topic.pubsUpdated = true;
            return this;
        }
    }
}
