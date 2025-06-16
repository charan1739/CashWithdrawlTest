import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WithdrawalAppTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\chara\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://qawithdrawal.ccbp.tech/");

        
        WebElement username = driver.findElement(By.cssSelector("div[class*='details'] > p.name"));
        String expectedUsername = "Sarah Williams";
        if (username.getText().equals(expectedUsername)) {
            System.out.println("Username is correct");
        } else {
            System.out.println("Incorrect username");
        }

        
        WebElement balance = driver.findElement(By.cssSelector("div[class*='balance'] > p.balance"));
        int initialBalance = Integer.parseInt(balance.getText());
        if (initialBalance == 2000) {
            System.out.println("Initial balance is correct");
        } else {
            System.out.println("Incorrect initial balance");
        }

        
        WebElement firstBtn = driver.findElement(By.cssSelector("ul[class^='denominations'] li:nth-child(1) button"));
        WebElement secondBtn = driver.findElement(By.cssSelector("ul[class^='denominations'] li:nth-child(2) button"));
        WebElement thirdBtn = driver.findElement(By.cssSelector("ul[class^='denominations'] li:nth-child(3) button"));
        WebElement fourthBtn = driver.findElement(By.cssSelector("ul[class^='denominations'] li:nth-child(4) button"));

        int balanceAmt = initialBalance;

       
        balanceAmt = clickAndVerify(firstBtn, balanceAmt, driver);
        balanceAmt = clickAndVerify(secondBtn, balanceAmt, driver);
        balanceAmt = clickAndVerify(thirdBtn, balanceAmt, driver);
        balanceAmt = clickAndVerify(fourthBtn, balanceAmt, driver);

        driver.quit();
    }

    private static int clickAndVerify(WebElement btn, int currentBalance, WebDriver driver) {
        int value = Integer.parseInt(btn.getText());

        for (int i = 0; i < 2; i++) {
            btn.click();
            currentBalance -= value;
            WebElement balance = driver.findElement(By.cssSelector("div[class*='balance'] > p.balance"));
            int actualBalance = Integer.parseInt(balance.getText());

            if (actualBalance != currentBalance) {
                System.out.println("Mismatch found in balance");
            } else {
                System.out.println("Withdrawal App working as expected");
            }
        }

        return currentBalance;
    }
}
