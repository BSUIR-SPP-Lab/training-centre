package com.bsuir.trainingcenter.entity.view;

import java.util.Objects;

public class Login {

    private String password;
    private String login;

    public Login(String password, String login) {
        this.password = password;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return Objects.equals(password, login1.password) &&
                Objects.equals(login, login1.login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(password, login);
    }

    @Override
    public String toString() {
        return "Login{" +
                "password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
