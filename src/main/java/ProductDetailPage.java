import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProductDetailPage extends BasePage {
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".o-productDetail__description")
    private WebElement productDetail;

    @FindBy(id = "priceNew")
    private WebElement priceField;

    @FindBy(id = "addBasket")
    private WebElement addBasketBut;

    @FindBy(css = ".m-variation > span:not(.-disabled)")
    private List<WebElement> variationChoices;

    public String getProductDetail() {
        return this.productDetail.getText();
    }

    public String getPriceData() {
        return this.priceField.getText();
    }

    public void clickAddBasketBut() {
        this.addBasketBut.click();
    }

    public void selectAVariationRandomly() {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, this.variationChoices.size());
        WebElement elm = waitElementToBeVisible(this.variationChoices.get(randomIndex));
        Click(elm);
    }
}
