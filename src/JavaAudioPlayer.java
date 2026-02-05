import java.io.File;
import java.util.Scanner;

public class JavaAudioPlayer {

    public static void main(String[] args) {

        //Place your music file here, in the ""
        //Accept File are wav, AU, AIFF
        File file = new File("");

        if(!file.exists()){
            System.out.println("File does not exist");
            return;
        }

        try(Scanner input = new Scanner(System.in)) {

            AudioPlayer player = new AudioPlayer(file);

            String response;

            while(true){
                System.out.println("P = Play");
                System.out.println("S = Stop");
                System.out.println("R = Reset");
                System.out.println("Q = Quit");
                System.out.print("Enter your choice: ");

                response = input.nextLine().toUpperCase();

                switch(response){
                    case "P" -> player.play();
                    case "S" -> player.stop();
                    case "R" -> player.reset();
                    case "Q" -> {
                        player.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }

        } catch(Exception e){
            System.out.println("Error loading audio.");
        }
    }
}
