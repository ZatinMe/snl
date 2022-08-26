package src.main.java.com.snl.services;

import lombok.AllArgsConstructor;

import java.util.Random;

public class ShuffleService {
    private int noOfDices;

    public ShuffleService(int noOfDices){
        this.noOfDices = noOfDices;
    }

    public int shuffleNDices(){
        int finalValue = 0;
        for(int i=0; i<noOfDices; i++){
            finalValue += getDiceValue();
        }
        return finalValue;
    }

    private int getDiceValue() {
        return 1 + new Random().nextInt(6);
    }
}
