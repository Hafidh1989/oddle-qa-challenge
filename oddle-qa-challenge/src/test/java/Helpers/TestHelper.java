package Helpers;

import Models.AppSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestHelper {
    public static String readFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static Models.AppSettings Settings(String jsonFile) throws IOException {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(),"src", "main", "resources", jsonFile);

        String json = readFileAsString(filePath.toString());

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        AppSettings settings = gson.fromJson(json, Models.AppSettings.class);
        return settings;
    }

    public static void pause(int milli) throws InterruptedException {
        pause(milli, TimeUnit.MILLISECONDS);
    }

    public static void pause(int time, TimeUnit timeUnit) throws InterruptedException {
        timeUnit.sleep(time);
    }

    public static void waitUntilVisible(WebDriver browser, By locator) throws InterruptedException {
        Boolean isVisible = false;
        do{
            pause(1000);
            try{
                if(browser.findElement(locator).isDisplayed()){
                    isVisible = true;
                }

            }catch(AssertionError e)
            {
                System.out.println(e);

            }
        }while ((!isVisible));
    }
}
