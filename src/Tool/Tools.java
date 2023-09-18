package Tool;

public class Tools {
    public static int parsInt(String input){
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
