package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class AppSettings {
    public String url;
    public String country;
    public String email;
    public ArrayList<Card> cards;
}

