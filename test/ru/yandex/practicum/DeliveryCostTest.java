package ru.yandex.practicum;

import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.Parcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DeliveryCostTest {
    // Тесты для вычисления стоимости посылок
    @Test
    @DisplayName("Тест расчета стоимости стандартной посылки")
    public void testStandardParcelCost() {
        // Стандартный сценарий
        Parcel standardParcel = new StandardParcel("Книги", 5, "ул. Ленина, 10", 15);
        int cost = standardParcel.calculateDeliveryCost();
        assertEquals(10, cost, "Стоимость стандартной посылки: вес 5 * базовый тариф 2 = 10");

        // Граничный сценарий - минимальный вес
        Parcel minWeightParcel = new StandardParcel("Письмо", 1, "ул. Пушкина, 5", 20);
        assertEquals(2, minWeightParcel.calculateDeliveryCost(), "Минимальный вес 1 * 2 = 2");

    }

    @Test
    @DisplayName("Тест расчета стоимости хрупкой посылки")
    public void testFragileParcelCost() {
        // Стандартный сценарий
        Parcel fragileParcel = new FragileParcel("Стекло", 5, "ул. Мира, 15", 12);
        int cost = fragileParcel.calculateDeliveryCost();
        assertEquals(20, cost, "Стоимость хрупкой посылки: вес 5 * базовый тариф 4 = 20");

        // Граничный сценарий - минимальный вес
        Parcel minWeightParcel = new FragileParcel("Маленькая ваза", 1, "ул. Кирова, 7", 18);
        assertEquals(4, minWeightParcel.calculateDeliveryCost(), "Вес 1 * 4 = 4");

    }

    @Test
    @DisplayName("Тест расчета стоимости скоропортящейся посылки")
    public void testPerishableParcelCost() {
        // Стандартный сценарий
        Parcel perishableParcel = new PerishableParcel("Продукты", 4, "ул. Пищевая, 8", 14, 5);
        int cost = perishableParcel.calculateDeliveryCost();
        assertEquals(12, cost, "Стоимость скоропортящейся посылки: вес 4 * базовый тариф 3 = 12");

        // Граничный сценарий - минимальный вес
        Parcel minWeightParcel = new PerishableParcel("Йогурт", 1, "ул. Молочная, 2", 22, 3);
        assertEquals(3, minWeightParcel.calculateDeliveryCost(), "Вес 1 * 3 = 3");

    }
}
