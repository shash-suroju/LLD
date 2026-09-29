import java.util.*;
import model.Group;
import model.SplitType;
import model.User;
import service.ExpenseService;

public class Main {

    public static void main(String[] args) {
        System.out.println("|-----------Split Wise------------|");

        User user1 = new User("u1", "John");
        User user2 = new User("u2", "Adam");
        User user3 = new User("u3", "Alice");

        List<User> users = new ArrayList<>();

        users.add(user3);
        users.add(user2);
        users.add(user1); 
        
        Group g1 = new Group("g1", "Flatmates", users);

        ExpenseService expenseService = new ExpenseService();

        expenseService.addExpense(user3, users, 250.0, SplitType.EQUAL, g1, null, "Partyy");




        // adding new expense with payer outside the group
        User user4 = new User("u4", "Bob");
        // expenseService.addExpense(user4, users, 505.25, SplitType.EQUAL, g1, null);

        // participant of another group
        users.add(user4);
        // expenseService.addExpense(user1, users, 505.25, SplitType.EQUAL, g1, null);


        // adding user 4to the group
        g1.addMember(user4);
        // EXACT split strategy
        expenseService.addExpense(user4, users, 1030.0, SplitType.EQUAL, g1, null, "dinner");


        expenseService.printGroupExpenses(g1.getGroupId());


        //printing balances by group
        expenseService.printBalances(g1.getGroupId());
    }
}