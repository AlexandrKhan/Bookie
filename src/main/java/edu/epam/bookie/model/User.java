package edu.epam.bookie.model;

import java.math.BigDecimal;
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
    private BigDecimal moneyBalance;
    private String passportScan;
    private StatusType statusType;

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.role = Role.USER;
        this.statusType = StatusType.NOT_ACTIVATED;
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

    public BigDecimal getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(BigDecimal moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(user.dateOfBirth) : user.dateOfBirth != null) return false;
        if (moneyBalance != null ? !moneyBalance.equals(user.moneyBalance) : user.moneyBalance != null) return false;
        if (passportScan != null ? !passportScan.equals(user.passportScan) : user.passportScan != null) return false;
        return statusType == user.statusType;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (moneyBalance != null ? moneyBalance.hashCode() : 0);
        result = 31 * result + (passportScan != null ? passportScan.hashCode() : 0);
        result = 31 * result + (statusType != null ? statusType.hashCode() : 0);
        return result;
    }
}
