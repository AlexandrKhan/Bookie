package edu.epam.bookie.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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
    private String token;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.role = builder.role;
        this.dateOfBirth = builder.dateOfBirth;
        this.moneyBalance = builder.moneyBalance;
        this.passportScan = builder.passportScan;
        this.statusType = builder.statusType;
        this.token = builder.token;
    }

    public static class UserBuilder {
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
        private String token;

        public UserBuilder() {
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder withMoney(BigDecimal money) {
            this.moneyBalance = money;
            return this;
        }

        public UserBuilder withPassport(String passportScan) {
            this.passportScan = passportScan;
            return this;
        }

        public UserBuilder withStatus(StatusType status) {
            this.statusType = status;
            return this;
        }

        public UserBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public UserBuilder withNewUserValues() {
            this.role = Role.USER;
            this.statusType = StatusType.NOT_ACTIVATED;
            this.moneyBalance = new BigDecimal(0);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        return id == user.id && username.equals(user.username)
                && firstName.equals(user.firstName)
                && lastName.equals(user.lastName)
                && email.equals(user.email)
                && dateOfBirth.equals(user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }
}
