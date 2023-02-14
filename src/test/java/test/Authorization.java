package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;
public class Authorization extends SetUpp{
    String user = "//input[@autofocus=\"True\"]";
    String password = "//input[@type=\"password\"]";
    String checkbox = "//input[@type=\"checkbox\"]";
    String linput = "//option[@value=contains(text(), \"Локальный вход\")]";
    String buttn = "//button[@id=\"btnLogin\"]";
    String url = "https://grc.eplat4m.ru/";

    public void findSendkey(String names, String name1){//Нахождение элемента со вставкой текста(X.Path)
        driver.findElement(By.xpath(names)).sendKeys(name1);
    }

    public void findClick(String names){//Нахождение + клик по элементу(X.Path)
        driver.findElement(By.xpath(names)).click();
    }
    public void waitVisibilityElement(String names){//Ожидание элемента(X.Path)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(names)));
    }

    public void checkingValue(Boolean valuess, String names, String title){//Проверка на совпадение элемента(X.Path)
        while (valuess != true){
            findClick(linput);
            new Actions(driver)
                    .keyDown(Keys.DOWN)
                    .sendKeys("")
                    .perform();
            new Actions(driver)
                    .keyDown(Keys.ENTER)
                    .sendKeys("")
                    .perform();
            title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linput))).getText();
            valuess = title.equals("Локальный вход");
        }
    }

    @Test
    public void Negative(){
        //Введение невалидных данных в поля
        driver.get(url);
        waitVisibilityElement(user);
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        String name = "САПУИБ\\NotExist" + n ;
        System.out.println(name);
        findSendkey(user, name);
        String passw = "wer" + n + "gg" + n;
        System.out.println(passw);
        findSendkey(password, passw);
        findClick(checkbox);
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linput))).getText();
        boolean ttss = title.equals("Локальный вход");
        checkingValue(ttss, linput, title);
        findClick(buttn);
        waitVisibilityElement("//div[@class=\"validation-summary-errors\"]//ul//li[contains(text(), \"Неправильный способ входа, имя пользователя или пароль.\")]");
        driver.navigate().refresh();
    }
    @Test
    public void Passitive(){
        //Введение валидных данных в поля
        driver.get(url);
        waitVisibilityElement(user);
        findSendkey(user, "САПУИБ\\test2");
        findSendkey(password, "654321");
        findClick(checkbox);
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linput))).getText();
        boolean ttss = title.equals("Локальный вход");
        checkingValue(ttss, linput, title);
        findClick(buttn);
        //Проверка активности вкладки "Соотвествие требованиям" по нахождению элемента на этой странице
        waitVisibilityElement("//label[@class=contains(text(), \" несоответствий по источникам требований\")]");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"dashboardControl grc-dashboard-link grc-dashboard-Link\"]//a)[11]"))).click();
        waitVisibilityElement("(//div//label[@class=contains(text(), \"Проекты, которым необходимо назначить аудитора\")])[last()]");
    }

}