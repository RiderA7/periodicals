package com.epam.Per1.entity;

public class Publication extends Entity{

    String name;
    double price;
    Topic topic;


    @Override
    public String toString() {
        return "Publication{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", topic=" + topic +
                '}';
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Topic getTopic() {
        return topic;
    }

    public static class Builder {
        Publication publication = new Publication();

        public Publication getPublication() {
            return publication;
        }

        public Builder setId(int id) {
            publication.setId(id);
            return this;
        }
        public Builder setName(String name) {
            publication.name = name;
            return this;
        }
        public Builder setPrice(double price) {
            publication.price = price;
            return this;
        }
        public Builder setTopic(Topic topic) {
            publication.topic = topic;
            return this;
        }
    }
}
