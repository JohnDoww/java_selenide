import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePageTest {

    @Test(description = "Use search. Search value must be still in search text holder")
    public void checkSearch() {
        open("https://minfin.com.ua/");
        $x("//*[@class=\"mfz-search-wrap\"]//child::input").click();
        $x("//*[@class=\"mfz-search-wrap\"]//child::input").setValue("Hello World");
        $x("//*[@name=\"next\"]").click();
        $("#gsc-i-id1").shouldHave(value("Hello World"));
    }

    @Test(description = "Check how we will go  back to the home page from the insurance page")
    public void checkFollowingToHomePage() {
        open("https://minfin.com.ua/ua/insurance/");
        $("a[class=\"header-top__logo logo\"]").click();
        $("a[class=\"header-top__logo logo\"]").shouldBe(Condition.appear);
    }

    @Test
    public void checkInfoAboutCoinbase() {
        open("https://minfin.com.ua/");
        $x("//*[@class=\"menu__item\"]//*[text()=\"Криптовалюта\"]").hover();
        $x("//*[@class=\"menu__item\"]//*[text()=\"Каталог криптобірж\"]").click();
        $x("//*[text()=\"Coinbase Exchange\"]").click();
        $x("//*[text()=\"Про Coinbase Exchange\"]").shouldBe(exist);
    }

    @Test
    public void checkHowToOpenBankPageByUsingHamburgerMenu() {
        open("https://minfin.com.ua/");
        $x("//*[@class=\"header-bottom__top\"]//parent::*[@class=\"hamburger\"]").click();
        $x("//*[@class=\"popover-menu__links\"]//*[text()=\"Банки\"]").click();
        $x("//*[@src=\"https://minfin.com.ua/img/company/229/logo/1561627002.jpg\"]").click();
        $("img[alt=\"А-Банк\"]").shouldHave(exist);
    }

    @Test
    public void checkAuthorizationExistUser() {
        open("https://minfin.com.ua/");
        $("div[class=\"mfm-auth--ddown-handle jsmz-toggleAuthDropdown\"]").click();
        $x("//*[@class=\"js-login-form\"]//input[@name=\"Login\"]").setValue("pageye6132@jobbrett.com");
        $x("//*[@class=\"js-login-form\"]//input[@name=\"Password\"]").setValue("Qwerty123!");
        $("div[class=\"header-top__profile\"]").shouldBe(visible);
    }

    @Test(description = "Follow to the currency dashboard throw hamburger menu and check availability of top currency")
    public void checkAvailabilityOfRequiredCurrencyOnDashboard() {
        open("https://minfin.com.ua/");
        $x("//*[@class=\"header-bottom__top\"]//parent::*[@class=\"hamburger\"]").click();
        $x("//*[@class=\"popover-menu__links\"]//a[contains(text(), \"Валюта\")]").click();
        $x("//*[@class=\"bvp3d3-9 FqORR\"]//*[@href=\"/ua/currency/usd/\"]").shouldHave(Condition.exactText("USD"));
        $x("//*[@class=\"bvp3d3-9 FqORR\"]//*[@href=\"/ua/currency/eur/\"]").shouldHave(Condition.exactText("EUR"));
        $x("//*[@class=\"bvp3d3-9 FqORR\"]//*[@href=\"/ua/currency/pln/\"]").shouldHave(Condition.exactText("PLN"));
        $x("//*[@class=\"bvp3d3-9 FqORR\"]//*[@href=\"/ua/currency/gbp/\"]").shouldHave(Condition.exactText("GBP"));
        $x("//*[@class=\"bvp3d3-9 FqORR\"]//*[@href=\"/ua/currency/chf/\"]").shouldHave(Condition.exactText("CHF"));
    }

    @Test(description = "Search results must contains partly search value")
    public void checkSearchResults() {
        open("https://minfin.com.ua/");
        $x("//*[@class=\"mfz-search-wrap\"]//child::input").click();
        $x("//*[@class=\"mfz-search-wrap\"]//child::input").setValue("Київ");
        $x("//*[@name=\"next\"]").click();
        $x("//*[@class=\"gsc-webResult gsc-result\"][1]").shouldHave(Condition.partialText("Ки"));
    }

    @Test(description = "Check navigation within calendar and select a date. Selected date and date on the page must be the same")
    public void checkCalendar() {
        open("https://minfin.com.ua/");
        $x("//*[@id=\"currencyWgt\"]//child::*[@href=\"/ua/currency/mb/\"]").click();
        $x("//button[@class=\"tjc6dx-1 eEtOYs\"]").click();
        $x("//*[@d=\"M15.41 16.59L10.83 12l4.58-4.59L14 6l-6 6 6 6 1.41-1.41z\"]").click();
        $x("//p[contains(text(), \"13\")]").click();
        $("h2[class=\"mfm-h2 bottom-head\"]").shouldHave(Condition.partialText("13"));
    }
}
