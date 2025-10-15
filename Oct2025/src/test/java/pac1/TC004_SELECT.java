package pac1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;

public class TC004_SELECT {
	    public static void main(String[] args) {
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();

	        driver.get("https://tutorialsninja.com/demo/index.php?");
	        driver.manage().window().maximize();

	        driver.findElement(By.linkText("Desktops")).click();
	        driver.findElement(By.linkText("Mac (1)")).click();

	        // Sorting options
	        WebElement sort = driver.findElement(By.id("input-sort"));
	        Select sle = new Select(sort);

	        // sle.selectByIndex(3); // optional line
	        List<WebElement> allOptions = sle.getOptions();

	        for (WebElement option : allOptions) {
	            System.out.println("Sort by: " + option.getText());
	        }

	        // Show options
	        WebElement show = driver.findElement(By.id("input-limit"));
	        Select sle1 = new Select(show);

	        sle1.selectByIndex(3); // optional
	        List<WebElement> showAllOptions = sle1.getOptions();

	        for (WebElement option1 : showAllOptions) {
	            System.out.println("Show options: " + option1.getText());
	        }

	        driver.quit();
	    }
	}


