./gradlew assembleDebug
adb shell getprop ro.build.version.release
adb devices


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

        // Sử dụng By.className để tìm phần tử nút (thay thế bằng tên lớp chính xác)
        WebElement showWordButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("android.widget.Button")
        ));

        // Sử dụng By.className để tìm phần tử TextView (thay thế bằng tên lớp chính xác)
        WebElement wordTextView = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("android.widget.TextView")
        ));

        // Lần click đầu tiên sẽ set text là "Hello - Xin chào"
        showWordButton.click();
        assertEquals("Hello - Xin chào", wordTextView.getText());
        Thread.sleep(5000);

        // Lần click thứ hai sẽ set text là "Goodbye - Tạm biệt"
        showWordButton.click();
        assertEquals("Goodbye - Tạm biệt", wordTextView.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
