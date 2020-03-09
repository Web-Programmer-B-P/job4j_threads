package ru.job4j.synchronizy.wrapper;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.synchronizy.dynamic.DynamicArrayContainer;
import ru.job4j.synchronizy.wrapper.interfaces.IThreadSafe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Class ThreadSafety
 *
 * @author Petr B.
 * @since 12.01.2020, 15:08
 */
@ThreadSafe
public class ThreadSafety<E> implements IThreadSafe<E> {
    @GuardedBy("this")
    private final DynamicArrayContainer<E> array;

    public ThreadSafety() {
        this.array = new DynamicArrayContainer<E>();
    }

    @Override
    public synchronized void add(E value) {
        array.add(value);
    }

    @Override
    public synchronized E get(int index) {
        return array.get(index);
    }

    @Override
    public synchronized int getSize() {
        return array.getSize();
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(array).iterator();
    }

    private List<E> copy(DynamicArrayContainer list) {
        List<E> dataFromOriginalContainer = new ArrayList<>();
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            dataFromOriginalContainer.add((E) itr.next());
        }
        return dataFromOriginalContainer;
    }
}
