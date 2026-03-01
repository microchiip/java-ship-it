package ru.yandex.practicum.delivery;


import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels;
    private int maxWeight;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public boolean addParcel(T parcel) {
        if (currentWeight + parcel.weight > maxWeight) {
            System.out.println("Error: превышен вес.");
            System.out.println("Текущий вес: " + currentWeight +
                    ", вес посылки: " + parcel.weight +
                    ", максимум: " + maxWeight);
            return false;
        }

        parcels.add(parcel);
        currentWeight += parcel.weight;
        System.out.println("Посылка <<" + parcel.description + ">> добавлена в коробку");
        System.out.println("Текущий вес коробки: " + currentWeight + "/" + maxWeight);
        return true;
    }

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public void printBoxInfo() {
        if (!parcels.isEmpty()) {
            System.out.println("Вес: " + currentWeight + "/" + maxWeight);
            System.out.println("Посылок: " + parcels.size());
            System.out.println("Список посылок:");
            for (int i = 0; i < parcels.size(); i++) {
                T p = parcels.get(i);
                System.out.println("    " + (i + 1) + ". " + p.description +
                        " (вес: " + p.weight + ", адрес: " + p.deliveryAddress + ")");
            }
        } else {
            System.out.println("Коробка пуста");
        }
    }
}
