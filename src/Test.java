package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB db = new DB();
		
		User user = new User("a", "a", "a", "123", "a");
		User user2 = new User("b", "b", "b", "321", "b");
		
		System.out.println(user.getUserID());
		System.out.println(user2.getUserID());
		user.favouriteUser(2);
		
		Auction auction = new Auction(2,1,1);
		
		UserList u = new UserList();
		ArrayList<User> users = u.getFavouriteUsers(1);
		
		Arrays.toString(users.toArray());
		
		AuctionList a = new AuctionList();
		ArrayList<Auction> auctions = a.getUsersAuctions(users);
		
		Arrays.toString(auctions.toArray());

	}

}
