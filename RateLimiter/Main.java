import model.RateLimiterType;
import model.User;
import service.RateLimiterService;

public class Main {
    public static void main (String [] args) {


        User user = new User("abc", RateLimiterType.TOKEN_BUCKET);

        RateLimiterService rateLimiterService = new RateLimiterService();


        for(int i=0; i<20; i++) {
            System.out.println(rateLimiterService.allowRequest(user));
        }
    }
}