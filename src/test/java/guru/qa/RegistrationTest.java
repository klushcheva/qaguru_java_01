package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationTest {
    @BeforeAll
    static void SetUpBrowser(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 5000;
    }


    @Test
    void TestRegistration() {
        open("/automation-practice-form");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@gmail.com");
        $(".custom-control-label").click();
        $("#userNumber").setValue("1234567890");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--012").click();
        $("#subjectsInput").setValue("a").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        $("label[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/java/resources/userpic.jpg"));
        $("#currentAddress").setValue("Maple street 72a");
        $("#react-select-3-input").setValue("Haryana").sendKeys(Keys.ENTER);
        $("#react-select-4-input").setValue("Karnal").sendKeys(Keys.ENTER);
        $("#submit").click();

        $(".modal-dialog").shouldBe(visible);
        $(".table").shouldHave(text("John Doe"));
        $(".table").shouldHave(text("john.doe@gmail.com"));  // Проверка электронного адреса
        $(".table").shouldHave(text("1234567890"));  // Проверка номера телефона
        $(".table").shouldHave(text("12 April,1996"));  // Проверка даты рождения
        $(".table").shouldHave(text("Accounting"));  // Проверка предмета
        $(".table").shouldHave(text("Sports"));  // Проверка хобби
        $(".table").shouldHave(text("userpic.jpg"));  // Проверка загруженного файла
        $(".table").shouldHave(text("Maple street 72a"));  // Проверка введенного адреса
        $(".table").shouldHave(text("Haryana Karnal"));  // Проверка штата и города
    }

}
