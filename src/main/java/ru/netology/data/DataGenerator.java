package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
public class DataGenerator {
    private DataGenerator() {}

    public static String getCity() {
        Random city = new Random();
        List<String> list = Arrays.asList("Москва", "Санкт-Петербург", "Майкоп", "Ижевск", "Владивосток");
        int randomIndex = city.nextInt(list.size());
        String randomElement = list.get(randomIndex);
        return randomElement;
    }

    public static String getName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getPhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static String date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(3);
        date.format(formatter);
        return date.format(formatter);
    }
}