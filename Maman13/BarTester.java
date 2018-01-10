
public class BarTester
{
    public static void main(String[] args)
    {
        Airport air = new Airport("Tel Aviv");
        Flight f = new Flight("Tel Aviv","London", 7, 1, 100, 100, 100);
        Flight f1 = new Flight("Tel Aviv","London", 7, 0, 100, 100, 100);
        air.addFlight(f);
        air.addFlight(f1);
        System.out.println(air.toString());
        air.removeFlight(f1);
        System.out.println(air.toString());
    }
    
    public int myF(char x, int y)
    {
        return 1;
    }
    
    public double myF(char y, int x)
    {
        return 1;
    }
}
