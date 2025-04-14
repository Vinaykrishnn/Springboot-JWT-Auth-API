package com.jwt.token.model;

public class JwtToken {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public JwtToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtToken(){

    }
}
