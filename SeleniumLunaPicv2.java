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

//Updated 7/24: Added screenshot and button highlight, began work on drawing
//Next steps: Use clickat() and coordinates from plug-in to get moustache drawing working, move screenshot to end,
//Next steps continued: Use robot to get Browse file picker working

public class PicEditor {
	public static void main(String[] args) {
		// set driver properties to Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jpenber\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//navigate to LunaPic
		driver.navigate().to("https://www110.lunapic.com/editor/");
		
		//open the Browse window file picker, enter the picture URL and submit it, NOT WORKING
		//driver.findElement(By.cssSelector("#yourBtn")).click();		
		//driver.switchTo()
		//	.activeElement()
		//	.sendKeys("cat.jpg");
		
		//Robot robot1 = null;
		//try {
		//	robot1 = new Robot();
		//} catch (AWTException e) {
		// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		//robot1.keyPress(KeyEvent.VK_ENTER);
		//robot1.keyRelease(KeyEvent.VK_ENTER);
		
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
		
		//take a screenshot, CHANGE FILE NAME MANUALLY EACH RUN FOR NOW
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("C:/Users/jpenber/screenshot7-24.png"));
		} 
		catch (IOException e)
		 {
		  System.out.println(e.getMessage());
		 }
		
		//crop image and navigate to Draw tool
		driver.findElement(By.cssSelector("#cropbutton")).click();	
		driver.findElement(By.linkText("Draw")).click(); 
		driver.findElement(By.linkText("Drawing Tool")).click(); 
		
		//draw Mustache on cat, not working, redo using clickat() 
		//Actions draw = new Actions(driver);
		//draw.moveToElement(chromeHeader).moveByOffset((width/2), (width/2)).clickAndHold().moveByOffset((width/6), 0);
		
		//highlight Filters button
		WebElement filterMenu = driver.findElement(By.linkText("Filters"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", filterMenu);
		
	}
}
