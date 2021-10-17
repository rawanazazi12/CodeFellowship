package com.codeFellows.codeFellowship.domain;

import javax.persistence.*;
import java.security.Timestamp;


@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String body;
    Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name="username")
    ApplicationUser applicationUser;

    public Post(){}

    public Post(String body, Timestamp createdAt, ApplicationUser applicationUser) {
        this.body = body;
        this.createdAt = createdAt;
        this.applicationUser = applicationUser;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getAppUser() {
        return applicationUser;
    }

    public void setAppUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}

