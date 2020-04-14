package ua.meta.sarna;

public class Person {
    private String name;
    private String lastname;
    private int age;
    private boolean hasCar;
    private CarType carType;
    private boolean[] carUse;

    public Person(String name, String lastname, int age, boolean hasCar, CarType carType, boolean[] carUse) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.hasCar = hasCar;
        this.carType = carType;
        this.carUse = carUse;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public CarType getCarType() {
        return carType;
    }

    public boolean[] getCarUse() {
        return carUse;
    }

}
