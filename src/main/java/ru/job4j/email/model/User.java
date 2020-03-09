package ru.job4j.email.model;

import java.util.Objects;

/**
 * Class User
 *
 * @author Petr B.
 * @since 20.01.2020, 7:55
 */
public class User {
    private final String name;
    private final String email;
    private boolean sendEmailFlag;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getEmailFlag() {
        return sendEmailFlag;
    }

    public void setEmailFlag(boolean sendEmailFlag) {
        this.sendEmailFlag = sendEmailFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name
                + '\''
                + ", email='" + email
                + '\''
                + ", sendEmailFlag=" + sendEmailFlag
                + '}';
    }
}
