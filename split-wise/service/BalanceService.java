package service;
import java.util.*;
import model.User;

public class BalanceService {

    // map of groups -> <user - <user, amount>>
    private final Map<String, Map<User, Map<User, Double>>> balancesByGroup;

    public BalanceService() {
        this.balancesByGroup = new HashMap<>();
    }
    

    public void recordDebt(String groupId, User debtor, User creditor, double amount) {
        if (amount == 0 || debtor.equals(creditor)) {
            return;
        }

        Map<User, Map<User, Double>> currentBalances = balancesByGroup.computeIfAbsent(groupId, key -> new HashMap<>());

        adjust(currentBalances, debtor, creditor, amount);
        adjust(currentBalances, creditor, debtor, -amount);
    }


    public void printBalances(String groupId) {
        Map<User, Map<User, Double>> balances = balancesByGroup.get(groupId);

        for(Map.Entry<User, Map<User, Double>> entry: balances.entrySet()) {
            User user = entry.getKey();
            Map<User, Double> userExpenses = entry.getValue();

            System.out.println("User: -> " + user.getUserName());
            for(Map.Entry<User, Double> entry1: userExpenses.entrySet()) {
                System.out.println("-- Owes " + entry1.getKey() + ", Amount: " + entry1.getValue());
            }
        }
    }


    private void adjust (Map<User, Map<User, Double>> currentBalances, User debtor, User creditor, double amount) {
        Map<User, Double> row = currentBalances.computeIfAbsent(debtor, k -> new HashMap<>());
        
        Double net = row.merge(creditor, amount, Double::sum);
        if(net == 0.0) {
            row.remove(creditor);
        }        
    }
}