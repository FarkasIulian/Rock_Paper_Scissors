import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

enum Game {
    ROCK,
    PAPER,
    SCISSORS
}
    /*
        0 - ROCK         0 - 0    0 - 1 => Lose  1 - 0 => Win   2 - 0 => Lose
        1 - PAPER        1 - 1    0 - 2 => Win   1 - 2 => Lose  2 - 1 => Win
        2 - SCISSORS     2 - 2
    */

public class Main {

    public static int citire_fisier(String path,String name) throws FileNotFoundException {
        Scanner file = new Scanner(new File("rating.txt"));
        String fisier = "";
        String[] players;
        while (file.hasNextLine())
            fisier += file.nextLine() + " ";
        fisier.trim();
        players = fisier.split(" ");
        for (int i = 0; i < players.length; i = i + 2)
            if (name.toLowerCase().equals(players[i].toLowerCase())) {
                file.close();
                return Integer.parseInt(players[i + 1]);
            }
        file.close();
        return 0;
    }

    public static Game getUser(String opt){
        return Game.valueOf(opt.toUpperCase());
    }
    public static Game getComputer(){
        Random random = new Random();
        Game[] aux = Game.values();
        return aux[random.nextInt(3)];
    }
    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String option,name;
        Game user = null;
        Game computer;
        final int[][] solutions = {{0, -1, 1}, {1, 0, -1}, {-1, 1, 0}};
        int score;
        System.out.println("Enter your name: ");
        name = scanner.nextLine();
        System.out.println("Hello, " + name);
        score = citire_fisier("rating.txt",name);
        while (true) {
            System.out.println("rock,paper,scissors,!rating or !exit: ");
            option = scanner.nextLine();
            switch (option.toLowerCase()){
                case "rock":
                case "paper":
                case "scissors":
                    user = getUser(option);
                    computer = getComputer();
                    int opt = solutions[user.ordinal()][computer.ordinal()];
                    switch (opt){
                        case 1:
                            score += 100;
                            System.out.println("Well done you won. The computer chose " + computer.name().toLowerCase() + " and failed");
                            break;
                        case 0:
                            score += 50;
                            System.out.println("There is a draw " + computer.name().toLowerCase());
                            break;
                        case -1:
                            System.out.println("Sorry you lost, but the computer chose " + computer.name().toLowerCase());
                            break;
                        default:
                            System.out.println("Error.");
                            break;
                    }
                    break;
                case "!rating":
                    System.out.println("Your rating: " +score);
                    break;
                case "!exit":
                    scanner.close();
                    exit(0);
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
