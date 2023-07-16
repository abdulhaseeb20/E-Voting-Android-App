package com.example.semesterproject;

public class VoteDescription {

    private String party_name, party_symbol, area;

    public VoteDescription(String name, String partysymbol, String area)
    {
        party_name = name;
        party_symbol = partysymbol;
        this.area = area;
    }

    public String getParty_name()
    {
        return party_name;
    }

    public void setParty_name(String party_name)
    {
        this.party_name = party_name;
    }

    public String getParty_symbol()
    {
        return party_symbol;
    }

    public void setParty_symbol(String party_symbol)
    {
        this.party_symbol = party_symbol;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }
}
