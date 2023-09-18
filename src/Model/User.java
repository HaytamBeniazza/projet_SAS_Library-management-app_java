package Model;

public class User {
    private int subscriberId;
    private String name;
    private String email;
    private int phone;


    public User (String name, String email, int phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public int getId(){
        return subscriberId;
    }

    public void setPhone(int phone){
        this.phone = phone;
    }

    public void  setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(int id){
        this.subscriberId = id;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public int getPhone(){
        return phone;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + subscriberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}

