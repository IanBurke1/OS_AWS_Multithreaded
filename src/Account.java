/*
 * This is a bean class for user account information
 */
public class Account {
	private String name;
	private String address;
	private int accNum;
	private String username;
	private String password;
	private double balance;
	private double amount;
	
//====CONSTRUCTORS===================================================================================================================================
	//default Constructor
	public Account(){
		balance = 0.0;
	} 
	
	public Account(String name, String address, int accNum, String username, String password, double balance) {
		super();
		this.name = name;
		this.address = address;
		this.accNum = accNum;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
//====GETTERS AND SETTERS=============================================================================================================================

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "[ Name: " + name + ", Address: " + address + ", Account No: " + accNum + ", username: " + username + ", Password: " + password +"]\n";
	}
	
	//Deposit
	public void deposit(double amount){
		balance += amount;
	}
	
	//Withdraw
	public void withdraw(double amount){
		balance -= amount;
	}
	

	
	
	
}
