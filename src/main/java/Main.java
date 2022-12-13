import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        ScrappingTables scrappingTables = new ScrappingTables();
        PokemonsUrl pokemonsUrl = new PokemonsUrl();

        scrappingTables.pokemonTables();
        pokemonsUrl.pokemonsUrls();
        pokemonsUrl.caracteristicasPokemon();


    }

}
