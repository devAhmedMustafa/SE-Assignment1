package utils.events;

import java.util.HashMap;
import java.util.Map;

public class EventFactory {

    private static final Map<Class<? extends Event<? extends EventPayload>>, Event<? extends EventPayload>> eventRegistry = new HashMap<>();

    public static <T extends EventPayload> Event<T> getEvent(Class<? extends Event<T>> eventClass) {
        if (eventRegistry.containsKey(eventClass)) {
            return (Event<T>) eventRegistry.get(eventClass);
        } else {
            try {
                Event<T> eventInstance = eventClass.getDeclaredConstructor().newInstance();
                eventRegistry.put(eventClass, eventInstance);
                return eventInstance;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create event instance.", e);
            }
        }
    }

}
