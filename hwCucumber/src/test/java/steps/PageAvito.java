package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class PageAvito extends PageSome {

    public PageAvito(WebDriver driver) {
        super(driver);
        open();
    }

    @Override
    protected void open() {
        driver.get("https://www.avito.ru/");
    }

    public void getScreenshot(WebDriver driver) {
        takeScreenshot(driver);
    }

    public void selectCategory(String category) {
        select(driver.findElement(By.cssSelector("#category")))
                .selectByVisibleText(category);
    }

    public void inputTextArea(String text) {
        WebElement textArea = driver.findElement(By.cssSelector("input[autocomplete = 'off'][maxlength = '100']"));
        textArea.click();
        textArea.sendKeys(text);
        driver.findElement(By.cssSelector("#app>div")).click();
    }

    public void clickOnFieldCity() {
        WebElement locationSelect = driver.findElement
                (By.xpath("//div[contains(@data-marker,'search-form/region')]"));
        locationSelect.click();
    }

    public void searchForCity(String city) {
        WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[data-marker = 'popup-location/region/input']")));
        location.sendKeys(city);
        location.click();

        WebElement locationField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("li[data-marker = 'suggest(0)']")));
        while (true) {
            if (locationField.getText().startsWith(city)) {
                locationField.click();
                break;
            }
        }
    }

    public void clickOnButton() {
        WebElement searchOnLocationField = driver.findElement
                (By.xpath("//button[contains(@data-marker,'save-button')]"));
        searchOnLocationField.click();
    }

    public void checkbox() {
        WebElement checkBoxStatus = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()=\"только с фото\"]")));
        if (!checkBoxStatus.isSelected())
            checkBoxStatus.click();
    }

    public void checkOpenedPage(String word) {
        Assert.assertTrue(driver.findElement(By.cssSelector("h1[data-marker = 'page-title/text']"))
                .getText().contains(word), "Значение не равны!");
    }

    public void selectFilter(String sortValue) {
        select(driver.findElement
                (By.cssSelector("div.index-topPanel-McfCA  select.select-select-IdfiC")))
                .selectByVisibleText(sortValue);
    }

    public void nameAndPrice(int count) {
        List<WebElement> printersNames;
        List<WebElement> printersPrices;

        printersNames = driver.findElements
                (By.xpath("//a[@data-marker='item-title']/h3[@itemprop='name']"));

        printersPrices = driver.findElements
                (By.xpath("//span[@data-marker='item-price']/span[contains(@class,'price')]"));
        for (int i = 0; i < count; i++) {
            System.out.println(printersNames.get(i).getText());
            System.out.println(printersPrices.get(i).getText());
            System.out.println();
        }
    }
}