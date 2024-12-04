package code.main;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        // Cấu hình sử dụng AndroidOptions
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setPlatformName("Android");
        options.setPlatformVersion("7.0");
        options.setApp("V:/File/Android-studio/app/build/outputs/apk/debug/app-debug.apk");
        options.setAutomationName("UiAutomator2");
        options.setNoReset(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @Test
    public void testShowWordButton() throws InterruptedException {
        // Tăng thời gian chờ của WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Chờ thời gian để ứng dụng có thể tải xong
        Thread.sleep(5000);

        // Sử dụng By.id để tìm phần tử nút và nhấn vào nó
        driver.findElement(By.id("code.main:id/showWordButton")).click();

        // Chờ cho đến khi phần tử TextView được cập nhật
        WebElement wordTextView = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("code.main:id/wordTextView")
        ));

        // Lần click đầu tiên sẽ set text là "Hello - Xin chào"
        wait.until(ExpectedConditions.textToBePresentInElement(wordTextView, "Hello - Xin chào"));
        assertEquals("Hello - Xin chào", wordTextView.getText());

        // Nhấn lại vào nút "Show Word"
        driver.findElement(By.id("code.main:id/showWordButton")).click();

        // Lần click thứ hai sẽ set text là "Goodbye - Tạm biệt"
        wait.until(ExpectedConditions.textToBePresentInElement(wordTextView, "Goodbye - Tạm biệt"));
        assertEquals("Goodbye - Tạm biệt", wordTextView.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
