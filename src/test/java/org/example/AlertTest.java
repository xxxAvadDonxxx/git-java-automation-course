package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertTest extends BaseTestClass {
    @Test
    void simpleAlertCheck() {
        driver.findElement(By.id("alertBtn")).click();

        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        // Check if the text in alert as expected
        Assertions.assertEquals("I am an alert box!", text);
    }

    @Test
    void confirmationAlertCheck() {
        WebElement confirmBtn = driver.findElement(By.id("confirmBtn"));
        scrollDownToElement(confirmBtn);

        confirmBtn.click();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        confirmBtn.click();
        String text2 = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        // Check if first and second text of same button is identical
        Assertions.assertEquals(text, text2);
        // Check if those texts are as expected
        Assertions.assertEquals("Press a button!", text2);
    }

    @Test
    void promptAlertCheck() {
        String name = "Jeremy";
        WebElement alertBtn = driver.findElement(By.id("promptBtn"));
        scrollDownToElement(alertBtn);

        alertBtn.click();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().accept();
        String demoText = driver.findElement(By.id("demo")).getText();

        // Check if the text of alert as expected
        Assertions.assertEquals("Please enter your name:", text);
        // Check if our answer to the textbox changes actual site state
        Assertions.assertEquals(String.format("Hello %s! How are you today?", name), demoText);
    }

    @Test
    void promptAlertCheckFail() {
        String name = "Jeremy";
        WebElement alertBtn = driver.findElement(By.id("promptBtn"));
        scrollDownToElement(alertBtn);

        alertBtn.click();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().dismiss();
        String demoText = driver.findElement(By.id("demo")).getText();

        // Check if the text of alert as expected
        Assertions.assertEquals("Please enter your name:", text);
        // Check if our answer to the textbox changes actual site state
        Assertions.assertEquals("User cancelled the prompt.", demoText);
    }
}
