import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    String MAIN_URL = "https://www.beymen.com";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@title = 'Beymen' and @href = '/']")
    private WebElement logoElm;

    @FindBy(xpath = "//input[contains(@class , 'search--input')]")
    private WebElement searchBox;

    @FindBy(css = "a[title = 'Sepetim']")
    private WebElement myCartLink;

    public void openHomePage() {
        driver.get(MAIN_URL);
    }

    public boolean checkLogoIsVisible() {
        return waitElementToBeVisible(this.logoElm).isDisplayed();
    }

    public boolean checkPageTitle(String pageTitle) {
        return getPageTitle().equals(pageTitle);
    }

    public void typeToSearchBox(String value) {
        this.searchBox.sendKeys(value);
    }

    public void clearSearchBox() throws InterruptedException {
        this.searchBox.clear();
        Thread.sleep(3000);
    }

    public void pressEnterInSearchBox() {
        this.searchBox.sendKeys(Keys.ENTER);
    }

    public void clickMyCartLink() {
        this.myCartLink.click();
    }

}
