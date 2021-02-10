package edu.epam.bookie.model;


import java.time.LocalDate;

public class User implements Entity {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private Role role;
    private LocalDate date_of_birth;
    private Double money_balance;

    public User() {
    }

    public User(String username, String first_name, String last_name, String email, String password, LocalDate date_of_birth) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Double getMoney_balance() {
        return money_balance;
    }

    public void setMoney_balance(Double money_balance) {
        this.money_balance = money_balance;
    }
}
