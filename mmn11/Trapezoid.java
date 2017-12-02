import java.util.Scanner;

public class Trapezoid
{
    public static void main(String [] args)
    {
        Scanner scan = new Scanner (System.in);
        
        // Gets the data for point A and it's base
        System.out.println ("Please enter position for point A (2 integers):");
        System.out.println ("Please enter x for A:");
        int xA = scan.nextInt();
        System.out.println ("Please enter y for A:");
        int yA = scan.nextInt();
        System.out.println ("Please enter the length of the base (AB) (integer):");
        int baseALenght = scan.nextInt();
        int xB = xA + baseALenght;
        
        // Gets the data for point D and it's base
        System.out.println ("Please enter position for point D (2 integers):");
        System.out.println ("Please enter x for D:");
        int xD = scan.nextInt();
        System.out.println ("Please enter y for D:");
        int yD = scan.nextInt();
        System.out.println ("Please enter the length of the base (CD) (integer):");
        int baseDLenght = scan.nextInt();
        int xC = xD + baseALenght;

        // Calculationg the perimeter
        double perimeter = baseDLenght + baseALenght; // Summing the distance from A to B and C to D
        perimeter +=  Math.sqrt(Math.pow(xA - xD, 2) + Math.pow(yA - yD, 2)); // Calc the distance from A to D
        perimeter += Math.sqrt(Math.pow(xB - xC, 2) + Math.pow(yA - yD, 2)); // Calc the distance from B to C - A & B and C & D share the same y

        // Calculationg the area
        // Getting the trapezoid height
        double height;
        if(yA >= yD)
        {
            height = yA - yD;
        }
        else
        {
            height = yD - yA;
        }
        double area = ((baseDLenght + baseALenght) * height) / 2;
        
        System.out.println("The trapezoid area is: " + area);
        System.out.println("The trapezoid perimeter is: " + perimeter);
    }
}
