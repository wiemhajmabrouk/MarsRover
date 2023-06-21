import java.util.HashMap;
import java.util.Map;

class Rover {

    private final static Map<Direction, Direction> turnLeft = new HashMap<>();
    private final static Map<Direction, Direction> turnRight = new HashMap<>();
    private  Map<Direction, Move> move = new HashMap<>();
    private Plateau plateau;
    private  int x;
    private  int y;
    private  Direction direction;
    static{
        turnLeft.put(Direction.N, Direction.W);
        turnLeft.put(Direction.W, Direction.S);
        turnLeft.put(Direction.S, Direction.E);
        turnLeft.put(Direction.E, Direction.N);

        turnRight.put(Direction.N, Direction.E);
        turnRight.put(Direction.E, Direction.S);
        turnRight.put(Direction.S, Direction.W);
        turnRight.put(Direction.W, Direction.N);
    }
    public Rover(int x, int y, Direction direction, Plateau plateau) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.plateau = plateau;
        move.put(Direction.N,() -> this.y++);
        move.put(Direction.E,() -> this.x++);
        move.put(Direction.W,() -> this.x--);
        move.put(Direction.S,() -> this.y--);

    }

    public Plateau getPlateau() {
        return plateau;
    }
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(String steps) throws RuntimeException {
        steps.chars()
                .mapToObj(step -> (char) step)
                .forEach(step -> {
                    if (step.equals(Step.Left.getValeur())) {
                        //utiliser des enums
                        direction =turnLeft.get(direction);
                    } else if (step.equals(Step.Right.getValeur())) {
                        direction = turnRight.get(direction);
                    } else if (step.equals(Step.Move.getValeur())) {
                        move.get(direction).move();
                        if(this.x > plateau.getWidth() || this.y > plateau.getHigth()){
                            throw new RuntimeException("Merci de vérifier votre entré, le rover ne peut pas sortir du plateau");
                        }
                    }
                });
    }

}