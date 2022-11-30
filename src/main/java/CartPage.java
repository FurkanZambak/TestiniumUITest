import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".m-productPrice__salePrice")
    private WebElement productPriceField;

    @FindBy(id = "quantitySelect0-key-0")
    private WebElement quantityDropdown;

    @FindBy(xpath = "//h4[@id = 'notifyTitle' and text() = 'Sepetiniz Güncellenmiştir']")
    private WebElement notificationTitle;

    @FindBy(xpath = "//p[@id = 'notifyMessage' and text() = 'Sepetinizden ürün başarılı bir şekilde silinmiştir.']")
    private WebElement notificationMessage;

    @FindBy(id = "removeCartItemBtn0-key-0")
    private WebElement removeCartItem;

    @FindBy(xpath = "//strong[text() = 'Sepetinizde Ürün Bulunmamaktadır']")
    private WebElement emptyCartMsgTxt;

    @FindBy(css = "a[title = 'Alışverişe Devam Et']")
    private WebElement continueShoppingLink;

    public String getProductPrice() {
        return waitElementToBeVisible(this.productPriceField).getText();
    }

    public void selectQuantity(String quantity) {
        Select quantityDrop = new Select(this.quantityDropdown);
        quantityDrop.selectByValue(quantity);
    }

    public int formatProdPriceToNumeric(String prodPrice) {
        return Integer.parseInt(prodPrice.replaceAll("[^0-9]+", ""));
    }

    public boolean checkNotificationTitleIsVisible() throws InterruptedException {
        WebElement elm = waitElementToBeVisible(this.notificationTitle);
        Thread.sleep(1000);
        return elm.isDisplayed();
    }

    public void clickRemoveCarItemBut() {
        this.removeCartItem.click();
    }

    public boolean checkEmptyCartMsgTxtIsVisible() {
        return waitElementToBeVisible(this.emptyCartMsgTxt).isDisplayed();
    }

    public boolean checkContinueShoppingLinkIsVisible() {
        return this.continueShoppingLink.isDisplayed();
    }

    public boolean checkNotificationMessageIsVisible() {
        return waitElementToBeVisible(this.notificationMessage).isDisplayed();
    }
}
