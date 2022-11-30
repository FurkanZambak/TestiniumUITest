import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProductListPage extends BasePage {
    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#productList .m-productCard__photo")
    private List<WebElement> productList;

    public void selectAProductFromListRandomly() {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, this.productList.size());
        waitElementToBeVisible(this.productList.get(randomIndex)).click();
    }
}
