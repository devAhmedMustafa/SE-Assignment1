package utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SampleRepository extends Repository<String, String> {

}

public class RepositoryTest {

    @Test
    @DisplayName("Test add and get methods of Repository")
    public void testAdd() {
        SampleRepository repo = new SampleRepository();
        repo.add("key1", "value1");
        Assertions.assertEquals("value1", repo.get("key1"));
    }
}
