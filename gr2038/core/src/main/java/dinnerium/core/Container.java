package dinnerium.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class Container<T> implements Iterable<T> {

    private Collection<T> container;

    /** Initializes the collections ingredients as a ArrayList. */
    public Container() {
        this.container = new ArrayList<>();
    }

    /** sets ingredients.  */
    public Container(Collection<T> container) {
        this.setCollection(container);
    }

    public void setCollection(Collection<T> container) {
        if (container.isEmpty()) {
            throw new IllegalArgumentException(
                    container + "invalid");
        }
        this.container = container;
    }

    public void addItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add to container");
        }
        this.container.add(item);
    }

    public Collection<T> getContainer() {
        return this.container;
    }

    public int getContainerSize() {
        return this.container.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.container.iterator();
    }
}
