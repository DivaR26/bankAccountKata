package utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DateGeneratorTest {

    private final DateGenerator dateGenerator = new SpecificDateGenerator();

    @Test
    void currentDateAsString(){
        String currentDate = dateGenerator.currentDateAsString();
        assertThat(currentDate,is("2022-01-02"));
    }

    @Test
    void getCurrentDate(){
        LocalDate currentDate = dateGenerator.getCurrentDate();
        assertThat(currentDate,is(LocalDate.of(2022,1,2)));
    }


    private static class SpecificDateGenerator extends DateGenerator {

        @Override
        public LocalDate getCurrentDate(){
            return LocalDate.of(2022,1,2);
        }
    }
}