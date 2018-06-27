package myMavenProgram;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTatocMaven {
             
	private static WebDriver driver;
	
	@BeforeClass
	public static void beforeClass() {
		driver = new ChromeDriver();
	}
	
	/*@AfterClass
	public static void afterClass() {
		driver.quit();
	}*/
	
	@Test (priority=1)
	public void tatoc() {
		driver.navigate().to("http://10.0.1.86/tatoc");
		//driver.get("http://10.0.1.86/tatoc");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[text()='Basic Course']")).click();
		System.out.println("After clicking Basic courses");
		}
	@Test(priority=2)
	public void greenBox(){
		System.out.println("In Green Box");
		driver.findElement(By.xpath("//div[@class='greenbox']")).click();
	    driver.navigate().to("http://10.0.1.86/tatoc/basic/frame/dungeon");
		Assert.assertEquals("http://10.0.1.86/tatoc/basic/frame/dungeon",  driver.getCurrentUrl(), "Strings are not matching");
		}
	@Test(priority=3)
	public void frameDungeon() {
		System.out.println("In frameDungeon");
		driver.switchTo().frame("main");
		String Box1 = driver.findElement(By.id("answer")).getAttribute("class");
		
		boolean condition = true;
		while(condition) {
			  
			  driver.findElement(By.xpath("//a[text()='Repaint Box 2']")).click();
			  
			  driver.switchTo().frame("child"); 
			  
			  String Box2 = driver.findElement(By.id("answer")).getAttribute("class");
			  
			  driver.switchTo().parentFrame();
			
			  if(Box1.equals(Box2)) {
				  
				  condition = false;
			  }
		  }
		driver.findElement(By.xpath("//a[text()='Proceed']")).click();
		driver.navigate().to("http://10.0.1.86/tatoc/basic/drag");
		Assert.assertEquals("http://10.0.1.86/tatoc/basic/drag",  driver.getCurrentUrl(), "Strings are not matching");
	}
	@Test(priority=4)
	public void dragAround() {
		WebElement drag = driver.findElement(By.xpath("//div[@id='dragbox']"));
		WebElement drop = driver.findElement(By.xpath("//div[@id='dropbox']"));
		Actions act=new Actions(driver);
		act.dragAndDrop(drag, drop).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
		driver.navigate().to("http://10.0.1.86/tatoc/basic/windows");
		Assert.assertEquals("http://10.0.1.86/tatoc/basic/windows",  driver.getCurrentUrl(), "Strings are not matching");
	}
	
	@Test (priority=5)
	public void popupWindows() throws InterruptedException {
		driver.findElement(By.linkText("Launch Popup Window")).click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.findElement(By.id("name")).sendKeys("Shitij Khanna");  
	    Thread.sleep(1000);
	    driver.findElement(By.id("submit")).click();
	    // driver.close();
	    driver.switchTo().window( tabs2.get(0));
	    
	    driver.findElement(By.linkText("Proceed")).click();
	    driver.navigate().to("http://10.0.1.86/tatoc/basic/cookie");
		Assert.assertEquals("http://10.0.1.86/tatoc/basic/cookie",  driver.getCurrentUrl(), "Strings are not matching");
	}
	
	@Test (priority=6)
	public void cookieHandling() {
		driver.findElement(By.linkText("Generate Token")).click();
	    String value = driver.findElement(By.id("token")).getText();
	    System.out.println(value);
	    String[] token = value.split(":");
	    value = (value.substring(7));
	    System.out.println(value);
	    Cookie Token1 = new Cookie("Token", value );
	    driver.manage().addCookie(Token1);
	    driver.findElement(By.linkText("Proceed")).click();
	}
	
	

}
