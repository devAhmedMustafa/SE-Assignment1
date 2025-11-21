package utils.events;

import java.util.List;

public abstract class Event<T extends EventPayload> {
    protected List<Listener<T>> listeners;

    protected Event() {
        this.listeners = new java.util.ArrayList<>();
    }

    public void subscribe(Listener<T> listener) {
        listeners.add(listener);
    }

    public void unsubscribe(Listener<T> listener) {
        listeners.remove(listener);
    }

    public void notify(T event) {
        for (Listener<T> listener : listeners) {
            listener.update(event);
        }
    }
}
