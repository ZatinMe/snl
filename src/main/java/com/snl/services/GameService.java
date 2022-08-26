package src.main.java.com.snl.services;

import lombok.AllArgsConstructor;
import src.main.java.com.snl.models.Board;
import src.main.java.com.snl.models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@AllArgsConstructor
public class GameService {
    private Board board;
    private ShuffleService shuffleService;
    private Queue<User> userRollOrder;
    private List<User> users;

    public GameService(Board board, ShuffleService shuffleService, List<User> users){
        this.board = board;
        this.shuffleService = shuffleService;
        this.users = users;
        userRollOrder = new LinkedList<>();
        userRollOrder.addAll(users);
    }

    public void startGame(){
        while(userRollOrder.size()>1) {
            User user = userRollOrder.poll();
            int moveLength = shuffleService.shuffleNDices();
            System.out.println(String.format("player %s rolled dices sum to value %d" ,user.getName(), moveLength ));
            int initialPositionOfUser = board.getUserPosition().get(user.getId());
            int finalPosition = getFinalPoition(initialPositionOfUser + moveLength);
            if(finalPosition == board.getCheckPoint()) {
                System.out.println(String.format("player %s with id %s has reached the checkpoint with position %d", user.getName(), user.getId(), getRank()));
                continue;
            }
            userRollOrder.add(user);
            if (finalPosition > board.getCheckPoint()) {
                System.out.println(String.format("player %s with id %s couldn't move", user.getName(), user.getId()));
                continue;
            }
            board.getUserPosition().put(user.getId(), finalPosition);
            System.out.println(String.format("player %s with id %s moved from %d to %d", user.getName(), user.getId(), initialPositionOfUser, finalPosition));
        }
    }

    private int getFinalPoition(int finalPosition) {
        while(board.getTransfers().containsKey(finalPosition)){
            finalPosition = board.getTransfers().get(finalPosition);
        }
        return finalPosition;
    }

    private Integer getRank() {
        return users.size() - userRollOrder.size();
    }
}