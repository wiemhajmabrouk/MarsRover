import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarsRover {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Merci de précisier le fichier d'entré");
            return;
        }

        try (Stream<String> lignes = Files.lines(Paths.get(args[0]))) {
            List<String> inputLignes = lignes.collect(Collectors.toList());
            if(inputLignes != null && !inputLignes.isEmpty()){
                String[] plateauSize = inputLignes.get(0).split(" ");

                Plateau plateau = new Plateau(Integer.parseInt(plateauSize[0]),Integer.parseInt(plateauSize[1]));
                for (int i = 1; i < inputLignes.size(); i += 2) {

                    String roverPositionLine = inputLignes.get(i);
                    String[] roverPosition = roverPositionLine.split(" ");
                    int x = Integer.parseInt(roverPosition[0]);
                    int y = Integer.parseInt(roverPosition[1]);
                    String direction = roverPosition[2];
                    Rover rover = new Rover(x, y, Direction.valueOf(direction), plateau);
                    String steps = inputLignes.get(i + 1);
                    rover.move(steps);
                    System.out.println(rover.getX()+" "+rover.getY()+" "+rover.getDirection());
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur, merci de vérifier votre fichier d'entré");
            e.printStackTrace();
        }
        catch(RuntimeException e){
            System.out.println("Erreur, votre fichié d'entré n'est pas valide");
            e.printStackTrace();
        }
    }

}