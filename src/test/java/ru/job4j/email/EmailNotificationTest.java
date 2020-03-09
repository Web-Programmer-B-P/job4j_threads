package ru.job4j.email;

import org.junit.Test;
import ru.job4j.email.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class EmailNotificationTest
 *
 * @author Petr B.
 * @since 20.01.2020, 10:01
 */
public class EmailNotificationTest {
    @Test
    public void whenSendEmail() {
        User user1 = new User("First User", "first@mail.ru");
        User user2 = new User("Second User", "second@mail.ru");
        EmailNotification poolEmailTasks = new EmailNotification();
        poolEmailTasks.emailTo(user1);
        poolEmailTasks.close();
        assertThat(user1.getEmailFlag(), is(true));
        assertThat(user2.getEmailFlag(), is(false));
    }
}