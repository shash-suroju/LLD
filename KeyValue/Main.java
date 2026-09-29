

public class Main
{
	public static void main(String[] args) {
	    
	    
	    
	   // private final Map<Eviction, KeyValueStrategy> strategies;
    
    //     public KeyValueService() {
    //         strategies = new HashMap<>();
    //         strategies.put(Eviction.LRU, new LRUStrategy(2));
    //         // strategies.put(Eviction.LFU, new LFUStrategy());
    //     }
        
        
    //     public String getKey(String key) {
    //         return strategies.get(Eviction.LRU).getKey(key);
    //     }
        
        
    //     public void addKey(Eviction evictionType, String key, String value) {
    //         KeyValueStrategy strategy = strategies.get(evictionType);
    //         strategy.putKey(key, value);
    //     }
        
		LRUStrategy lruStrategy = new LRUStrategy(3);
		String key1 = "k1";
		String key2 = "k2";
		
		lruStrategy.putKey(key1, "v1");
		lruStrategy.putKey(key2, "v2");
		lruStrategy.putKey("k3", "v3");
		
		System.out.println(lruStrategy.getKey(key1));
		
		
		lruStrategy.putKey("k4", "v4");
		
		System.out.println(lruStrategy.getKey(key2));
		System.out.println(lruStrategy.getKey(key1));
		
	}
}