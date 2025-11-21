package utils.storage;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SampleModel {
    @KeyAttribute
    public String key;

    public SampleModel(String key) {
        this.key = key;
    }
}

class SampleRepository extends Repository<SampleModel> {
    public SampleRepository() {
        super();
    }
}

public class RepositoryTest {

    @Test
    @DisplayName("Test add and get methods of Repository")
    public void testAdd() {
        SampleRepository repo = new SampleRepository();
        repo.add(new SampleModel("key1"));
        SampleModel retrieved = repo.get("key1");
    }
}
