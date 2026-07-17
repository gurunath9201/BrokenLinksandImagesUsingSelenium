package com.pp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class BrokenLinksTest {
	public static void main(String[] args) throws IOException {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.tutorialspoint.com/selenium/practice/broken-links.php");
		List<WebElement> alllinks=driver.findElements(By.tagName("a"));
		System.out.println("Number of Links on Page:"+alllinks.size());
		System.out.println("All Links");
		for(WebElement link:alllinks)
		{
			String urls=link.getAttribute("href");
			System.out.println(link+"="+urls);
			URL url=new URL(urls);
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.connect();
			int code=con.getResponseCode();
			if(code>400)
			{
				System.out.println(link.getText()+"="+urls+"---- is a Broken Link");
				System.out.println(con.getResponseCode()+" "+con.getResponseMessage());
			}
			else {
				System.out.println(link.getText()+"="+urls+"---- is a Valid Link");
			}
		}
	}
}
