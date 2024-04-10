package org.example.app.entity;

public class Users {

    private Long id;
    private String name;
    private String email;

    public Users() {
    }

    public Users(Long id, String name,
                 String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "id " + id +
                ", name: " + name + ", email: " + email + "\n";
    }
}
