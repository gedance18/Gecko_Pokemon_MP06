import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.Scanner;

public class ScrappingTables {

    Scanner scanner = new Scanner(System.in);
    public void pokemonTables(){
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);

        driver.get("https://www.wikidex.net/wiki/Lista_de_Pok%C3%A9mon");

        String titulo = driver.findElement(By.id("firstHeading")).getText();
        System.out.println("Cuantos pokemons quieres scrappear?");
        int pokemons = scanner.nextInt();
        System.out.println(titulo);
        System.out.print("|---------------|");
        int contador2 = 0;

        List<WebElement> tablas_pokemons = driver.findElements(new By.ByClassName("tabpokemon"));

        for (WebElement tabla : tablas_pokemons) {
            List<WebElement> pokemonRow = tabla.findElements(new By.ByTagName("tr"));
            for (WebElement row : pokemonRow) {
                if(contador2 > pokemons){
                    return;
                } else {
                    List<WebElement> cells = row.findElements(new By.ByTagName("td"));
                    int contador = 0;

                    for (WebElement cell : cells) {
                        contador++;

                        if (contador == 3) {
                            List<WebElement> tipo_pokemons = cell.findElements(new By.ByTagName("a"));

                            for (WebElement tipo : tipo_pokemons) {
                                System.out.println(tipo.getAttribute("title"));
                            }

                        } else if (contador > 3) {

                        } else {
                            System.out.println(cell.getText());
                        }
                    }
                }
                System.out.println();
                contador2 ++;
            }
        }
    }
}
