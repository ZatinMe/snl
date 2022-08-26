package src.main.java.com.snl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public class User {
    String name;
    String id;

    public User(String name){
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }
}
