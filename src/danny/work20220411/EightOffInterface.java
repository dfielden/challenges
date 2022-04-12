package danny.work20220411;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EightOffInterface {
    private final BufferedReader reader;
    private final EightOff eightOff;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public EightOffInterface(BufferedReader reader, EightOff eightOff) {
        this.reader = reader;
        this.eightOff = eightOff;
        eightOff.initialiseGame();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        EightOff eightOff = new EightOff();
        EightOffInterface game = new EightOffInterface(reader, eightOff);

        System.out.println(eightOff);
        System.out.println(instructions());

        inputs:
        while (true) {
            String command = game.reader.readLine();

            switch (command) {
                case "p": {
                    System.out.println("Take card from which stack?: ");
                    int stackNum = Integer.parseInt(reader.readLine());
                    System.out.println("Move card to which space?: ");
                    int spaceNum = Integer.parseInt(reader.readLine());
                    try {
                        game.eightOff.moveCardFromStackToSpace(stackNum, spaceNum);
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
                    }
                    break;
                }
                case "t": {
                    System.out.println("Take card from which space?: ");
                    int spaceNum = Integer.parseInt(reader.readLine());
                    System.out.println("Move card to which stack?: ");
                    int stackNum = Integer.parseInt(reader.readLine());
                    try {
                        game.eightOff.moveCardFromSpaceToStack(spaceNum, stackNum);
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
                    }
                    break;
                }
                case "w":
                    System.out.println("First space to swap:");
                    int firstPos = Integer.parseInt(reader.readLine());
                    System.out.println("Second space to swap:");
                    int secondPos = Integer.parseInt(reader.readLine());
                    try {
                        game.eightOff.swapCardsBetweenSpaces(firstPos, secondPos);
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
                    }
                    break;
                case "q": {
                    System.out.println("Are you sure you wish to quit? y/n");
                    String s = reader.readLine();
                    if (s.equals("y")) {
                        break inputs;
                    }
                    break;
                }
                default:
                    System.out.println("Invalid command.");
                    continue inputs;
            }
            if (game.eightOff.isWon()) {
                System.out.println(ANSI_GREEN + "CONGRATULATIONS - YOU WIN!" + ANSI_RESET);
                break;
            }
            System.out.println("");
            System.out.println("--------------------------------------------------");
            System.out.println();
            System.out.println(eightOff);
            System.out.println(instructions());
        }
    }

    public static String instructions() {
        return "What do you wish to do? Enter command and hit enter:\n    " +
                "Move card to space: p\n    " +
                "Move card to stack: t\n    " +
                "Swap cards between spaces: w\n    " +
                "Quit game: q" +
                "\n";
    }
}
