package utils.events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SampleEventPayload implements EventPayload {
    public String message;

    public SampleEventPayload(String message) {
        this.message = message;
    }
}

class SampleEvent extends Event<SampleEventPayload> {
    public SampleEvent() {
        super();
    }
}

public class EventFactoryTest {

    @Test
    @DisplayName("Test get event not added yet")
    public void testGetEventNotAddedYet() {
        Event<SampleEventPayload> event = EventFactory.getEvent(SampleEvent.class);

        Assertions.assertNotNull(event);
        Assertions.assertEquals(SampleEvent.class, event.getClass());
    }

    @Test
    @DisplayName("Test get event after adding")
    public void testGetEventAfterAdding() {
        Event<SampleEventPayload> event = EventFactory.getEvent(SampleEvent.class);

        Assertions.assertNotNull(event);
        Assertions.assertEquals(SampleEvent.class, event.getClass());

        Event<SampleEventPayload> sameEvent = EventFactory.getEvent(SampleEvent.class);
        Assertions.assertSame(event, sameEvent);
    }
}
