import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestDemo {

    public static void main(String[] args) throws InterruptedException {

        //Abrindo o navegador
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        String url = "https://www.discourse.org/";
        driver.get(url);

        WebElement clickDemo = driver.findElement(By.xpath("//a[text()='Demo']"));
        clickDemo.click();
        Thread.sleep(5000);

        //Trocando de página
        Set<String> st = driver.getWindowHandles();
        Iterator<String> it = st.iterator();
        String parent = it.next();
        String child = it.next();

        // switch to child
        driver.switchTo().window(child);
        Thread.sleep(1000);

        // Scroll até o final da tela
        JavascriptExecutor js = (JavascriptExecutor) driver;

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
        //Listando os títulos dos tópicos fechados
        List<WebElement> listaDeCadeado = driver.findElements(By.xpath("//*/tr/td/span/div/span[@title='This topic is closed; it no longer accepts new replies']/../../a"));

        System.out.println("Resposta da letra a:");
        for (WebElement cadeado : listaDeCadeado) {
            System.out.println(cadeado.getText());
        }

        //Listando quantidade de itens com categoria

        WebElement linkDeCategorias = driver.findElement(By.xpath("//a[text() = 'Categories']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", linkDeCategorias);
        Thread.sleep(5000);

        List<WebElement> listaCategorias = driver.findElements(By.xpath("//td[@class='category  ']/h3/a/div/span"));
        List<WebElement> listaDeTopicos = driver.findElements(By.xpath("//td[@class='topics']//span"));

        System.out.println("");
        System.out.println("Resposta da letra b:");

        for (int i = 0; i < listaCategorias.size(); i++) {
            System.out.println(listaCategorias.get(i).getText() + " " + listaDeTopicos.get(i).getText());
        }
        
        WebElement voltarParaDemo = driver.findElement(By.xpath("//h1[text()='Demo']"));
        voltarParaDemo.click();

        Thread.sleep(5000);

        WebElement visualizacoes = driver.findElement(By.xpath("//span[text()='Views']"));
        visualizacoes.click();

        Thread.sleep(5000);

        WebElement nomedoTopico = driver.findElement(By.xpath("//tbody/tr/td/span/a"));
        System.out.println("");
        System.out.println("Resposta da letra c:");
        System.out.println(nomedoTopico.getText());


        //Fechar navegador
        driver.quit();

    }

}
