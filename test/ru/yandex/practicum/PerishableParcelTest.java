package ru.yandex.practicum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerishableParcelTest {
    @Test
    @DisplayName("Тест метода isExpired")
    public void testIsExpired() {
        // Посылка с отправкой в день 10 и сроком годности 5 дней
        PerishableParcel parcel = new PerishableParcel("Молоко", 2, "ул. Центральная, 1", 10, 5);

        // Стандартный сценарий - посылка свежая
        assertTrue(parcel.isExpired(12), "День 12: прошло 2 дня из 5 - посылка свежая");

        // Граничный сценарий - ровно в последний день годности
        assertTrue(parcel.isExpired(15), "День 15: прошло ровно 5 дней - посылка еще свежая");

    }

}
