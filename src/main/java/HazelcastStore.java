import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by jakubkorczak on 17/07/2017.
 */
public class HazelcastStore {

    private static final String STORE = "store";

    HazelcastInstance client;

    public HazelcastStore() {
        ClientConfig clientConfig = new ClientConfig();
        client = HazelcastClient.newHazelcastClient(clientConfig);
    }

    public void store(Object key, Object value) {
        client.getMap(STORE).put(key, value);
    }

    public Object get(Object key) {
        return client.getMap(STORE).get(key);
    }

    public boolean isCache() {
        try {
            client.getMap(STORE);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
