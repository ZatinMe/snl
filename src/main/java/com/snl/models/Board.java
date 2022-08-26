package src.main.java.com.snl.models;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    Integer checkPoint;
    Integer noOfDices;
    Map<Integer, Integer> transfers;
    Map<String, Integer> userPosition;

    public Board(Integer size, Map<Integer, Integer> transfers , List<User> users, Integer noOfDices){
        this.checkPoint = size;
        this.transfers = transfers;
        this.userPosition = new HashMap<>();
        this.noOfDices = noOfDices;
        users.forEach(user -> userPosition.put(user.getId(), 0));
    }

    public Integer getNoOfDices(){
        return this.noOfDices;
    }
    public Integer getCheckPoint(){
        return this.checkPoint;
    }
    public Map<Integer, Integer> getTransfers(){
        return this.transfers;
    }
    public Map<String, Integer> getUserPosition(){
        return this.userPosition;
    }
}
