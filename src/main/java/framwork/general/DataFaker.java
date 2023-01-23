package framwork.general;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.Locale;

public class DataFaker {

    public String howIMetYourMotherQuote(int maxlength) {
        Faker faker = new Faker(new Locale("he"));

        String quote = faker.howIMetYourMother().quote();
        while (quote.length() > maxlength) {
            quote = faker.howIMetYourMother().quote();
        }
        return (quote);
    }

    public String howIMetYourMotherCatchPhrase(int maxlength) {
        Faker faker = new Faker(new Locale("he"));

        String catchPhrase = faker.howIMetYourMother().catchPhrase();
        while (catchPhrase.length() > maxlength) {
            catchPhrase = faker.howIMetYourMother().catchPhrase();
        }
        return (catchPhrase);
    }


    public String gameOfThrones(int maxlength) {
        Faker faker = new Faker(new Locale("he"));

        String quote = faker.gameOfThrones().quote();
        while (quote.length() > maxlength) {
            quote = faker.gameOfThrones().quote();
        }
        return(quote);
    }

    public String dune(int maxlength) {
        Faker faker = new Faker(new Locale("he"));

        String saying = faker.dune().saying();
        while (saying.length() > maxlength) {
            saying = faker.gameOfThrones().quote();
        }
        return(saying);
    }

    public String rickAndMorty(int maxlength) {
        Faker faker = new Faker(new Locale("he"));

        String quote = faker.rickAndMorty().quote();
        while (quote.length() > maxlength) {
            quote = faker.gameOfThrones().quote();
        }
        return(quote);
    }

//    @Test
//    public void fakerDatatest() {
//        Faker faker = new Faker(new Locale("he"));
//
//        System.out.println(faker.rickAndMorty().quote());
//    }
}
