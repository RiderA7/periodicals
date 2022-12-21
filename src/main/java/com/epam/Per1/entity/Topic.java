package com.epam.Per1.entity;

public class Topic {

    int topic_id;
    String topic_name;

    public int getId() {
        return topic_id;
    }

    public String getName() {
        return topic_name;
    }

    public static class Builder{
        Topic topic = new Topic();

        public Topic getTopic(){
            return topic;
        }

        public Builder setId(int id){
            topic.topic_id = id;
            return this;
        }

        public Builder setName(String name){
            topic.topic_name = name;
            return this;
        }
    }
}
