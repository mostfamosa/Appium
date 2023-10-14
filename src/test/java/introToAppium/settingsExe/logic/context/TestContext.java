package introToAppium.settingsExe.logic.context;

import introToAppium.settingsExe.logic.entites.enums.TestContextKey;

import java.util.HashMap;

public class TestContext {
    private HashMap<TestContextKey, Object> map;

    public TestContext() {
        this.map = new HashMap<>();
    }

    public <T> T get(TestContextKey key) {
        Object item = map.get(key);
        if (item != null) {
            return (T) item;
        } else {
            System.out.println("Item with key = " + key + ", not found in context");
            return null;//return null to catch it in the after hooks
        }
    }

    public void put(TestContextKey key, Object item) {
        map.put(key, item);
    }
}