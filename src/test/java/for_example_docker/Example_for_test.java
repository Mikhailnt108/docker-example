package for_example_docker;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class Example_for_test {
    @BeforeEach
    public void initDriver() throws IOException {
      final String url = "http://192.168.1.59:4444/wd/hub";
      WebDriver driver = new RemoteWebDriver(new URL(url), DesiredCapabilities.chrome());
      driver.manage().window().setSize(new Dimension(1920,1024));
      WebDriverRunner.setWebDriver(driver);
    }
    @Test
    @DisplayName("Issue")
    public void testIssue() {
        step("Open Page", () -> {
            open("https://github.com");
        });
        step("Open page with repository", () -> {
            $x("//*[contains(@class, 'header-search-input')]").click();
            $x("//*[contains(@class, 'header-search-input')]").sendKeys("eroshenkoam/allure-example");
            $x("//*[contains(@class, 'header-search-input')]").submit();
            $x("//a[@href='/eroshenkoam/allure-example']").click();
        });
        //step("Open page with issues in repository", () -> {
           // $x("//span[@data-content='Issues']").click();
       // });
        //step("Проверяем title", () -> {
            //$x("//a[@id='issue_12_link']").click();
            //$x("//h1[contains(@class, 'gh-header-title')]").should(Condition.text("Hello, World!"));
        //});

    }
   @AfterEach
   public void stopDriver() {
        Optional.ofNullable(WebDriverRunner.getWebDriver()).ifPresent(WebDriver::quit);
   }
}
