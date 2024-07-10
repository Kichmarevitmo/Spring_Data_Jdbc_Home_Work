package ru.easyum.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
