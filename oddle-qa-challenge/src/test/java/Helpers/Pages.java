package Helpers;
import org.openqa.selenium.By;
public class Pages {
    public static final By BTN_FIVE_DOLLAR = By.xpath("//button[contains(text(),'Donate $5.00 once')]");
    public static final By TXTBOX_FIVE_DOLLAR = By.xpath("//*[text()='$5.00']");
    public static final By TXTBOX_EMAIL = By.xpath("//*[@id=\"email\"]");
    public static final By TXTBOX_CARD_NUMBER = By.xpath("//*[@id=\"cardNumber\"]");
    public static final By TXTBOX_EXPIRY = By.xpath("//*[@id=\"cardExpiry\"]");
    public static final By TXTBOX_CVC = By.xpath("//*[@id=\"cardCvc\"]");
    public static final By TXTBOX_NAME = By.xpath("//*[@id=\"billingName\"]");
    public static final By BTN_PAY = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/form/div[2]/div[2]/button/div[3]");
    public static final By TXT_PAYMENT_SUCCEED = By.xpath("//*[text()='Your test payment succeeded']");
}
