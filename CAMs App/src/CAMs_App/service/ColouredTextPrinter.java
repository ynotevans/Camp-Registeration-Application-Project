package CAMs_App.service;

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
       
        public static void printRed(String text) {
            System.out.println(RED + text + RESET);
        }
    
        public static void printGreen(String text) {
            System.out.println(GREEN + text + RESET);
        }
    
        public static void printYellow(String text) {
            System.out.println(YELLOW + text + RESET);
        }
    
        public static void printBlue(String text) {
            System.out.println(BLUE + text + RESET);
        }
    
        public static void printPurple(String text) {
            System.out.println(PURPLE + text + RESET);
        }
    
        public static void printCyan(String text) {
            System.out.println(CYAN + text + RESET);
        }
    
        public static void printWhite(String text) {
            System.out.println(WHITE + text + RESET);
        }

   
        public static void printRainbowText(String text) {
            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.charAt(i);
                String rainbowColor = RAINBOW_COLORS[i % RAINBOW_COLORS.length];
                System.out.print(rainbowColor + currentChar);
            }
            System.out.println(RESET);
        }
        
}

