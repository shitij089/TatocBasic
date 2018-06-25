package myProgram;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class BasicTatoc {

	//private static final String String = null;

	public static void main(String[] args) throws InterruptedException {
				
				WebDriver driver = new ChromeDriver();
				
				driver.get("http://10.0.1.86/tatoc");
				
				driver.manage().window().maximize();
				
				driver.findElement(By.tagName("a")).click();
				
				driver.findElement(By.className("greenbox")).click();
				
				driver.switchTo().frame("main");
				
				String Box1 = driver.findElement(By.id("answer")).getAttribute("class");
				
				        boolean condition = true;
				        
				  while(condition) {
					  
					  driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click();
					  
					  driver.switchTo().frame("child"); 
					  
					  String Box2 = driver.findElement(By.id("answer")).getAttribute("class");
					  
					  driver.switchTo().parentFrame();
					
					  if(Box1.equals(Box2)) {
						  
						  condition = false;
					  }
				  }
					
				  
				  driver.findElement(By.cssSelector("body > center > a:nth-child(9)")).click(); 
				  
				  WebElement drag = driver.findElement(By.xpath("//div[@id='dragbox']"));
				  
				  WebElement drop = driver.findElement(By.xpath("//div[@id='dropbox']"));
				  
				  Actions act=new Actions(driver);
				  
				  act.dragAndDrop(drag, drop).build().perform();
				  
				  driver.findElement(By.linkText("Proceed")).click();	
				  
				  driver.findElement(By.linkText("Launch Popup Window")).click();
				  
				   ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
				    
				    driver.switchTo().window(tabs2.get(1));
				    
				    driver.findElement(By.id("name")).sendKeys("Shitij Khanna");  
				    
				    Thread.sleep(1000);
				    
				    driver.findElement(By.id("submit")).click();
				    
				   // driver.close();
				    
				    driver.switchTo().window( tabs2.get(0));
				    
				    driver.findElement(By.linkText("Proceed")).click();
				    
				    driver.findElement(By.linkText("Generate Token")).click();
				    
				    String value = driver.findElement(By.id("token")).getText();
				    
				    System.out.println(value);
				    
				    String[] tok = value.split(":");
				    
				    value = (value.substring(7));
				    
				    System.out.println(value);
				    
				    Cookie Token1 = new Cookie("Token", value );
				    
					driver.manage().addCookie(Token1);
				    
					//driver.manage().getCookies();
				    
					driver.findElement(By.linkText("Proceed")).click();
	
				    }
				  
				  
		}


