package ua.meta.sarna;

public class Flat {
    @Id
    private int flat;
    private String district;
    private String address;
    private int area;
    private int roomCount;
    private int price;

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "flat='" + flat + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", roomCount=" + roomCount +
                ", price=" + price +
                '}';
    }

    public Flat(){

    }

    public Flat(int flat, String district, String address, int area, int roomCount, int price) {
        this.flat = flat;
        this.district = district;
        this.address = address;
        this.area = area;
        this.roomCount = roomCount;
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
