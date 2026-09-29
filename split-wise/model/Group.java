package model;
import java.util.*;

public class Group {

    private final String groupId;

    private final String name;

    private Set<User> users;

    public Group (String groupId, String name, Collection<User> users) {
        this.groupId = groupId;
        this.name = name;
        this.users = new HashSet<>();
        this.users.addAll(users);
    }

    public String getGroupId() {
        return groupId;
    }

    public boolean hasMember(User user) {
        return users.contains(user);
    }

    public void addMember(User user) {
        users.add(user);
    }

}