package src.main.java.com.snl.services;

import src.main.java.com.snl.models.Board;
import src.main.java.com.snl.models.ObstacleType;
import src.main.java.com.snl.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class BoardService {
    private Map<Integer, Integer> obstacles;
    private List<User> users;

    public BoardService(){
        this.obstacles = new HashMap<>();
        this.users = new ArrayList<>();
    }

    public List<User> getUsers(){
        return this.users;
    }

    public Board formBoard(){
        Scanner scanner = new Scanner(System.in);
        Integer boardSize = scanner.nextInt();
        Integer noOfDices = scanner.nextInt();

        int noOfPlayers = scanner.nextInt();
        for(int i=0; i<noOfPlayers; i++){
            users.add(new User(scanner.next()));
        }
        int src;
        int target;
        int noOfSnakes = scanner.nextInt();
        int noOfLadders = scanner.nextInt();
        for(int i=0;i<noOfSnakes; i++){
            src = scanner.nextInt();
            target = scanner.nextInt();
            if(validateEntry(src, target, ObstacleType.SNAKE))
                obstacles.put(src, target);
        }

        for(int i=0;i<noOfLadders; i++){
            src = scanner.nextInt();
            target = scanner.nextInt();
            if(validateEntry(src, target, ObstacleType.LADDER))
                obstacles.put(src, target);
        }
        return new Board(boardSize, obstacles, users, noOfDices);
    }

    private Boolean validateEntry(int src, int target, ObstacleType obstacleType){
        if(obstacles.containsKey(src)){
            Logger.getAnonymousLogger().info("already a snake or ladder exist at this position : " + src);
            return false;
        }
        else if(ObstacleType.SNAKE.equals(obstacleType) && target > src) {
            Logger.getAnonymousLogger().info(" snake must end at a lower value, actual values : " + src + ", " + target  );
            return false;
        }
        else if(ObstacleType.LADDER.equals(obstacleType) && target < src) {
            Logger.getAnonymousLogger().info(" ladder must end at a higher value, actual values : " + src + " , " + target  );
            return false;
        }
        return checkLoop(src, target);
    }

    private boolean checkLoop(int src, int target) {
        Map<Integer, Boolean> vis = new HashMap();
        vis.put(src, true);
        vis.put(target, true);
        while(obstacles.containsKey(target)){
            if(vis.containsKey(obstacles.get(target))) return false;
            vis.put(obstacles.get(target), true);
            target = obstacles.get(target);
        }
        return true;
    }
}
