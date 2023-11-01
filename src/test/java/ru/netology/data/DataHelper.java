package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {}
    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class VerificationCode {
        String code;
    }


    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }
    public static VerificationCode generateRandomCode(){
        return new VerificationCode(faker.numerify("#####"));
    }
    private static String generateRandomLogin(){
        return faker.name().username();
    }
    private static String generateRandomPassword(){
        return faker.internet().password();
    }
    public static AuthInfo generateRandomUser(){
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }


}

