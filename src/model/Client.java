package model;

public class Client extends BaseEntity {
    private String name;
    private String surname;
    private String gender;
    private String address;
    private String email;
    private String phone;

    public Client(String name, String surname, String gender, String address, String email, String phone){
        super();
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Client(int id,String name, String surname, String gender, String address, String email, String phone){
        super(id);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName(){
        return  name;
    }

    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return "name: " + name + ", surname: " + surname + ", gender: " + gender + ", address: " + address + ", email: "+ email + ", phone: " + phone;
    }
}
