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
    public static final By TXT_PAYMENT_ERROR = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/form/div[2]/div[1]/p");

    // 3D secure
    public static final By MAIN_FRAME = By.xpath("/html/body/div[1]/iframe");
    public static final By CONTENT_FRAME = By.xpath("//*[@id=\"challengeFrame\"]");
    public static final By BTN_FAIL = By.xpath("//*[@id=\"test-source-fail-3ds\"]");
    public static final By BTN_COMPLETE = By.xpath("//*[@id=\"test-source-authorize-3ds\"]");
}
