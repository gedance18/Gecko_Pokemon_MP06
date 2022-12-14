import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokemonsUrl {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> urls_pokemon = new ArrayList<>();

    /**
     * Este metodo coge los URLS de cada pokemon de las tablas
     */
    public void pokemonsUrls() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.wikidex.net/wiki/Lista_de_Pok%C3%A9mon");

        List<WebElement> tablas_pokemons = driver.findElements(new By.ByClassName("tabpokemon"));
        System.out.println("De cuantos pokemons quieres sus caracteristicas?");
        int pokemons_url = scanner.nextInt();
        int contador2 = 0;

        for (WebElement tabla : tablas_pokemons) {
            List<WebElement> pokemonRow = tabla.findElements(new By.ByTagName("tr"));
            for (WebElement row : pokemonRow) {
                List<WebElement> cells = row.findElements(new By.ByTagName("td"));
                int contador = 0;

                for (WebElement cell : cells) {
                    contador++;

                    if (contador == 2) {
                        contador2++;

                        if (contador2 > pokemons_url) {
                            return;
                        } else {
                            WebElement url = cell.findElement(new By.ByTagName("a"));
                            String url_string = url.getAttribute("href");

                            System.out.println(url_string);
                            urls_pokemon.add(url_string);
                        }

                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * Este metodo coge las caracteristicas de cada pokemon del que previamente hemos cogido su URL con el metodo pokemonURLS
     */
    public void caracteristicasPokemon() {
        //Cogiendo tabla de las caracteristicas
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);

        ArrayList<String[]> pokemonInfo = new ArrayList<>();

        for (String url : urls_pokemon) {
            int contador = 0;

            driver.get("" + url);
            WebElement nombre = driver.findElement(new By.ById("nombrepokemon"));
            System.out.println("---------------------");

            String[] info_pokemon = new String[100];
            info_pokemon[contador] = nombre.getText();
            contador ++;
            System.out.println(nombre.getText());
            System.out.println("---------------------");

            WebElement tabla_caracteristicas = driver.findElement(new By.ByClassName("datos"));
            List<WebElement> filas = tabla_caracteristicas.findElements(new By.ByTagName("tr"));
            int contador2 = 0;

            for (WebElement fila : filas) {
                contador2++;
                if (contador2 == 1) {

                } else if (contador2 == 3) {

                } else if (contador2 == 5) {

                } else if (contador2 == 8) {

                } else if (contador2 == 9) {

                } else if (contador2 == 10) {

                } else if (contador2 == 11) {

                } else if (contador2 == 13) {

                } else if (contador2 == 14) {

                } else if (contador2 == 15) {

                } else {
                    List<WebElement> celdas = fila.findElements(new By.ByTagName("td"));
                    contador ++;
                    info_pokemon[contador] = fila.getAttribute("title") + ": ";
                    System.out.print(fila.getAttribute("title") + ": ");

                    for (WebElement celda : celdas) {
                        contador ++;
                        info_pokemon[contador] = celda.getText();
                        System.out.println(celda.getText());
                    }
                }

            }
            System.out.println("---------------------");

            //Cogiendo el titulo Evolucion y el texto que contiene
            List<WebElement> subtitulos = driver.findElements(new By.ByTagName("h2"));
            int contador3 = 0;

            for (WebElement h2 : subtitulos) {
                contador3++;
                if (contador3 == 4) {
                    List<WebElement> spans = h2.findElements(new By.ByTagName("span"));

                    int contador4 = 0;
                    for (WebElement span : spans) {
                        contador4++;
                        if (contador4 == 2) {
                            contador ++;
                            info_pokemon[contador] = span.getText() + ": ";
                            System.out.print(span.getText() + ": ");
                            List<WebElement> evoluciones = driver.findElements(new By.ByTagName("p"));
                            int contador5 = 0;

                            for (WebElement evolucion : evoluciones) {
                                contador5++;
                                if (contador5 == 5){
                                    contador ++;
                                    info_pokemon[contador] = evolucion.getText();
                                    System.out.println(evolucion.getText());
                                }
                            }
                        }
                    }
                }
            }
            //Cogiendo los movimientos
            int contador6 = 0;
            for (WebElement h2 : subtitulos) {
                contador6++;
                if (contador6 == 8) {
                    List<WebElement> spans = h2.findElements(new By.ByTagName("span"));

                    int contador7 = 0;
                    for (WebElement span : spans) {
                        contador7++;
                        if (contador7 == 1) {
                            contador ++;
                            info_pokemon[contador] = span.getText() + ": ";
                            System.out.println(span.getText() + ": ");
                            WebElement table = driver.findElement(new By.ByClassName("movnivel"));
                            List<WebElement> trs = table.findElements(new By.ByTagName("tr"));

                            int contador8 = 0;
                            for (WebElement tr : trs) {
                                contador8++;
                                if (contador8 == 1) {
                                    List<WebElement> ths = tr.findElements(new By.ByTagName("th"));

                                    for (WebElement th : ths) {
                                        contador ++;
                                        info_pokemon[contador] = th.getText() + " ";
                                        System.out.print(th.getText() + " ");
                                    }
                                } else {
                                    List<WebElement> tds = tr.findElements(new By.ByTagName("td"));

                                    int contador9 = 0;
                                    for (WebElement td : tds) {
                                        contador9++;
                                        if (contador9 == 1){
                                            contador ++;
                                            info_pokemon[contador] = " " + td.getText() + " ";
                                            System.out.print(" " + td.getText() + " ");
                                        }
                                        else {
                                            WebElement a = td.findElement(new By.ByTagName("a"));
                                            contador ++;
                                            info_pokemon[contador] = "  " + a.getAttribute("title") + " ";
                                            System.out.print("  " + a.getAttribute("title") + " ");
                                        }

                                    }
                                }
                                System.out.println();
                            }
                            System.out.println();

                        }
                    }
                }
            }
            pokemonInfo.add(info_pokemon);
        }

        pokemonInfo.forEach(strings -> {
            for (String s:
                 strings) {
                System.out.print(s+"- ");
            }
            System.out.println();
        });

        try {
            ScrappingTables.writeAllLines(pokemonInfo,"data/caracteristicas_pokemon.csv");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}