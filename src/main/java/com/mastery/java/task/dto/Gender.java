package com.mastery.java.task.dto;

public enum Gender {
    MALE ,
    FEMALE ;

    public static Gender parseGender(String str) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(str)) {
                return gender;
            }
        }
        return null;
    }
}
