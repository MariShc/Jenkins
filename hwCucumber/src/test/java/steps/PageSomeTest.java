package steps;

import io.qameta.allure.Step;
import io.cucumber.java.After;
import io.cucumber.java.ParameterType;
import io.cucumber.java.bg.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class PageSomeTest {
    private WebDriver driver;
    private PageAvito page;

    @ParameterType(".*")
    public Categories categories(String category) {
        return Categories.valueOf(category);
    }

    @ParameterType(".*")
    public Filters filters(String filter) {
        return Filters.valueOf(filter);
    }

    @Step("Открыть ресурс авито")
    @Пусть("открыт ресурс авито")
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        page = new PageAvito(driver);
        page.getScreenshot(driver);
    }
    @Step("В выпадающем списке категорий выбрать категорию")
    @И("в выпадающем списке категорий выбрана {categories}")
    public void inDropDownListCategoryChoose(Categories category) {
        page.selectCategory(category.getName());
        page.getScreenshot(driver);
    }

    @Step("В поле поиска ввести значение")
    @И("в поле поиска введено значение {word}")
    public void inSearchFieldInput(String text) {
        page.inputTextArea(text);
        page.getScreenshot(driver);
    }

    @Step("Активировать чекбокс только с фотографией")
    @И("активирован чекбокс только с фотографией")
    public void checkboxOnlyWithPhoto() {
        page.checkbox();
        page.getScreenshot(driver);
    }

    @Step("Кликнуть по выпадающему списку региона")
    @Тогда("кликнуть по выпадающему списку региона")
    public void clickOnDropDownListCity() {
        page.clickOnFieldCity();
        page.getScreenshot(driver);
    }

    @Step("В поле регион ввести значение")
    @Тогда("в поле регион введено значение {word}")
    public void inFieldCityInput(String city) {
        page.searchForCity(city);
        page.getScreenshot(driver);
    }

    @Step("Нажать кнопку показать объявления")
    @И("нажата кнопка показать объявления")
    public void clickOnButtonShowAds() {
        page.getScreenshot(driver);
        page.clickOnButton();
    }

    @Step("Открылась страница результаты по искомому запросу")
    @Тогда("открылась страница результаты по запросу {word}")
    public void openedPageOnRequest(String word) {
        page.checkOpenedPage(word);
        page.getScreenshot(driver);
    }

    @Step("В выпадающем списке сортировка выбрать нужное значение")
    @И("в выпадающем списке сортировка выбрано значение {filters}")
    public void inDropDownListSortChoose(Filters filter) {
        page.selectFilter(filter.getName());
        page.getScreenshot(driver);
    }

    @Step("Вывести в консоль значение названия и цены нужного количества товаров")
    @И("в консоль выведено значение названия и цены {word} первых товаров")
    public void nameAndPriceOfProduct(String count) {
        page.nameAndPrice(Integer.valueOf(count));
        page.getScreenshot(driver);
    }

    @After
    public void after() {
        if (driver != null) {
            driver.close();
        }
    }
}
