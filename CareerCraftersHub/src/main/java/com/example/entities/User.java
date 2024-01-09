package com.example.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String name;

    private String password;

    private boolean verified;

    private String verificationToken;

    private LocalDateTime createdAt;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] profilePhoto;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "user")
    @JsonManagedReference
    private List<Post> posts;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "user")
    @JsonManagedReference
    private List<Comment> comments;

    public User() {
        this.createdAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
