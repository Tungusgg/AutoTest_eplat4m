package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;
public class Authorization extends SetUpp{
    @Test
    public void Negative(){
        //Введение невалидных данных в поля
        driver.get("https://grc.eplat4m.ru/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@autofocus=\"True\"]")));
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        String name = "САПУИБ\\NotExist" + n ;
        System.out.println(name);
        driver.findElement(By.xpath("//input[@autofocus=\"True\"]")).sendKeys(name);
        String passw = "wer" + n + "gg" + n;
        System.out.println(passw);
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(passw);
        driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value=contains(text(), \"Локальный вход\")]"))).getText();
        boolean ttss = title.equals("Локальный вход");
        while (ttss != true){
            driver.findElement(By.xpath("//option[@value=contains(text(), \"Локальный вход\")]")).click();
            new Actions(driver)
                    .keyDown(Keys.DOWN)
                    .sendKeys("")
                    .perform();
            new Actions(driver)
                    .keyDown(Keys.ENTER)
                    .sendKeys("")
                    .perform();
            title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value=contains(text(), \"Локальный вход\")]"))).getText();
            ttss = title.equals("Локальный вход");
        }
        driver.findElement(By.xpath("//button[@id=\"btnLogin\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"validation-summary-errors\"]//ul//li[contains(text(), \"Неправильный способ входа, имя пользователя или пароль.\")]")));
        driver.navigate().refresh();
    }
    @Test
    public void Passitive(){
        //Введение валидных данных в поля
        driver.get("https://grc.eplat4m.ru/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@autofocus=\"True\"]")));
        driver.findElement(By.xpath("//input[@autofocus=\"True\"]")).sendKeys("САПУИБ\\test2");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("654321");
        driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value=contains(text(), \"Локальный вход\")]"))).getText();
        boolean ttss = title.equals("Локальный вход");
        while (ttss != true){
            driver.findElement(By.xpath("//option[@value=contains(text(), \"Локальный вход\")]")).click();
            new Actions(driver)
                    .keyDown(Keys.DOWN)
                    .sendKeys("")
                    .perform();
            new Actions(driver)
                    .keyDown(Keys.ENTER)
                    .sendKeys("")
                    .perform();
            title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value=contains(text(), \"Локальный вход\")]"))).getText();
            ttss = title.equals("Локальный вход");
        }
        driver.findElement(By.xpath("//button[@id=\"btnLogin\"]")).click();
        //Проверка активности вкладки "Соотвествие требованиям" по нахождению элемента на этой странице
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class=contains(text(), \" несоответствий по источникам требований\")]")));
        driver.findElement(By.xpath("(//div[@class=\"dashboardControl grc-dashboard-link grc-dashboard-Link\"]//a)[11]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div//label[@class=contains(text(), \"Проекты, которым необходимо назначить аудитора\")])[last()]")));
    }

}
