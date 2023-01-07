package com.epam.Per1.entity;

public class Topic {

    int id;
    String name;
    int pubs = 0;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPubs() {
        return pubs;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
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
            topic.id = id;
            return this;
        }
        public Builder setName(String name){
            topic.name = name;
            return this;
        }
        public Builder setPubs(int pubs){
            topic.pubs = pubs;
            return this;
        }
    }
}
