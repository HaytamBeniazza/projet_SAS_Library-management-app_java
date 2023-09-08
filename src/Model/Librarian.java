// Librarian.java
package Model;

public class Librarian {
    private String name;
    private String code;
    private String email;

    public Librarian(String name, String code, String email) {
        this.name = name;
        this.code = code;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Code: " + code + ", Email: " + email;
    }
}
