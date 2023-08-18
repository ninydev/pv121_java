package org.itstep.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // https://projectlombok.org/features/ - создать все геттеры и сеттеры
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // Синтаксис C# - в рамках JAVA такого не существует
    // public String val { get; set};


    private String name;

    private String email;

    private String password;

//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getEmail() {
//        return email;
//    }


//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
}
