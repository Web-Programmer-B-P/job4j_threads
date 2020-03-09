package ru.job4j.email;

import ru.job4j.email.model.User;
import ru.job4j.email.template.TemplateEmail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class EmailNotification
 *
 * @author Petr B.
 * @since 20.01.2020, 7:54
 */
public class EmailNotification {
    private final ExecutorService pool;
    private final TemplateEmail template;

    public EmailNotification() {
        this.template = new TemplateEmail();
        pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void emailTo(User user) {
        Runnable sendEmail = () -> {
            send(template.prepareSubject(
                    user.getName(), user.getEmail()),
                    template.prepareBody(user.getName()),
                    "someone`s@mail.ru");
            user.setEmailFlag(true);
        };
        pool.submit(sendEmail);
    }

    private void send(String subject, String body, String email) {
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
