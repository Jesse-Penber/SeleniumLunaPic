package test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//created by Jesse Penber 7/23/2018. 
//Completed: Find and upload picture by link, maximize window, crop photo two different ways
//Next steps: Switch window to color picker using Robot, get picture file picker using Robot, draw crown on cat, 
//Next steps continued: Mouse hover, take screenshot, open DevTools

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
		
		//crop image and navigate to Draw tool
		driver.findElement(By.cssSelector("#cropbutton")).click();	
		driver.findElement(By.linkText("Draw")).click(); 
		driver.findElement(By.linkText("Drawing Tool")).click(); 
		
		//click color popup and switch active Selenium window to color popup 
		driver.findElement(By.cssSelector("#color")).click();
		Set allHandles = driver.getWindowHandles();
		String[] individualHandle = new String[allHandles.size()];
		Iterator it = allHandles.iterator(); 
		int i=0;
		while(it.hasNext()) {
			individualHandle[i] = (String) it.next();
			i++;
		}
		driver.switchTo().window(individualHandle[1]);
		
	}
}
