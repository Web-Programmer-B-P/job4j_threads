package ru.job4j.synchronizy.storage;

import org.junit.Test;
import ru.job4j.synchronizy.storage.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class UserStorageTest
 *
 * @author Petr B.
 * @since 11.01.2020, 13:48
 */
public class UserStorageTest {
    private class ThreadAdd extends Thread {
        private final UserStorage storage;
        private final User user;

        private ThreadAdd(UserStorage storage, User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            storage.add(user);
        }
    }

    @Test
    public void whenTryToAddTheSameUsersParallel() throws InterruptedException {
        User user = new User(1, 500);
        UserStorage storage = new UserStorage();
        Thread addFirst = new ThreadAdd(storage, user);
        Thread addSecond = new ThreadAdd(storage, user);
        addFirst.start();
        addSecond.start();
        addFirst.join();
        addSecond.join();
        assertThat(storage.getSizeOfStorage(), is(1));
    }

    @Test
    public void whenAddUserTrue() {
        User user = new User(1, 1000);
        assertThat(new UserStorage().add(user), is(true));
    }

    @Test
    public void whenAddUserFalse() {
        User user = new User(1, 1000);
        UserStorage storage = new UserStorage();
        storage.add(user);
        assertThat(storage.add(user), is(false));
    }

    @Test
    public void whenDeleteUserTrue() {
        User user1 = new User(1, 1000);
        User user2 = new User(2, 500);
        UserStorage storage = new UserStorage();
        storage.add(user1);
        storage.add(user2);
        assertThat(storage.delete(user2), is(true));
    }

    @Test
    public void whenDeleteUserFalse() {
        User user1 = new User(1, 1000);
        UserStorage storage = new UserStorage();
        assertThat(storage.delete(user1), is(false));
    }

    @Test
    public void whenUpdateUser() {
        User user1 = new User(1, 1000);
        User user2 = new User(2, 500);
        UserStorage storage = new UserStorage();
        storage.add(user1);
        storage.add(user2);
        user1.setAmount(5000);
        storage.update(user1);
        assertThat(storage.getUser(user1), is("User{id=1, amount=5000}"));
    }

    @Test
    public void whenDoTransferTrue() {
        User user1 = new User(1, 1000);
        User user2 = new User(2, 500);
        UserStorage storage = new UserStorage();
        storage.add(user1);
        storage.add(user2);
        assertThat(storage.transfer(user1.getId(), user2.getId(), 800), is(true));
        assertThat(storage.getUser(user2), is("User{id=2, amount=1300}"));
    }

    @Test
    public void whenDoTransferFalse() {
        User user1 = new User(1, 1000);
        User user2 = new User(2, 500);
        UserStorage storage = new UserStorage();
        storage.add(user1);
        storage.add(user2);
        assertThat(storage.transfer(user1.getId(), user2.getId(), 1200), is(false));
    }
}