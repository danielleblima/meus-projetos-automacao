package br.com.cesarschool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class TestCesarSchool {

    public static void main(String[] args) throws InterruptedException {

        //Abrindo o navegador
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        String url = "https://www.cesar.school/";
        driver.get(url);

        //Definindo tempo de espera padrão de 5 segundos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;


        //Aceitar cookies do site
        driver.findElement(By.xpath("/html/body/div[1]/div/a[2]")).click();

        //Passa o cursor em cima do Menu School
        driver.findElement(By.xpath("//*[@id=\"menu-item-15376\"]/a/span[2]")).click();

        //Clicar em Blog
        driver.findElement(By.linkText("Blog")).click();

        //Na Segunda página da lista de posts

        //Encontrar o Segundo Post
        String tituloSegundoPost = driver.findElement(By.cssSelector("#post-64560 > div > div > header > h2 > a")).getText();
        Assertions.assertEquals("Resultado no ar! Saiu o listão de remanejados do Vestibular 2022.2 da CESAR School",tituloSegundoPost);

        //Imprimir Título do Segundo Post
        System.out.println("Título do Segundo Post: " +tituloSegundoPost);

        //Encontrar Data de Publicação
        String diaPublicacao = driver.findElement(By.xpath("//*[@id=\"post-64560\"]/div/div/div[1]/a/div/span/time[1]/span[2]")).getText();
        String mesPublicacao = driver.findElement(By.xpath("//*[@id=\"post-64560\"]/div/div/div[1]/a/div/span/time[1]/span[1]")).getText();
        String anoPublicacao = driver.findElement(By.xpath("//*[@id=\"post-64560\"]/div/div/div[1]/a/div/span/time[1]/span[3]")).getText();
        System.out.println(diaPublicacao + " de " + mesPublicacao + " de " + anoPublicacao);

        //Encontrar o Terceiro Post
        String tituloTerceiroPost = driver.findElement(By.xpath("//*[@id=\"post-64459\"]/div/div/header/h2")).getText();
        Assertions.assertEquals("Primeiro artigo acadêmico da CESAR School sobre o metaverso será apresentado no FICC, nos EUA", tituloTerceiroPost);

        //Imprimir Título do Terceiro Post
        System.out.println("Título do Terceiro Post: " +tituloTerceiroPost);

        //Encontrar o Autor do Terceiro Post
        String autorTerceiroPost = driver.findElement(By.xpath("//*[@id=\"post-64428\"]/div/div/header/div/span[2]/a/span")).getText();

        //Imprimir o Autor
        System.out.println("Autor do Terceiro post:" +autorTerceiroPost);


        // Scroll até o final da tela
        long intialLength = (long) js.executeScript("return document.body.scrollHeight");

        while (true) {
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long currentLength = (long) js.executeScript("return document.body.scrollHeight");
            if (intialLength == currentLength) {
                break;
            }
            intialLength = currentLength;

        }

        //Imprimir o Endereço do Cesar School

        String enderecoCesarSchool = driver.findElement(By.xpath("/html/body/div[4]/p")).getText();
        System.out.println("Endereço do School: " +enderecoCesarSchool);


        //Fechar o navegador
       driver.quit();


    }
}
