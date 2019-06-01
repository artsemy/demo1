package com.mastery.java.task.dto;

public enum Gender {
    MALE ("male"),
    FEMALE ("female");

    private final static String sMale = "male";
    private final static String sFemale = "female";

    private final String gender;

    Gender(String str){
        gender = str;
    }

    @Override
    public String toString() {
        return this.gender;
    }

    public Gender parseGender(String str) {
        if (sMale.equals(str)) { return MALE; }
        if (sFemale.equals(str)) { return FEMALE; }
        return null;
    }
}
