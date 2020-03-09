package ru.job4j.email.template;

/**
 * Class TemplateEmail
 *
 * @author Petr B.
 * @since 20.01.2020, 8:33
 */
public class TemplateEmail {
    private String subject = "Notification {username} to email {email}";
    private String body = "Add a new event to {username}";

    public String prepareSubject(String name, String email) {
        return subject.replaceAll("\\{(username)}", name).replaceAll("\\{(email)}", email);
    }

    public String prepareBody(String name) {
        return body.replaceAll("\\{(username)}", name);
    }
}
