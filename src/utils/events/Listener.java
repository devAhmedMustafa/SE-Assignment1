package utils.events;

public interface Listener<T extends EventPayload> {
    void update(T event);
}
