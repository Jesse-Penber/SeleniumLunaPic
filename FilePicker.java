package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

//uploads local file to website from Windows file picker using Robot class from AWT package
//created by Jesse Penber, 7-27-2018

public class RobotFileUpload {
	
	public static void main(String args[]) throws Exception {
		
		Robot robot = new Robot();
		
		//set browser to Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jpenber\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//navigate to Lunapic file upload, open file picker
		driver.navigate().to("https://www110.lunapic.com/editor/?action=quick-upload");
		driver.findElement(By.xpath("//input[@type='file']")).click();
		
		robot.setAutoDelay(2000);
		
		//converts file path to string, copies to clipboard
		StringSelection stringSelect = new StringSelection("‪dog.PNG");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelect, null);
		
		//pastes string to file picker
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
 
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
 
        robot.setAutoDelay(1000);
        
        //presses enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
