package com.namkit.namki.practiceintent;

import java.io.Serializable;

/**
 * Created by namki on 2018-02-25.
 */

public class Data implements Serializable {
    public String name;
    public int age;

    public Data(){}

    public Data(String name, int age){
        this.name = name;
        this.age = age;
    }
}
