package com.reader;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;


public class FileReaderTest {

    @Test
    public void testCalculateAgeInDays() {
        //Given
        LocalDate date = LocalDate.of(2018, Month.MARCH, 1);
        LocalDate currentDate = LocalDate.now();
        FileReader fileReader = new FileReader();

        //When
        int resultDay = fileReader.calculateAgeInDays(date);

        //Then
        assertEquals(5, resultDay);
    }

    @Test
    public void testCalculateAgeInYears() {
        //Given
        LocalDate date = LocalDate.of(2015, Month.MARCH, 1);
        LocalDate currentDate = LocalDate.now();
        FileReader fileReader = new FileReader();

        //When
        int resultYear = fileReader.calculateAgeInYears(date);

        //Then
        assertEquals(3, resultYear);
    }
}