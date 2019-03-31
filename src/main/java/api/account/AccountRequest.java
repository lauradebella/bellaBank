package api.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String passportNumber;

    private String name;


    public AccountRequest(@JsonProperty("passportNumber") String passportNumber,
                          @JsonProperty("name") String name) {
        this.passportNumber = passportNumber;
        this.name = name;
    }

    public Account getAccount(){
        return new Account(this.passportNumber, this.name);
    }
}
