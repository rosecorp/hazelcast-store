import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by jakubkorczak on 17/07/2017.
 */
public class HazelcastStoreTest {

    private HazelcastStore testObj = new HazelcastStore();

    @BeforeClass
    public static void before() {
        startInstance();
    }

    @Test
    public void testStore() {
        testObj.store("key1", "value1");
        Assert.assertEquals("value1", testObj.get("key1"));
    }

    @Test
    public void isStoreNotAvailable() {
        Hazelcast.shutdownAll();
        Assert.assertFalse(testObj.isCache());
        startInstance();
    }

    @Test
    public void isStoreAvailable() {
        Assert.assertTrue(testObj.isCache());
    }

    public static void startInstance() {
        Config cfg = new Config();
        Hazelcast.newHazelcastInstance(cfg);
    }

}