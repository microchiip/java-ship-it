package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelBoxTest {
    // Тесты для добавления посылок в коробку
    private ParcelBox<StandardParcel> standardBox;
    private ParcelBox<FragileParcel> fragileBox;
    private ParcelBox<PerishableParcel> perishableBox;

    @BeforeEach
    public void setUp() {
        standardBox = new ParcelBox<>(50);
        fragileBox = new ParcelBox<>(30);
        perishableBox = new ParcelBox<>(20);
    }

    @Test
    @DisplayName("Тест добавления стандартных посылок в коробку")
    public void testAddStandardParcelToBox() {
        StandardParcel parcel1 = new StandardParcel("Книги", 10, "Адрес 1", 1);
        StandardParcel heavyParcel = new StandardParcel("Тяжелая", 16, "Адрес", 1);
        ParcelBox<StandardParcel> smallBox = new ParcelBox<>(15);

        // Стандартный сценарий - добавление посылки в пустую коробку
        assertTrue(standardBox.addParcel(parcel1), "Посылка с весом 10 должна добавиться");
        assertEquals(1, standardBox.getAllParcels().size(), "Посылка добавлена в коробку");

        // Граничный сценарий - превышение максимального веса
        assertFalse(smallBox.addParcel(heavyParcel), "Посылка с весом 16 не должна добавиться");
        assertEquals(0, smallBox.getAllParcels().size(), "Посылка НЕ добавлена в коробку");
    }

    @Test
    @DisplayName("Тест добавления хрупких посылок в коробку")
    public void testAddFragileParcelToBox() {
        FragileParcel parcel1 = new FragileParcel("Стекло", 5, "Адрес 1", 1);
        FragileParcel parcel2 = new FragileParcel("Еще стекло", 25, "Адрес 2", 2);

        // Стандартный сценарий - добавление посылки в пустую коробку
        assertTrue(fragileBox.addParcel(parcel1), "Посылка с весом 5 должна добавиться");
        assertEquals(1, fragileBox.getAllParcels().size(), " Посылка добавлена в коробку ");

        // Граничный сценарий - точное совпадение с лимитом
        assertTrue(fragileBox.addParcel(parcel2), "Посылка с весом 25 должна добавиться");
        assertEquals(2, fragileBox.getAllParcels().size(), "В коробке 2 посылки (текущий вес 5 + 25 = 30)");
    }

    @Test
    @DisplayName("Тест добавления скоропортящихся посылок в коробку")
    public void testAddPerishableParcelToBox() {
        PerishableParcel parcel1 = new PerishableParcel("Молоко", 8, "Адрес 1", 1, 5);
        PerishableParcel parcel2 = new PerishableParcel("Мясо", 13, "Адрес 2", 2, 3);

        // Стандартный сценарий - добавление посылки в пустую коробку
        assertTrue(perishableBox.addParcel(parcel1), "Посылка с весом 8 должна добавиться");
        assertEquals(1, perishableBox.getAllParcels().size(), " Посылка добавлена в коробку ");

        // Граничный сценарий - превышение максимального веса коробки
        assertFalse(perishableBox.addParcel(parcel2), "Посылка с весом 13 не должна добавиться");
        assertEquals(1, perishableBox.getAllParcels().size(), " Посылка НЕ добавлена в коробку. В коробке осталась 1 посылка");
    }

}


