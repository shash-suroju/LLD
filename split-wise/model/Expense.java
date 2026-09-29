package model;

import java.util.*;

public class Expense {

    private final String expenseId;

    private final User paidBy;

    private final String groupId;

    private final double amount;

    private final SplitType splitType;

    private final String description;

    private final List<Split> splits;

    public Expense (String expenseId, User paidBy, String groupId, Double amount, SplitType splitType, String description, List<Split> splits) {
        this.expenseId = expenseId;
        this.paidBy = paidBy;
        this.groupId = groupId;
        this.amount = amount;
        this.splitType = splitType;
        this.description = description;
        this.splits = List.copyOf(splits);
    }


    @Override
    public String toString() {
        // return "check";
        return "\n paidBy: " + paidBy + "\n amount: " + amount + "\n description: " + description;
    }
}