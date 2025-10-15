package pac1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC005_ALERT {
	public static void main(String[] args) {
// Setup ChromeDriver
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();

// Open Rediffmail login page
driver.get("https://mail.rediff.com/cgi-bin/login.cgi");

// Click the "proceed" button without entering credentials
driver.findElement(By.name("proceed")).click();

// Handle the alert popup
String alertMessage = driver.switchTo().alert().getText();
System.out.println("Alert message: " + alertMessage);

// Accept the alert
driver.switchTo().alert().accept();

// Close browser
driver.quit();
}
}