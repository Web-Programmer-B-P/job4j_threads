package ru.job4j.non;

import ru.job4j.non.exception.OptimisticException;
import ru.job4j.non.model.Base;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class NonBlockingAlgorithm
 *
 * @author Petr B.
 * @since 15.01.2020, 10:37
 */
public class NonBlockingAlgorithm {
    private ConcurrentHashMap<Integer, Base> list;

    public NonBlockingAlgorithm() {
        list = new ConcurrentHashMap<>();
    }

    public Base getModelById(int id) {
        return list.get(id);
    }

    public void add(Base model) {
        list.put(model.getId(), model);
    }

    public void delete(Base model) {
        list.remove(model.getId());
    }

    public void update(Base model) throws OptimisticException {
        list.computeIfPresent(model.getId(),
                (id, data) -> {
                    int newVersion = model.getVersion();
                    if (data.getVersion() != newVersion) {
                        throw new OptimisticException("version has already changed");
                    }
                    model.setVersion(++newVersion);
                    return model;
                });
    }

    public int getSize() {
        return list.size();
    }
}
