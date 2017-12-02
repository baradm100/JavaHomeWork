import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Flight_Tester
 * Tests Flight class.
 *
 * @author Dror
 * @version 20441/Maman_12/2018A
 */
public class Flight_Tester
{  
   //Test Flight Constructors
     @Test  /*new Flight ("Origin", "destination", depHour, depMinute, durTimeminutes, noOfPass, price)
              getArrivalTime()/setArrivalTime()
              getDeparture(),
              getDestination(),
              getFlightDuration(),
              getIsFull()    
             */
     public void testFlight_ctor()
     {
           Flight flight1 = new Flight(new String("London"), new String("Paris"),9,40,200,100,1000);
           String flight1_str = flight1.toString();
           String flight1_str_exp = "Flight from London to Paris departs at 09:40. Flight is not full.";
         //Expcted to be equal
           assertEquals("flight1 toString():", flight1_str_exp, flight1.toString() );
           
           Flight flight2 = new Flight( flight1 );
         //Expcted to be equal:  flight1_str_exp and flight2 which is a copy of flight1
           assertEquals("flight1 toString():", flight1_str_exp, flight2.toString() );
           
         //Test the default values:
         //Try create a new object with wrong argument values for duration(-1), number of passengers( 1000 > MAX_CAPACITY), and price(-10)
           Flight flight3 = new Flight( "London", "Paris", 9, 40, -1, 1000, -10);
           String flight3_str_exp = "Flight from London to Paris departs at 09:40. Flight is full.";  
           assertEquals("flight3 toString():", flight3_str_exp, flight3.toString() );
         //Check getFlightDuration (expected to initiate 0 Minutes).
           assertEquals("flight3 getFlightDuration():", 0, flight3.getFlightDuration() );
         //Check getNoOfPassengers (expected to initiate MAX_CAPACITY Passengers to flight).
           assertEquals("flight3 getNoOfPassengers():", 250, flight3.getNoOfPassengers() );      
         //Check getProice (expected to initiate a price of 0 to a flight-Ticket).
           assertEquals("flight3 getPrice():", 0, flight3.getPrice() );
     }//End testFlight_ctor  
     
   //Test Flight.equals()
     @Test  
     public void testFlight_equals()
     {
           Flight flight1 = new Flight(new String("Aman"), new String("Thailand"),0,38,600,250,1500);
           String flight1_str = flight1.toString();
           String flight1_str_exp = "Flight from Aman to Thailand departs at 00:38. Flight is full.";
         //Expcted to be equal
           assertEquals("flight1 toString():", flight1_str_exp, flight1.toString() );
           
           Flight flight2 = new Flight( flight1 );
         //Expcted to be equal:  flight1_str_exp and flight2 which is a copy of flight1
           boolean flight1_flight2 = flight1.equals(flight2);
           boolean flight2_flight1 = flight2.equals(flight1);
           assertEquals("flight1 is expected to be equal to flight2 :", true, flight1_flight2 );
           assertEquals("flight2 is expected to be equal to flight1 :", true, flight2_flight1 );
 
     }//End testFlight_equals    
     
         
   //Test Flight get/set origin and destination  
     @Test
     public void testFlight_SetGetOrigin()
     {
           Flight flight1 = new Flight(new String("London"), new String("Paris"),9,40,200,100,1000);
           Flight flight2 = new Flight( flight1 );
         //Expcted to be equal
           assertEquals("flight1 getOrigin():", "London", flight1.getOrigin() );
 
         //Expcted to be equal:
           assertEquals("flight2 toString():", "London", flight2.getOrigin() );
         //Expcted false:
            String LONDON = "LONDON";
            assertEquals("flight1 toString():", false, LONDON.equals(flight2.getOrigin()) );
           
         //Set origin to be TLV
           flight1.setOrigin("TLV");
           assertEquals("flight1 set Origin to TLV:", "TLV", flight1.getOrigin() );  
           assertEquals("flight2 Origin is still London:", "London", flight2.getOrigin() );    
           
         //Set Destination to be Berlin
           flight1.setDestination("Berlin");
           assertEquals("flight1 set Destination to Berlin:", "Berlin", flight1.getDestination() );  
           assertEquals("flight2 Origin is still London:", "Paris", flight2.getDestination() );  
           
     }
           
 
   //Test Flight get/set Flight Duration
     @Test
     public void testFlight_flightDuration()
     {
           Flight flight1 = new Flight(new String("London"), new String("Paris"),9,40,200,100,1000);
         //Set Flight Duration to be negative --> expected not be changed
           flight1.setFlightDuration( -10 );            
         //Expcted to maintain the 200 minutes value
           assertEquals("flight1 FlightDuration after setting to -10 Minutes(maintains old value)", 200, flight1.getFlightDuration() );
 
         //Set Flight Duration to be 600
           flight1.setFlightDuration( 600 );            
         //Expcteded to be 600 Minutes.
           assertEquals("flight1 FlightDuration after setting to 600 Minutes: ", 600, flight1.getFlightDuration() );          
                   
     }
           
     
     @Test  //Test minFromMidnight(), equals(), before(), after()
     public void testDate2()
     {
            Time1 t1 = new Time1(23,59);
            int t1_from_midnight = t1.minFromMidnight();
            assertEquals("Time1(23,59)", "23:59", t1.toString());
            Time1 t2 = new Time1(24,00);
            assertEquals("Time1(24,00)", "00:00", t2.toString());
            t1.setHour(22);
            assertEquals("t1.setHour(22)", "22:59", t1.toString());
            t1.setHour(24);
            assertEquals("t1.setHour(24)", "22:59", t1.toString());
            t1.setHour(-1);
            assertEquals("t1.setHour(-1)", "22:59", t1.toString());
 
     }
     
     
}