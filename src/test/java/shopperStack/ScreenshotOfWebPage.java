package shopperStack;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;


public class ScreenshotOfWebPage {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.selenium.dev/");
		//1.First downcast the WebDriver ref to TakeScreenShot Interface
		TakesScreenshot ts=(TakesScreenshot)driver;
		//2.Call the getScreenshotAs() and pass OutputType.FILE as an argument of File type
		File source=ts.getScreenshotAs(OutputType.FILE);
		//3.Declare the location of the file where the screenshot is to be stored
		//pass the path of the file as an argument to the constructor and storage location
		File target=new File("C:\\Users\\Asus\\OneDrive\\Desktop\\automation\\restAssured\\src\\test\\java\\screenshot\\ss.png");
		//4.Call the copy() of FileHandler class and pass the source variable
		FileHandler.copy(source,target);
		driver.quit();

	}

}
