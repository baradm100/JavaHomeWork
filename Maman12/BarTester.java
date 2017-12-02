
/**
 * Write a description of class BarTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BarTester
{
    public static void main(String[] args)
    {
        Time2 t1 = new Time2(7, 30);
        Time2 t2 = new Time2(t1);
        System.out.println(t1.toString());
        System.out.println(t2.equals(t1));
    }
}
