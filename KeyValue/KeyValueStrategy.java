public interface KeyValueStrategy {
    
    public String getKey(String key);
    
    public void putKey(String key, String value);
    
    public void deleteKey(String key);
}