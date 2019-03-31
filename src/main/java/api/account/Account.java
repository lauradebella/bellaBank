package api.account;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NamedQuery(name="Account.findById", query="SELECT a from Account a WHERE a.id=:ACCOUNTID")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String passportNumber;

    private String name;


    public Account(String passportNumber, String name) {
        this.passportNumber = passportNumber;
        this.name = name;
    }

    public Account() {

    }

    public long getId() {
        return id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }

}
