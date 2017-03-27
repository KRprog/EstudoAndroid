package com.example.cristiane_aula.prjlanchonete.model;

/**
 * Created by RAFAEL on 10/03/2017.
 */

public class Credentials {
    private String User;
    private String Password;

    public Credentials(String user, String password) {
        this.User = user;
        this.Password = password;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
