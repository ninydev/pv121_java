package org.itstep.entities;

import lombok.Data;

@Data // https://projectlombok.org/features/ - создать все геттеры и сеттеры
public class User {
    // Синтаксис C# - в рамках JAVA такого не существует
    // public String val { get; set};


    private String email;

//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getEmail() {
//        return email;
//    }

    private String password;
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
}
