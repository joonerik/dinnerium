package dinnerium.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * An abstract class for containing different types of objects.
 *
 * @param <T> the type that the container is containing.
 */
public abstract class Container<T> implements Iterable<T> {

    private Collection<T> container;

    /** Initializes the container as a ArrayList. */
    public Container() {
        this.container = new ArrayList<>();
    }

    /**
     * Creates a Container object containing the provided collection of items.
     *
     * @param container the container of type T that the Container should be initialized with.
     */
    public Container(Collection<T> container) {
        this.setContainer(container);
    }

    /**
     * sets the collection and thus the container, used to validate the input of the constructor.
     *
     * @param container
     *        of collection
     * @throws IllegalArgumentException
     *        if container is invalid/empty
     */
    private void setContainer(Collection<T> container) {
        if (container.isEmpty()) {
            throw new IllegalArgumentException(
                    "Missing content");
        }
        this.container = new ArrayList<>(container);
    }

    /**
     * adds item to the container.
     *
     * @param item
     *        of type
     * @throws IllegalArgumentException
     *        if name is invalid
     */
    public void addItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add to container");
        }
        this.container.add(item);
    }

    /**
     * Returns a container.
     *
     * @return new container copy
     */
    public Collection<T> getContainer() {
        return new ArrayList<>(this.container);
    }

    /**
     * Returns the size of the container.
     *
     * @return number of elements in container
     */
    public int getContainerSize() {
        return this.container.size();
    }

    /**
     * Returns an iterator for the container.
     *
     * @return a container iterator
     */
    @Override
    public Iterator<T> iterator() {
        return this.container.iterator();
    }
}
