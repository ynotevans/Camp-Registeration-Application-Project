package CAMs_App.service;
/**
 * The class {@link ColouredTextPrinter} prints color text in the console using ANSI escape codes.
 * The class includes methods for printing text of various colors and a rainbow effect for each color
 * 
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
 */
public class ColouredTextPrinter {
        // ANSI escape codes for colors
        static final String RESET = "\u001B[0m";
        static final String RED = "\u001B[31m";
        static final String GREEN = "\u001B[32m";
        static final String YELLOW = "\u001B[33m";
         static final String BLUE = "\u001B[34m";
         static final String PURPLE = "\u001B[35m";
         static final String CYAN = "\u001B[36m";
         static final String WHITE = "\u001B[37m";
         static final String[] RAINBOW_COLORS = {
            "\u001B[31m", // Red
            "\u001B[33m", // Yellow
            "\u001B[32m", // Green
            "\u001B[36m", // Cyan
            "\u001B[34m", // Blue
            "\u001B[35m"  // Purple
    };
        /**
         * Prints the text in red
         * @param text : The text content to be printed
         */
        public static void printRed(String text) {
            System.out.println(RED + text + RESET);
        }
        /**
         * Prints the text in green
         * @param text : The text content to be printed
         */
        public static void printGreen(String text) {
            System.out.println(GREEN + text + RESET);
        }
        /**
         * Prints the text in yellow
         * @param text : The text content to be printed
         */
        public static void printYellow(String text) {
            System.out.println(YELLOW + text + RESET);
        }
        /**
         * Prints the text in blue
         * @param text : The text content to be printed
         */
        public static void printBlue(String text) {
            System.out.println(BLUE + text + RESET);
        }
        /**
         * Prints the text in purple
         * @param text : The text content to be printed
         */
        public static void printPurple(String text) {
            System.out.println(PURPLE + text + RESET);
        }
        /**
         * Prints the text in cyan
         * @param text : The text content to be printed
         */
        public static void printCyan(String text) {
            System.out.println(CYAN + text + RESET);
        }
        /**
         * Prints the text in white
         * @param text : The text content to be printed
         */
        public static void printWhite(String text) {
            System.out.println(WHITE + text + RESET);
        }

       /**
         * Prints the text in rainbow effect, different color for each character
         * @param text : The text content to be printed
         */
        public static void printRainbowText(String text) {
            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.charAt(i);
                String rainbowColor = RAINBOW_COLORS[i % RAINBOW_COLORS.length];
                System.out.print(rainbowColor + currentChar);
            }
            System.out.println(RESET);
        }
        
}

