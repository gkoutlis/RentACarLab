package model;
import java.math.BigDecimal;


public class Car extends BaseEntity {
    private String model;
    private BigDecimal costPerDay;
    private int horsePower;
    private int seats;
    private Category category;

    public Car(String model, BigDecimal costPerDay, int horsePower, int seats, Category category){
        super();
        this.model = model;
        this.costPerDay = costPerDay;
        this.horsePower = horsePower;
        this.seats = seats;
        this.category = category;
    }

    public Car(int id,String model, BigDecimal costPerDay, int horsePower, int seats, Category category){
        super(id);
        this.model = model;
        this.costPerDay = costPerDay;
        this.horsePower = horsePower;
        this.seats = seats;
        this.category = category;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public int getSeats() {
        return seats;
    }

    public Category getCategory() {
        return category;
    }

    public String getModel(){
        return model;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setModel(String model){
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                ", model='" + model + '\'' +
                ", costPerDay=" + costPerDay +
                ", horsePower=" + horsePower +
                ", seats=" + seats +
                ", category=" + category +
                '}';
    }


}
