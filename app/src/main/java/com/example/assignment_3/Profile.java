package com.example.assignment_3;

import java.io.Serializable;

public class Profile implements Serializable {
    String name;
    int age;
    String mood;

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mood='" + mood + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Profile(String name, int age, String mood) {
        this.name = name;
        this.age = age;
        this.mood = mood;
    }
}
