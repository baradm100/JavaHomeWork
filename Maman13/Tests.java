

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Tests.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Tests
{
    /**
     * Default constructor for test class Tests
     */
    public Tests()
    {
    }

    
    public Airport getFullAirport() {
        Airport a1 = new Airport("Tel-Aviv");
        Flight f1;
        for(int i=0; i < 200; i++) {
            f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
            a1.addFlight(f1);
        }
        return a1;
    }
            //         assertTrue(flight2.landsEarlier(flight1));
        //assertEquals("03:29", flight1.getArrivalTime().toString());
    
    @Test
    public void test_addFlight(){
        Airport a1 = new Airport("Tel-Aviv");
            
        //AddFlight
        Flight f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        assertTrue(a1.addFlight(f1));
        f1 = new Flight("London","Tel-Aviv",12,0,210,250,100);
        assertTrue(a1.addFlight(f1));
        Flight f2 = null;
        assertTrue(!a1.addFlight(f2));    
        f1 = new Flight("London","Tel Aviv",12,0,210,250,100);
        assertTrue(!a1.addFlight(f1));
        f1 = new Flight("London","Tel-Aviv",12,0,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("London","Tel-Aviv",12,0,210,250,100);
        // Max Flights reached
        Airport full = getFullAirport();
        assertTrue(!full.addFlight(f1));
        
        a1 = new Airport("Tel-Aviv");
        for(int i=0; i < 200; i++) {
            f1 = new Flight("Paris","London",12,0,210,250,100);
            assertTrue(!a1.addFlight(f1));
        }
        f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        assertTrue(a1.addFlight(f1));
        assertEquals("The flights for airport Tel-Aviv today are:\n"+
                     "Flight from Tel-Aviv to London departs at 12:00. Flight is full.", a1.toString());
        
    }
    
    @Test
    public void test_removeFlight(){
        Flight f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        Flight f2 = new Flight("New York","Tel-Aviv",10,50,210,250,100);
        Flight f3 = new Flight("Tel-Aviv","Paris",11,35,210,210,100);
        Flight f4 = new Flight("Tel-Abiv","Paris",11,35,210,210,100);
        Airport a1 = new Airport("Tel-Aviv");
        Airport a2 = new Airport("Tel-Aviv");
        assertTrue(!a1.removeFlight(f4));
        a1.addFlight(f1);
        a1.addFlight(f2);
        a1.addFlight(f3);
        a2.addFlight(f1);
        a2.addFlight(f3);
        assertTrue(a1.removeFlight(f2));
        assertTrue(!a1.removeFlight(f4));
        assertTrue(!a1.removeFlight(null));
        assertEquals(a1.toString(), a2.toString());
        Flight fullFlight = new Flight("Tel-Aviv","London",12,0,210,250,100);
        Airport fullAirport = getFullAirport();
        assertTrue(fullAirport.removeFlight(fullFlight)); 
        assertTrue(fullAirport.addFlight(fullFlight));
        assertTrue(!fullAirport.addFlight(fullFlight));
    }
    
    @Test
    public void test_firstFlightFromDestination (){
        Flight f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        Flight f2 = new Flight("Tel-Aviv","London",11,0,210,250,100);
        Flight f3 = new Flight("London","Tel-Aviv",10,0,210,250,100);
        Airport a1 = new Airport("Tel-Aviv");
        a1.addFlight(f1);
        a1.addFlight(f2);
        a1.addFlight(f3);
        Time1 out = a1.firstFlightFromDestination("Tel-Aviv");
        assertEquals(out.toString(), "10:00");
        out = a1.firstFlightFromDestination("London");
        assertEquals(out.toString(), "11:00");
        out = a1.firstFlightFromDestination("Tel-Abiv");
        assertTrue(out == null);
        out = a1.firstFlightFromDestination(null);
        assertTrue(out == null);
        
    }
    
    @Test
    public void test_toString(){
        Airport a1 = new Airport("Tel-Aviv");
        Flight f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("New York","Tel-Aviv",10,50,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("Tel-Aviv","Paris",11,35,210,210,100);
        a1.addFlight(f1);
        assertEquals("The flights for airport Tel-Aviv today are:\n"+
        "Flight from Tel-Aviv to London departs at 12:00. Flight is full.\n"+
        "Flight from New York to Tel-Aviv departs at 10:50. Flight is full.\n"+
        "Flight from Tel-Aviv to Paris departs at 11:35. Flight is not full.", a1.toString());
    }
    
    @Test
    public void test_howManyFullFlights(){
        Airport a1 = new Airport("Tel-Aviv");
        assertEquals(a1.howManyFullFlights(), 0);
        Flight f1 = new Flight("Tel-Aviv","London",12,0,60,250,100);
        a1.addFlight(f1);
        assertEquals(a1.howManyFullFlights(), 1);
        f1 = new Flight("Tel-Aviv","London",12,0,60,200,100);
        a1.addFlight(f1);
        assertEquals(a1.howManyFullFlights(), 1);
        f1 = new Flight("Tel-Aviv","London",12,0,60,250,100);
        a1.addFlight(f1);
        assertEquals(a1.howManyFullFlights(), 2);
    }
    
    @Test
    public void test_howManyFlightsBetween (){
        Airport a1 = new Airport("Tel-Aviv");
        Flight f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("New York","Tel-Aviv",10,50,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("Tel-Aviv","London",10,50,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("London", "Tel-Aviv",10,50,210,250,100);
        a1.addFlight(f1);
        assertEquals(a1.howManyFlightsBetween("London", "Tel-Aviv"), 3);
        assertEquals(a1.howManyFlightsBetween(null, "Tel-Aviv"), 0);
        assertEquals(a1.howManyFlightsBetween("Tel-Aviv", "New York"), 1);
        assertEquals(a1.howManyFlightsBetween("Tel-Aviv", "NewYork"), 0);
    }
    
    @Test
    public void test_mostPopularDestination(){
        Airport a1 = new Airport("Tel-Aviv");
        assertTrue(a1.mostPopularDestination() == null);
        Flight f1 = new Flight("Tel-Aviv","New York",12,0,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("New York","Tel-Aviv",10,50,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("Tel-Aviv","London",10,50,210,250,100);
        a1.addFlight(f1);
        a1.addFlight(f1);
        f1 = new Flight("London", "Tel-Aviv",10,50,210,250,100);
        a1.addFlight(f1);
        assertTrue(a1.mostPopularDestination() == "London");
        a1 = new Airport("Tel-Aviv");
        f1 = new Flight("Tel-Aviv","New York",12,0,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        a1.addFlight(f1);
        f1 = new Flight("Tel-Aviv","Paris",12,0,210,250,100);
        a1.addFlight(f1);
        String s = a1.mostPopularDestination();
        assertTrue(s == "New York" || s == "London" || s == "Paris");
        a1.addFlight(f1);
        assertTrue(a1.mostPopularDestination() == "Paris");
    }
    
    @Test
    public void test_mostExpensiveTicket(){
        Airport a1 = new Airport("Tel-Aviv");
        assertTrue(a1.mostExpensiveTicket() == null);
        Flight f1 = new Flight("Tel-Aviv","New York",12,0,210,250,100);
        a1.addFlight(f1);
        Flight f2 = new Flight("Tel-Aviv","Paris",12,0,210,250,120);
        a1.addFlight(f2);
        Flight f3 = new Flight("Tel-Aviv","Paris",12,0,210,250,150);
        a1.addFlight(f3);
        assertTrue(a1.mostExpensiveTicket().equals(f3));
        a1.removeFlight(f3);
        assertTrue(a1.mostExpensiveTicket().equals(f2));
        a1.removeFlight(f2);
        assertTrue(a1.mostExpensiveTicket().equals(f1));
    }
    
    @Test
    public void test_longestFlight(){
        Airport a1 = new Airport("Tel-Aviv");
        assertTrue(a1.longestFlight() == null);
        Flight f1 = new Flight("Tel-Aviv","New York",12,0,210,250,100);
        a1.addFlight(f1);
        Flight f2 = new Flight("Tel-Aviv","Paris",12,0,220,250,100);
        a1.addFlight(f2);
        Flight f3 = new Flight("Tel-Aviv","Paris",12,0,230,250,100);
        a1.addFlight(f3);
        assertTrue(a1.longestFlight().equals(f3));
        a1.removeFlight(f3);
        assertTrue(a1.longestFlight().equals(f2));
        a1.removeFlight(f2);
        assertTrue(a1.longestFlight().equals(f1));
    }
}
