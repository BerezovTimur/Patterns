package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;

public class FormTest {

    public String city = getCity();
    public String phone = getPhone();
    public String name = getName();
    public String newDate = newDate();

    @Test
    void shouldSubmitCorrectForm() {
        open("http://localhost:9999/");
        $("[type='text'][placeholder]").setValue(getCity());
        $("[class='input__control'][type='tel'][placeholder]").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[class='input__control'][type='tel'][placeholder]").setValue(date());
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue(getPhone());
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldGetErrorIfEmptyForm() {
        open("http://localhost:9999/");
        $("[class='button__text']").click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }

    @Nested
    @DisplayName("Test new functions")
    class NewFunctions {
        @Test
        void shouldSubmitCorrectForm() {
            open("http://localhost:9999/");
            $("[type='text'][placeholder]").setValue(city);
            $("[class='input__control'][type='tel'][placeholder]").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[class='input__control'][type='tel'][placeholder]").setValue(date());
            $("[name='name']").setValue(name);
            $("[name='phone']").setValue(phone);
            $("[data-test-id='agreement']").click();
            $("[class='button__text']").click();
            $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
            $("[type='text'][placeholder]").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[type='text'][placeholder]").setValue(city);
            $("[class='input__control'][type='tel'][placeholder]").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[class='input__control'][type='tel'][placeholder]").setValue(newDate);
            $("[name='name']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[name='name']").setValue(name);
            $("[name='phone']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[name='phone']").setValue(phone);
            $("[class='button__text']").click();
            $(byText("Перепланировать")).click();
            $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
        }
    }

    @Nested
    @DisplayName("City test")
    class CityTest {

        @Test
        void shouldGetMistakeIfWrongCity() {
            open("http://localhost:9999/");
            $("[type='text'][placeholder]").setValue("Гатчина");
            $("[class='input__control'][type='tel'][placeholder]").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[class='input__control'][type='tel'][placeholder]").setValue(date());
            $("[name='name']").setValue(getName());
            $("[name='phone']").setValue(getPhone());
            $("[data-test-id='agreement']").click();
            $("[class='button__text']").click();
            String getText = $("[data-test-id='city']").getText();
            assertEquals("Доставка в выбранный город недоступна", getText);
        }

        @Test
        void shouldGetMistakeIfNoCity() {
            open("http://localhost:9999/");
            $("[type='text'][placeholder]").setValue("");
            $("[class='input__control'][type='tel'][placeholder]").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[class='input__control'][type='tel'][placeholder]").setValue(date());
            $("[name='name']").setValue(getName());
            $("[name='phone']").setValue(getPhone());
            $("[data-test-id='agreement']").click();
            $("[class='button__text']").click();
            String getText = $("[data-test-id='city']").getText();
            assertEquals("Поле обязательно для заполнения", getText);
        }

        @Test
        void shouldGetMistakeIfLatinNameCity() {
            open("http://localhost:9999/");
            $("[type='text'][placeholder]").setValue("Saint-Petersburg");
            $("[class='input__control'][type='tel'][placeholder]").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            $("[class='input__control'][type='tel'][placeholder]").setValue(date());
            $("[name='name']").setValue(getName());
            $("[name='phone']").setValue(getPhone());
            $("[data-test-id='agreement']").click();
            $("[class='button__text']").click();
            String getText = $("[data-test-id='city']").getText();
            assertEquals("Доставка в выбранный город недоступна", getText);
        }
    }
}
