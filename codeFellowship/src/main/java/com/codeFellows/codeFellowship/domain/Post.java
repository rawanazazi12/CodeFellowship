package com.codeFellows.codeFellowship.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
//import java.security.Timestamp;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Transactional
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String body;
    @ManyToOne
    @JoinColumn(name="username")
    ApplicationUser applicationUser;

//    @CreationTimestamp
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp createdAt;

    public Post(){}

    public Post(String body, Timestamp createdAt, ApplicationUser applicationUser) {
        this.body = body;
        this.createdAt = createdAt;
        this.applicationUser = applicationUser;
    }

    public Post(String body, ApplicationUser user) {
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

