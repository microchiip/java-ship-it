package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static ArrayList<Trackable> trackableItems = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(30);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(20);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());


            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackItem(); //не сразу увидела, что в тз для всех нужно
                    break;
                case 5:
                    trackAllItems();
                    break;
                case 6:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void trackAllItems(){
        if (trackableItems.isEmpty()) {
            System.out.println("\nНет посылок, поддерживающих трекинг!");
            return;
        }

        String newLocation;
        while (true) {
            System.out.print("\nВведите новое местоположение: ");
            newLocation = scanner.nextLine();

            if (!newLocation.isEmpty()) {
                break;
            } else {
                System.out.println("Error. Пустое значение.");
            }
        }

        System.out.println("Обновление статуса для " + trackableItems.size() + " отправлений:");

        for (int i = 0; i < trackableItems.size(); i++) {
            Trackable item = trackableItems.get(i);
            System.out.print((i + 1) + ". " + item.getDescription() + "\n");
            item.reportStatus(newLocation);
        }

        System.out.println("Статус всех отслеживаемых отправлений обновлён!");
    }

    private static void trackItem() {
        if (trackableItems.isEmpty()) {
            System.out.println("Нет посылок, поддерживающих трекинг!");
            return;
        }

        System.out.println("Отслеживаемые посылки:");
        for (int i = 0; i < trackableItems.size(); i++) {
            Trackable item = trackableItems.get(i);
            String description = item.getDescription();
            System.out.println("  " + (i + 1) + ". " + item.getDescription() + ". Адрес: " + item.getDeliveryAddress() + ".");
        }

        int index;
        while (true) {
            System.out.print("Введите номер посылки: ");
            if (scanner.hasNextInt()) {
                index = scanner.nextInt();
                scanner.nextLine();

                if (index >= 1 && index <= trackableItems.size()) {
                    break;
                } else {
                    System.out.print("Error: некорректное число.");
                }
            } else {
                System.out.print("Error: вы ввели не число.");
                scanner.nextLine();
            }
        }

        String newLocation;
        while (true) {
            System.out.print("Введите новое местоположение: ");
            newLocation = scanner.nextLine();

            if (!newLocation.isEmpty()) {
                break;
            } else {
                System.out.println("Error. Пустое значение.");
            }
        }

        System.out.println("Обновление статуса:");
        trackableItems.get(index - 1).reportStatus(newLocation);
        System.out.println("Статус обновлён!");

    }

    private static void showMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 - (ДЛЯ 1 ПОСЫЛКИ)сообщить местоположение (тест/только хрупкое)");
        System.out.println("5 - (ДЛЯ ВСЕХ ПОСЫЛОК)сообщить местоположение (тест/только хрупкое)");
        System.out.println("6 - Содержимое коробок");
        System.out.println("0 — Завершить");
    }


    private static void addParcel() {

        int type;
        while (true){
            System.out.println("Введите тип посылки (1 - стандарт, 2 - хрупкая, 3 - скоропортящиеся):");
            if(scanner.hasNextInt()){
                type = scanner.nextInt();
                scanner.nextLine();

                if(type >= 1 && type <= 3) break;
                else {
                    System.out.println("Error. Введите число от 1 до 3.");
                }
            }
            else {
                System.out.println("Error. Вы ввели не число.");
                scanner.nextLine();
            }
        }

        String description;
        while (true) {
            System.out.print("Введите описание посылки: ");
            description = scanner.nextLine();

            if (!description.isEmpty()) {
                break;
            } else {
                System.out.println("Error. Пустое значение.");
            }
        }

        int weight = 0;
        while (true) {
            System.out.print("Введите вес посылки: ");
            if (scanner.hasNextInt()) {
                weight = scanner.nextInt();
                scanner.nextLine();

                if (weight > 0) break;
                else {
                    System.out.println("Error. Вес посылки некорректный.");
                }
            } else {
                System.out.println("Error. Вы ввели не число.");
                scanner.nextLine();
            }
        }

        String address;
        while (true) {
            System.out.print("Введите адрес доставки: ");
            address = scanner.nextLine().trim();

            if (!address.isEmpty()) break;
            else {
                System.out.println("Error. Пустое значение.");
            }
        }

        int sendDay = 0;
        while (true) {
            System.out.print("Введите день отправки (число от 1 до 31): ");
            if (scanner.hasNextInt()) {
                sendDay = scanner.nextInt();
                scanner.nextLine();

                if (sendDay >= 1 && sendDay <= 31) {
                    break;
                } else {
                    System.out.println("Error. Число от 1 до 31.");
                }
            } else {
                System.out.println("Error. Вы ввели не число.");
                scanner.nextLine();
            }
        }

        Parcel parcel = null;

        switch (type) {
            case 1:
                parcel = new StandardParcel(description, weight, address, sendDay);
                System.out.println("\nСтандартная посылка успешно создана!");

                System.out.println("Добавление посылки в коробку 'Cтандарт'.");

                if (standardBox.addParcel((StandardParcel) parcel)) {
                    System.out.println("Стандартная посылка помещена в коробку!");
                } else {
                    System.out.println("Посылка создана, но не помещена в коробку (превышен вес)");
                }
                break;

            case 2:
                parcel = new FragileParcel(description, weight, address, sendDay);
                System.out.println("\nХрупкая посылка успешно создана!");
                trackableItems.add((Trackable) parcel);
                System.out.println("Посылка добавлена в систему трекинга!");

                System.out.println("Добавление посылки в коробку 'Хрупкое'.");
                if (fragileBox.addParcel((FragileParcel) parcel)) {
                    System.out.println("Хрупкая посылка помещена в коробку!");
                } else {
                    System.out.println("Посылка создана, но не помещена в коробку (превышен вес)");
                }
                break;

            case 3:
                int timeToLive = 0;
                while (true) {
                    System.out.print("Введите срок годности: ");
                    if (scanner.hasNextInt()) {
                        timeToLive = scanner.nextInt();
                        scanner.nextLine();

                        if (timeToLive > 0) {
                            break;
                        } else {
                            System.out.println("Error. Некорректное число.");
                        }
                    } else {
                        System.out.println("Error. Вы ввели не число.");
                        scanner.nextLine();
                    }
                }

                parcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
                System.out.println("\nСкоропортящаяся посылка успешно создана!");

                System.out.println("Добавление посылки в коробку 'Скоропортящаяся'.");
                if (perishableBox.addParcel((PerishableParcel) parcel)) {
                    System.out.println("Скоропортящаяся посылка помещена в коробку!");
                } else {
                    System.out.println("Посылка создана, но не помещена в коробку (превышен вес)");
                }

                break;
        }
        if (parcel != null) {
            allParcels.add(parcel);
            System.out.println("Посылка добавлена в список. Всего посылок: " + allParcels.size());
        }
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        if(allParcels.isEmpty()){
            System.out.println("\nСписок посылок пуст.");
            return;
        }
        System.out.println("\nОтправка посылок:");
        for (int i = 0; i < allParcels.size(); i++) {
            Parcel parcel = allParcels.get(i);
            System.out.println("\n    Отправка посылки " + (i + 1));
            parcel.packageItem();
            parcel.deliver();
        }

        System.out.println("\nВсе посылки успешно отправлены!");

    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        if (allParcels.isEmpty()) {
            System.out.println("\nНет посылок для расчёта стоимости!");
            return;
        }
        int summ = 0; //сумма стоимости
        for (int i = 0; i < allParcels.size(); i++){
            Parcel parcel = allParcels.get(i);
            summ += parcel.calculateDeliveryCost();
        }
        System.out.println("\nОбщая стоимость доставки: " + summ);
    }

    private static void showBoxContents() {
        System.out.println("\nВыберите тип коробки:");
        System.out.println("1. Стандартные посылки");
        System.out.println("2. Хрупкие посылки");
        System.out.println("3. Скоропортящиеся посылки");
        System.out.print("Ваш выбор: ");

        int choice;
        while (true){
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                scanner.nextLine();

                if(choice >= 1 && choice <= 3) break;
                else {
                    System.out.println("Error. Введите число от 1 до 3.");
                }
            }
            else {
                System.out.println("Error. Вы ввели не число.");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                System.out.println("\nКоробка 'Стандарт': ");
                standardBox.printBoxInfo();
                break;
            case 2:
                System.out.println("\nКоробка 'Хрупкое': ");
                fragileBox.printBoxInfo();
                break;
            case 3:
                System.out.println("\nКоробка 'Скоропортящаяся': ");
                perishableBox.printBoxInfo();
                break;
            default:
                System.out.println("\nError");
        }
    }


}


