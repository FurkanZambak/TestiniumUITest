import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestClassOrder(ClassOrderer.DisplayName.class)
@DisplayName("Ürün Arama, Sepete Ekleme ve Silme Senaryosu")
public class TestScenario extends BaseTest {

    @Nested
    @DisplayName("1-Anasayfanın açılması ve ürün arama işleminin gerçekleştirilmesi")
    @TestMethodOrder(MethodOrderer.DisplayName.class)
    class HomePageTest {
        HomePage homePage;

        @BeforeEach
        public void declarePageObj() {
            homePage = new HomePage(driver);
        }

        @Test
        @DisplayName("1- Anasayfa açılır")
        public void openHomePage() {
            homePage.openHomePage();
        }

        @Test
        @DisplayName("2- Anasayfanın açıldığı kontrol edilir")
        public void shouldHomePageOpened() {
            Assertions.assertTrue(homePage.checkLogoIsVisible());
            Assertions.assertTrue(homePage.checkPageTitle("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu"));
        }

        @DisplayName("3- Excelden okunan veri searchbox alanına yazılır")
        @ParameterizedTest(name = "Dosyanın {0}. satırının {1}. sütunundaki değer searchbox alanına yazılır")
        @CsvSource({"1,1"})
        public void typeValueToSearchBox(int rowNo, int colNo) {
            DataOps dataOps = new DataOps();
            String value = dataOps.returnData(rowNo, colNo);
            homePage.typeToSearchBox(value);
        }

        @Test
        @DisplayName("4- Arama alanına girilen değer silinir")
        public void clearSearchBox() throws InterruptedException {
            homePage.clearSearchBox();
        }

        @DisplayName("5- Excelden okunan 2.veri searchbox alanına yazılır")
        @ParameterizedTest(name = "Dosyanın {0}. satırının {1}. sütunundaki değer searchbox alanına yazılır")
        @CsvSource({"1,2"})
        public void typeAnotherValueToSearchBox(int rowNo, int colNo) {
            DataOps dataOps = new DataOps();
            String value = dataOps.returnData(rowNo, colNo);
            homePage.typeToSearchBox(value);
        }

        @Test
        @DisplayName("6- Klavye üzerinden ENTER tuşuna basılır")
        public void pressEnterToSearch() {
            homePage.pressEnterInSearchBox();
        }
    }

    @Nested
    @DisplayName("2- Ürün listesi sayfasında ürün seçimi işleminin gerçekleştirilmesi")
    class ProductListTest {
        ProductListPage productListPage;

        @BeforeEach
        public void declarePageObj() {
            productListPage = new ProductListPage(driver);
        }

        @Test
        @DisplayName("Sergilenen ürünlerden rastgele bir ürün seçilir")
        public void selectAProductRandomly() {
            productListPage.selectAProductFromListRandomly();
        }
    }

    @Nested
    @DisplayName("3- Ürün detayı sayfasında ürünün sepete eklenmesi")
    @TestMethodOrder(MethodOrderer.DisplayName.class)
    class ProductDetailTest {
        ProductDetailPage productDetailPage;

        @BeforeEach
        public void declarePageObj() {
            productDetailPage = new ProductDetailPage(driver);
        }

        @Test
        @DisplayName("1- Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır")
        public void writeProductDetailsToFile() {
            String data = productDetailPage.getProductDetail() + " - " + productDetailPage.getPriceData();
            DataOps dataOps = new DataOps();
            dataOps.writeDataToFile(data);
        }

        @Test
        @DisplayName("2- İlgili ürün için rastgele bir beden seçilir")
        public void chooseAVariation() {
            productDetailPage.selectAVariationRandomly();
        }

        @Test
        @DisplayName("3- Seçilen ürün için Sepete Ekle butonuna tıklanır")
        public void clickAddToBasketBut() {
            productDetailPage.clickAddBasketBut();
        }

        @Test
        @DisplayName("4- Sepetim linkine tıklanır")
        public void clickMyCartLink() {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyCartLink();
        }
    }

    @Nested
    @DisplayName("4- Sepet sayfasındaki işlemlerin ve kontrollerin gerçekleştirilemesi")
    @TestMethodOrder(MethodOrderer.DisplayName.class)
    class CartTest {

        CartPage cartPage;

        static String prodPrice;

        @BeforeEach
        public void declarePageObj() {
            cartPage = new CartPage(driver);
        }

        @Test
        @DisplayName("1- Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır")
        public void shouldProductPricesMatch() {
            DataOps dataOps = new DataOps();
            String productDetail = dataOps.readFile();
            String prodPrice1 = productDetail.split(" - ")[1];
            prodPrice = prodPrice1;
            String prodPrice2 = cartPage.getProductPrice();
            Assertions.assertEquals(prodPrice1, prodPrice2);
        }

        @DisplayName("2- Ürün adeti arttırılır")
        @ParameterizedTest(name = "Ürün adedi {0} olarak güncellenir")
        @ValueSource(strings = "2")
        public void changeProdQuantity(String quantity) throws InterruptedException {
            cartPage.selectQuantity(quantity);
            Assertions.assertTrue(cartPage.checkNotificationTitleIsVisible());
        }

        @Test
        @DisplayName("3- Ürün adedinin arttığının kontrolü")
        public void shouldProdQuantityBeIncreased() {
            int oldPrice = cartPage.formatProdPriceToNumeric(prodPrice);
            int newPrice = cartPage.formatProdPriceToNumeric(cartPage.getProductPrice());
            Assertions.assertEquals(oldPrice * 2, newPrice);
        }

        @Test
        @DisplayName("4- Ürünün silinmesi")
        public void shouldProductBeRemoved() {
            cartPage.clickRemoveCarItemBut();
            Assertions.assertTrue(cartPage.checkNotificationMessageIsVisible());
        }

        @Test
        @DisplayName("5- Sepetin boş olduğunun kontrol edilmesi")
        public void shouldCartBeEmpty() {
            Assertions.assertTrue(cartPage.checkEmptyCartMsgTxtIsVisible());
            Assertions.assertTrue(cartPage.checkContinueShoppingLinkIsVisible());
        }
    }
}
