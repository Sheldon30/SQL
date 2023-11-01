package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement codeButton = $("[data-test-id='action-verify']");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public void DashboardPage() {
        codeField.shouldBe(visible);
    }

    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        codeButton.click();
    }
    public DashboardPage validVerify(String verificationCode){
        verify(verificationCode);
        return new DashboardPage();
    }
    public void errorNotificationVerify(String expectedText){
        errorNotification.shouldHave(exactText(expectedText)).shouldBe(visible);
    }
}
