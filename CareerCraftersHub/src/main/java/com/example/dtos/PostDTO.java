package com.example.dtos;

import java.time.LocalDateTime;

public class PostDTO {
    private String text;

    private boolean visible;

    private LocalDateTime createdAt;

    public PostDTO() {
        this.createdAt = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
