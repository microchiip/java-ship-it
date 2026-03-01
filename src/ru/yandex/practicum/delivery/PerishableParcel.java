package ru.yandex.practicum.delivery;

//скоропортящиеся
public class  PerishableParcel extends Parcel {
    private static final int BASE_COST = 3;
    int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    //испорчена?
    public boolean isExpired(int currentDay) {
        // Посылка испорчена, если текущий день больше дня отправки + срок жизни
        return currentDay > (sendDay + timeToLive);
    }

}
