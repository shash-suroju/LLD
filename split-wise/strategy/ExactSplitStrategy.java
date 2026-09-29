package strategy;
import java.math.BigDecimal;
import java.util.*;
import model.Split;
import model.User;

public class ExactSplitStrategy implements  SplitStrategy {

    public ExactSplitStrategy() {

    }

    @Override
    public List<Split> calculateSplits(double amount, List<User> users, Map<User, BigDecimal> splitDetails) {
        int n = users.size();
        if(n<0) {
            throw new IllegalArgumentException("members cannot be empty");
        }

        List<Split> splits = new ArrayList<>();
        double amountPerUser = amount/n;

        for (User user: users) {
            Split split = new Split(user, amountPerUser);
            splits.add(split);
        }

        return splits;
    }
}