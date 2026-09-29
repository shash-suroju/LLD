package strategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import model.Split;
import model.User;

public interface SplitStrategy {

    List<Split> calculateSplits(double amount, List<User> users, Map<User, BigDecimal> splitDetails);
}