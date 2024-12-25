/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Authentification.dto;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class LoginResponse {

    private String token;

    private long expiresIn;

    private Date expiration;

  
    

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Date getExpiration() {
        return expiration;
    }

    public LoginResponse setExpiration(Date expiration) {
        this.expiration = expiration;
        return this;
    }

//    @Override
//    public String toString() {
//        return "LoginResponse{" +
//                "token='" + token + '\'' +
//                ", expiresIn=" + expiresIn +
//                '}';
//    }
//    
    @Override
    public String toString() {
        return "LoginResponse{" + "token=" + token + ", expiresIn=" + expiresIn + ", expiration=" + expiration + '}';
    }

}
