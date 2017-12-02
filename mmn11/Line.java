import java.util.Scanner;

public class Line
{
    public static void main(String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("Please enter 4 integers ");
        
        // Input for point A
        System.out.println ("Please enter x1:");
        int x1 = scan.nextInt();
        System.out.println ("Please enter y1:");
        int y1 = scan.nextInt();
        
        // Input for point B
        System.out.println ("Please enter x2:");
        int x2 = scan.nextInt();
        System.out.println ("Please enter y2:");
        int y2 = scan.nextInt();
        
        // Calculate the line length:
        double lineLength = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
        System.out.println("The length of the line between the points (" + x1 + ", " + y1 + ") and (" +x2 + ", " + y2 + ") is " + lineLength);
    }
}
