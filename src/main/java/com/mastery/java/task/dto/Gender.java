package com.mastery.java.task.dto;

public enum Gender {
    MALE ("male"),
    FEMALE ("female");

    private final String gender;

    Gender(String str){
        gender = str;
    }

    @Override
    public String toString() {
        return this.gender;
    }
}
