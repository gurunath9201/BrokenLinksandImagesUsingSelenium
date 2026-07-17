package com.pp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class BrokenImagesTest {
	public static void main(String[] args) throws IOException {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://practice-automation.com/broken-images/");
		List<WebElement> allimages=driver.findElements(By.tagName("img"));
		System.out.println("Number of Images on Page:"+allimages.size());
		System.out.println("All Images");
		for(WebElement image:allimages)
		{
			String imagelinks=image.getAttribute("src");
			System.out.println("Images links from page"+imagelinks);
			URL url=new URL(imagelinks);
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.connect();
			con.setConnectTimeout(200);
			int code=con.getResponseCode();
			if(code>400)
			{
				System.out.println(imagelinks+"--- is a Broken Image");
			}
		}
	}
}
