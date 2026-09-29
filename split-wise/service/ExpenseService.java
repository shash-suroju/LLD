package service;

import java.math.BigDecimal;
import java.util.*;
import model.Expense;
import model.Group;
import model.Split;
import model.SplitType;
import model.User;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.SplitStrategy;

public class ExpenseService {

    private final Map<SplitType, SplitStrategy> strategies;

    private final Map<String, List<Expense>> expensesByGroup;

    private final BalanceService balanceService;

    public ExpenseService() {
        expensesByGroup = new HashMap<>();

        strategies = new HashMap<>();
        strategies.put(SplitType.EQUAL, new EqualSplitStrategy());
        strategies.put(SplitType.EXACT, new ExactSplitStrategy());

        balanceService = new BalanceService();
    }

    //add expense functionality
    public void addExpense(User payer, List<User> members, double amount, SplitType splitType, Group group, Map<User, BigDecimal> splitDetails, String description)  {

        //validations
        validate(payer, members, amount, splitType, group);

        // calculate Splits from the splitDetails
        SplitStrategy strategy = strategies.get(splitType);
        Map<User, BigDecimal> details =
                splitDetails == null ? Collections.emptyMap() : splitDetails;
                
        List<Split> splits = strategy.calculateSplits(amount, members, details);

        // validateSplits()

        // record an Expense and add it to the expenseGroup
        String randomId = UUID.randomUUID().toString();
        Expense expense = new Expense(randomId, payer, group.getGroupId(), amount, splitType, description, splits);


        expensesByGroup.computeIfAbsent(group.getGroupId(), k -> new ArrayList<>()).add(expense);


        // update balances.
        for (Split split : splits) {
            if(!split.getUser().equals(payer)) {
                balanceService.recordDebt(group.getGroupId(), split.getUser(), payer, split.getAmount());
            }
        }
    }



    public void printGroupExpenses(String groupId) {
        List<Expense> expenses = expensesByGroup.get(groupId);

        for(Expense expense: expenses) {
            System.out.println(expense);
        }
    }

    public void printBalances (String groupId) {
        balanceService.printBalances(groupId);
    }
 


    private void validate (User payer, List<User> members, double amount, SplitType splitType, Group group) {
        if (!group.hasMember(payer)) {
            throw new IllegalArgumentException("Payer must be part of the group");
        }

        for (User user: members) {
            if(!group.hasMember(user)) {
                throw new IllegalArgumentException("Participant should be part of the group");
            }
        }
    }
}