package ie.ait.dao.bank;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="account")
@XmlType(propOrder = {"branch_code", "account_number", "cust_name", "cust_address", "cust_rating", "balance"})
public class BankAccount {
    private String branch_code;
    private String account_number;
    private String cust_name;
    private String cust_address;
    private int cust_rating;
    private float balance;
    
    public BankAccount (String branch_code, String account_number, String cust_name, String cust_address, int cust_rating, float balance) {
        this.branch_code = branch_code;
        this.account_number = account_number;
        this.cust_name = cust_name;
        this.cust_address = cust_address;
        this.cust_rating = cust_rating;
        this.balance = balance;
    }
    
    public BankAccount() {
    }

    public String getBCode() {
        return branch_code;
    }

    public void setBCode(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getAcNumber() {
        return account_number;
    }

    public void setAcNumber(String account_number) {
        this.account_number = account_number;
    }

    public String getCustName() {
        return cust_name;
    }

    public void setCustName(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCustAddress() {
        return cust_address;
    }

    public void setCustAddress(String cust_address) {
        this.cust_address = cust_address;
    }

    public int getCustRating() {
        return cust_rating;
    }

    public void setCustRating(int cust_rating) {
        this.cust_rating = cust_rating;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "branch_code=" + branch_code + ", account_number = " + account_number + ", cust_name=" + cust_name + ", cust_address=" + cust_address + ", cust_rating=" + cust_rating +  ", balance = " + balance + '}';
    }
    
    
}
