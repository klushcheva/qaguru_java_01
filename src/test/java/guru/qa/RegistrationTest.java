package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationTest {
    @BeforeAll
    static void setUpBrowser(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 5000;
    }


    @Test
    void testRegistration() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@gmail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--012").click();
        $("#subjectsInput").setValue("A");
        $("#subjectsWrapper").$(byText("Accounting")).click();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("userpic.jpg");
        $("#currentAddress").setValue("Maple street 72a");
        $("#stateCity-wrapper").$(byText("Select State")).click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        $(".modal-dialog").shouldBe(visible);
        $(".table").shouldHave(text("John Doe"));
        $(".table").shouldHave(text("john.doe@gmail.com"));
        $(".table").shouldHave(text("Male"));
        $(".table").shouldHave(text("1234567890"));
        $(".table").shouldHave(text("12 April,1996"));
        $(".table").shouldHave(text("Accounting"));
        $(".table").shouldHave(text("Sports"));
        $(".table").shouldHave(text("userpic.jpg"));
        $(".table").shouldHave(text("Maple street 72a"));
        $(".table").shouldHave(text("Haryana Karnal"));
    }

}
