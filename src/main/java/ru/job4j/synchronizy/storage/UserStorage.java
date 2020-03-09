package ru.job4j.synchronizy.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.synchronizy.storage.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Class UserStorage
 *
 * @author Petr B.
 * @since 11.01.2020, 13:32
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> listOfUsers;

    public UserStorage() {
        listOfUsers = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        boolean res = false;
        if (!checkUserInStorage(user)) {
            listOfUsers.put(user.getId(), user);
            res = true;
        }
        return res;
    }

    public synchronized boolean delete(User user) {
        boolean res = false;
        if (checkUserInStorage(user)) {
            listOfUsers.remove(user.getId());
            res = true;
        }
        return res;
    }

    public synchronized boolean update(User user) {
        boolean res = false;
        if (checkUserInStorage(user)) {
            listOfUsers.replace(user.getId(), user);
            res = true;
        }
        return res;
    }

    public synchronized String getUser(User user) {
        String res = null;
        if (checkUserInStorage(user)) {
            res = listOfUsers.get(user.getId()).toString();
        }
        return res;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean res = false;
        if (listOfUsers.containsKey(fromId) && listOfUsers.containsKey(toId)) {
            User userFrom = listOfUsers.get(fromId);
            User userTo = listOfUsers.get(toId);
            if (userFrom.getAmount() - amount >= 0) {
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
                listOfUsers.replace(userFrom.getId(), userFrom);
                listOfUsers.replace(userTo.getId(), userTo);
                res = true;
            }
        }
        return res;
    }

    private synchronized boolean checkUserInStorage(User user) {
        return listOfUsers.containsKey(user.getId());
    }

    public synchronized int getSizeOfStorage() {
        return listOfUsers.size();
    }
}
