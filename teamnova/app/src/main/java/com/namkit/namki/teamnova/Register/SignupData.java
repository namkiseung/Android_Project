package com.namkit.namki.teamnova.Register;

import java.io.Serializable;

/**
 * Created by namki on 2018-03-23.
 */


public class SignupData implements Serializable {
    public String name;
    public String age;

    public SignupData(){ }

    public SignupData(String name, String age){
        this.name = name;
        this.age = age;
    }
}