package com.edmarzungo.pedidosnamao.security;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tokenId;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public Token() {
    }

    public Token(UUID tokenId, String token, LocalDateTime createdAt, LocalDateTime expiredAt, LocalDateTime validatedAt, User user) {
        this.tokenId = tokenId;
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.validatedAt = validatedAt;
        this.user = user;
    }

    public UUID getTokenId() {
        return tokenId;
    }

    public void setTokenId(UUID tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public LocalDateTime getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(LocalDateTime validatedAt) {
        this.validatedAt = validatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(tokenId, token1.tokenId) && Objects.equals(token, token1.token) && Objects.equals(createdAt, token1.createdAt) && Objects.equals(expiredAt, token1.expiredAt) && Objects.equals(validatedAt, token1.validatedAt) && Objects.equals(user, token1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenId, token, createdAt, expiredAt, validatedAt, user);
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", createdAt=" + createdAt +
                ", expiredAt=" + expiredAt +
                ", validatedAt=" + validatedAt +
                ", user=" + user +
                '}';
    }
}
