import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScrappingTables {

    Scanner scanner = new Scanner(System.in);

    /**
     * Este metodo scrapea todas las tablas que contienen las diferentes generacionoes de pokemon
     */
    public void pokemonTables() {
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
        ArrayList<String[]> pokemonInfo = new ArrayList<>();

        for (WebElement tabla : tablas_pokemons) {
            List<WebElement> pokemonRow = tabla.findElements(new By.ByTagName("tr"));

            for (WebElement row : pokemonRow) {

                if (contador2 > pokemons) {
                    pokemonInfo.forEach(strings -> {
                        for (String s:
                                strings) {
                            System.out.print(s+"- ");
                        }
                        System.out.println();
                    });

                    try {
                        writeAllLines(pokemonInfo,"data/pokemon.csv");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return;
                } else {
                    List<WebElement> cells = row.findElements(new By.ByTagName("td"));
                    int contador = 0;

                    String[] info_pokemon = new String[3];
                    for (WebElement cell : cells) {
                        if (contador == 2) {
                            List<WebElement> tipo_pokemons = cell.findElements(new By.ByTagName("a"));
                            String tipos = "";
                            for (WebElement tipo : tipo_pokemons) {
                                tipos += tipo.getAttribute("title") + ", ";
                            }
                            info_pokemon[contador] = tipos;
                            System.out.println(info_pokemon[contador]);
                        } else if (contador > 2) {

                        } else {
                            info_pokemon[contador] = cell.getText();
                            System.out.println(info_pokemon[contador]);
                        }
                        contador++;
                    }
                    pokemonInfo.add(info_pokemon);
                }
                System.out.println();
                contador2++;

            }
        }

    }

    public static void writeAllLines(List<String[]> lines, String path) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        writer.writeAll(lines);
        writer.close();
    }
}

