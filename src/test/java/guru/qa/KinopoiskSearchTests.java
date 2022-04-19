package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KinopoiskSearchTests {

    @ValueSource(strings = {
            "Видоизменённый углерод",
            "Властелин колец"
    })

    @ParameterizedTest(name = "Проверка поиска по названию фильма {0}")
    void kinopoiskSearchTest(String testData) {

        //Pre-condition

        Selenide.open("https://www.kinopoisk.ru/");

        //Steps

        $("[name=kp_query]").setValue(testData);
        $("[type=submit]").click();

        //Expected Result

        $$(".search_results").find(Condition.text(testData)).shouldBe(visible);
    }

    @CsvSource (value = {
            "Видоизменённый углерод | фантастика, детектив, фильм-нуар",
            "Властелин колец | фэнтези, приключения, драма"
    },
    delimiter = '|')

    @ParameterizedTest (name = "Проверка поиска по названию фильма {0}, ожидаем  результат: {1}")
    void kinopoiskSearchComplexTest(String testData, String expectedResult) {

        //Pre-condition

        Selenide.open("https://www.kinopoisk.ru/");

        //Steps

        $("[name=kp_query]").setValue(testData);
        $("[type=submit]").click();

        //Expected Result

        $$(".search_results").find(Condition.text(testData)).shouldBe(visible);
    }

}
