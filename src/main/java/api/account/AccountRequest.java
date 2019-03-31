package api.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

    private String passportNumber;

    private String name;


    public AccountRequest(@JsonProperty("passportNumber") String passportNumber,
                          @JsonProperty("name") String name) {
        this.passportNumber = passportNumber;
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }

    public Account getAccount(){
        return new Account(this.passportNumber, this.name);
    }
}
