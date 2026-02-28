public abstract class Parcel {
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    //стоимость посылки
    protected abstract int getBaseCost();

    //упаковать
    protected void basicPackage() {
        System.out.println("Посылка <<" + description + ">> упакована");
    }

    public void packageItem() {
        basicPackage();
    }

    //доставить
    public void deliver(){
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress);
    }

    //стоимость
    public int calculateDeliveryCost(){
        int cost = weight * getBaseCost();
        System.out.println("Стоимость доставки посылки <<" + description + ">>: " + cost);
        return cost;
    }
}
