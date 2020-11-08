package ru.netology.domain;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Data
@NoArgsConstructor
public class DataGenerator {
    public static Faker faker = new Faker(new Locale("ru"));

    public static String getCity() {
        List<String> list = Arrays.asList("Омск","Санкт-Петербург", "Иркутск", "Мурманск", "Пенза", "Ярославль", "Севастополь", "Смоленск", "Краснодар", "Москва");
        Random random = new Random();
        String city = list.get(random.nextInt(list.size()));
        return city;
    }

    public static String getDate(int day) {
        String dateOfDelivery = LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return dateOfDelivery;
    }

    public static String getName() {
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    public static String getPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}

