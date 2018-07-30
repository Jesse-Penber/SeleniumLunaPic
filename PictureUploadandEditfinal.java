package test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//Updated 7/30: Added canvas drawing, moved screenshot to end.

public class PictureEditor {
	public static void main(String[] args) {
		// set driver properties to Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jpenber\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//navigate to LunaPic
		driver.navigate().to("https://www110.lunapic.com/editor/");
		
		//adds image URL and uploads it
		driver.findElement(By.xpath("//input[@type ='text']")).sendKeys("www.petmd.com/sites/default/files/what-does-it-mean-when-cat-wags-tail.jpg");
		driver.findElement(By.xpath("//input[@type ='submit']")).click();
		
		//maximize browser window
		driver.manage().window().maximize();
		
		//click Crop
		driver.findElement(By.linkText("Crop")).click(); 
		
		//drag and drop crop square to lower right of cropping window, using Element to Element 
		WebElement lowerRightCorner = driver.findElement(By.xpath("//div[@class='ord-se jcrop-handle']"));
		WebElement croppedSquare = driver.findElement(By.xpath("//div[@class='jcrop-tracker']"));
		Actions drag = new Actions(driver);
		drag.dragAndDrop(croppedSquare, lowerRightCorner).build().perform();
		
		//drag upper right corner of crop square towards center, using coordinates 
		WebElement upperRightCorner = driver.findElement(By.xpath("//div[@class='ord-ne jcrop-handle']"));
		drag.dragAndDropBy(upperRightCorner, -60, 20).perform();
		
		//crop image and navigate to Draw tool
		driver.findElement(By.cssSelector("#cropbutton")).click();	
		driver.findElement(By.linkText("Draw")).click(); 
		driver.findElement(By.linkText("Drawing Tool")).click(); 
		
		//draw eyebrows on cat
		Actions draw = new Actions(driver);
		draw.moveToElement(driver.findElement(By.cssSelector("#pick1")));
		draw.moveByOffset(250, 250);
		draw.clickAndHold();
		draw.moveByOffset(30, 0);
		draw.release();
		draw.moveByOffset(25, 0);
		draw.clickAndHold();
		draw.moveByOffset(30, 0);
		draw.release();
		draw.perform();
		
		//take a screenshot
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("C:/Users/jpenber/screenshot7-24.png"));
		} 
			catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
				
		//highlight Filters button
		WebElement filterMenu = driver.findElement(By.linkText("Filters"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", filterMenu);
		
	}
}