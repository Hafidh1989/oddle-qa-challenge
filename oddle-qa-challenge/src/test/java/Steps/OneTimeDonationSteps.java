package Steps;

import Helpers.Pages;
import Helpers.TestHelper;
import Models.AppSettings;
import Models.Card;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static Helpers.TestHelper.pause;
import static Helpers.TestHelper.waitUntilVisible;

public class OneTimeDonationSteps {
    private static WebDriver driver;
    public final static int TIMEOUT = 10;
    public String AppSettings = "AppSettings.json";
    public AppSettings settings;
    public Boolean isSecure = false;

    public OneTimeDonationSteps() throws IOException {
        // Load web setting
        settings = TestHelper.Settings(AppSettings);

        Properties prop = new Properties();
        prop.setProperty("log4j.rootLogger", "WARN");
        PropertyConfigurator.configure(prop);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("disable-gpu");
        options.addArguments("disable-extensions");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Given("I want to do 5 dollar one time donate with out 3D secure verification")
    public void i_want_to_do_dollar_one_time_donate_with_out_3d_secure_verification() throws InterruptedException {
        driver.get(settings.url);
        pause(100);
        driver.findElement(Pages.BTN_FIVE_DOLLAR).click();
        waitUntilVisible(driver, Pages.TXTBOX_FIVE_DOLLAR);
        String Amount = driver.findElement(Pages.TXTBOX_FIVE_DOLLAR).getText();
        Assert.assertEquals(Amount, "$5.00");
    }

    @When("I Donate")
    public void i_donate() throws InterruptedException {
        pause(250);
        Card card = new Card();

        for(Card val : settings.cards){
            if(val.isSecure == isSecure){
                card = val;
                break;
            }else{
                card = val;
            }

        }

        driver.findElement(Pages.TXTBOX_EMAIL).sendKeys(settings.email);
        driver.findElement(Pages.TXTBOX_CARD_NUMBER).sendKeys(card.cardInfo.cardNumber);
        driver.findElement(Pages.TXTBOX_EXPIRY).sendKeys(card.cardInfo.cardExpiry);
        driver.findElement(Pages.TXTBOX_CVC).sendKeys(card.cardInfo.cvc);
        driver.findElement(Pages.TXTBOX_NAME).sendKeys(card.cardInfo.nameOnCard);
        pause(1000);
        driver.findElement(Pages.BTN_PAY).click();
        pause(1500);
    }

    @Then("Then I should redirect to payment succeeded page")
    public void then_i_should_redirect_to_payment_succeeded_page() throws InterruptedException {
        waitUntilVisible(driver, Pages.TXT_PAYMENT_SUCCEED);
        System.out.println(driver.findElement(Pages.TXT_PAYMENT_SUCCEED).isDisplayed());
        Assert.assertTrue(driver.findElement(Pages.TXT_PAYMENT_SUCCEED).isDisplayed());
    }

    @Given("I want to do 5 dollar one time donate with 3D secure verification")
    public void i_want_to_do_dollar_one_time_donate_with_3d_secure_verification() throws InterruptedException {
        isSecure = true;
        driver.get(settings.url);
        pause(100);
        driver.findElement(Pages.BTN_FIVE_DOLLAR).click();
        waitUntilVisible(driver, Pages.TXTBOX_FIVE_DOLLAR);
        String Amount = driver.findElement(Pages.TXTBOX_FIVE_DOLLAR).getText();
        System.out.println(Amount);
        Assert.assertEquals(Amount, "$5.00");

    }

    @Then("I should be prompt with 3D secure verification page")
    public void i_should_be_prompt_with_3d_secure_verification_page() throws InterruptedException {
        pause(800);
        driver.switchTo().frame(driver.findElement(Pages.MAIN_FRAME));
        driver.switchTo().frame(driver.findElement(Pages.CONTENT_FRAME));
    }

    @And("I can click complete button to proceed payment")
    public void iCanClickCompleteButtonToProceedPayment() {
        Assert.assertTrue(driver.findElement(Pages.BTN_COMPLETE).isDisplayed());
        Assert.assertTrue(driver.findElement(Pages.BTN_FAIL).isDisplayed());
        driver.findElement(Pages.BTN_COMPLETE).click();
    }
    @And("I can click fail button to abort payment")
    public void iCanClickFailButtonToAbortPayment() {
        Assert.assertTrue(driver.findElement(Pages.BTN_COMPLETE).isDisplayed());
        Assert.assertTrue(driver.findElement(Pages.BTN_FAIL).isDisplayed());
        driver.findElement(Pages.BTN_FAIL).click();
    }

    @Then("I should get error payment error message")
    public void iShouldGetErrorPaymentErrorMessage() throws InterruptedException {
        pause(800);
        driver.switchTo().defaultContent();
        String actualString = driver.findElement((Pages.TXT_PAYMENT_ERROR)).getText();
        Assert.assertTrue(actualString.contains("We are unable to authenticate your payment method"));
    }
}
