package com.game.dto;

public class BankDTO {
    private String owner;
    private String acNum;

    public BankDTO () {}
    public BankDTO(String owner, String acNum) {
        this.owner = owner;
        this.acNum = acNum;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAcNum() {
        return acNum;
    }

    public void setAcNum(String acNum) {
        this.acNum = acNum;
    }
}
