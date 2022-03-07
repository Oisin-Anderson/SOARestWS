package ie.ait.rest.bank;

import ie.ait.dao.bank.BankAccount;
import ie.ait.dao.bank.BankDao;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("accounts")
public class BankResource {
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<BankAccount> getAllAccounts() {
        return BankDao.instance.getAllAccounts();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{accountNumber}")
    public BankAccount getAccount(@PathParam("accountNumber") String accNumber) {
        return BankDao.instance.getAccount(accNumber);
    }
    
}
