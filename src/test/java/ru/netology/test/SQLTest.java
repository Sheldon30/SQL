package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanAuth_code;
import static ru.netology.data.SQLHelper.cleanDB;


public class SQLTest {
    LoginPage loginPage;
    @AfterEach
    void tearDown() throws SQLException {
        cleanAuth_code();
    }
    @AfterEach
    void tearDownAll() throws SQLException {
        cleanDB();
    }
    @BeforeEach
    void setUp(){
        loginPage = open("http://localhost:9999", LoginPage.class);
    }
    @Test
    void shouldBeEntranceSuccessful() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.DashboardPage();
        var verificationCod = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCod.getCode());
    }
    @Test
    void shouldBeErrorWithLoginOrPassword(){
        var authInfo = DataHelper.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.errorNotificationVerify("Ошибка! \nНеверно указан логин или пароль");
    }
    @Test
    void shouldBeErrorWithCode(){
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.DashboardPage();
        var verificationCode = DataHelper.generateRandomCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.errorNotificationVerify("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }

}
