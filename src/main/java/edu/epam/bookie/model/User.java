package edu.epam.bookie.model;

import java.time.LocalDate;

public class User implements Entity {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private LocalDate dateOfBirth;
    private Double moneyBalance;
    private String passportScan;
    private StatusType statusType;

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String passportScan) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.passportScan = passportScan;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = StatusType.valueOf(statusType.toUpperCase());
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public String getPassportScan() {
        return passportScan;
    }

    public void setPassportScan(String passportScan) {
        this.passportScan = passportScan;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(Double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }
}
