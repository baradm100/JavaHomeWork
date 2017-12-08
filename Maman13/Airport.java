/**
 * Represents a airport. A Airport object is represented by the airport's flights schedule, no of flights and airport name
 */
public class Airport
{
    // instance variables
    private Flight [] _flightsSchedule;
    private int _noOfFlights;
    private String _airport;
    private static final int FLIGHTS_MAX = 200;

    /**
     * Constructor for a Flight object.
     * Initialise noOfFlights to 0 and flightsSchedule as empty array of Flight
     * @param   airport      Airport name
     */
    public Airport(String airport)
    {
        // initialise instance variables
        this._airport = airport;
        this._flightsSchedule = new Flight[FLIGHTS_MAX];
        this._noOfFlights = 0;
    }
    
    /**
     * Returns if the flights was added to the airport's flights schedule
     * Flight is added if the Flight isn't null, the Flight isn't from or too the airport and if the flightsSchedule isn't full
     * @param   addFlight      Flight to add
     * @return  if the flight was added.
     */
    public boolean addFlight(Flight addFlight)
    {   if(addFlight == null)
            return false;
       
        if(!addFlight.getDestination().equals(this._airport) && !addFlight.getOrigin().equals(this._airport))
            return false; // The flight isn't origin or detination of the current airport, so dosn't add
         
        if(this._noOfFlights >= FLIGHTS_MAX)
            return false; // The flightsSchedule is full
        
        this._flightsSchedule[this._noOfFlights] = new Flight(addFlight);
        this._noOfFlights++;
        return true;
    }
    
    /**
     * Returns if the flights was removed to the airport's flights schedule
     * Flight is removed if the Flight isn't null and if the flight was found.
     * After removel, moving the remaning flights to to "fill" the hole of the removed flight
     * @param   removeFlight      Flight to remove
     * @return  if the flight was removed.
     */
    public boolean removeFlight(Flight removeFlight)
    {
        if(removeFlight == null)
            return false;
            
        boolean flightWasRemoved = false;
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(this._flightsSchedule[i].toString().equals(removeFlight.toString()) && !flightWasRemoved)
                flightWasRemoved = true; // Marking as found
            
            
            if(flightWasRemoved)
            {
                if(i + 1 >= FLIGHTS_MAX)
                    this._flightsSchedule[i] = null;
                else
                    this._flightsSchedule[i] = this._flightsSchedule[i + 1]; // Changing to current cell in the array to the next one
            }
        }
        
        if(flightWasRemoved)
            this._noOfFlights--;
        
        return flightWasRemoved;
    }
    
    /**
     * Returns the earlier flight from  destination
     * @param   place      Destination of the flight
     * @return  Departure of the first flight, can be null if the flight wasn't found
     */
    public Time1 firstFlightFromDestination(String place)
    {
        int minMinutes = Integer.MAX_VALUE; // set minMinutes as the max value of int
        Time1 minDeparture = null;
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(this._flightsSchedule[i].getDestination().equals(place))
            {
                Time1 tempTime = this._flightsSchedule[i].getDeparture();
                int temMin = tempTime.minFromMidnight();
                if(temMin <= minMinutes)
                {
                    // found earlier flight
                    minMinutes = temMin;
                    minDeparture = tempTime;
                }
            }
        }
        
        return minDeparture;
    }
    
    /**
     * Returns the earlier flight from  destination
     * @param   place      Destination of the flight
     * @return  Departure of the first flight, can be null if the flight wasn't found
     */
    public int howManyFullFlights()
    {
        int fullFlightsCount = 0;
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(this._flightsSchedule[i].getIsFull())
                fullFlightsCount++;
        }
        
        return fullFlightsCount;
    }
    
    /**
     * Returns how many flights were between 2 cities, no matter the origin and destination
     * @param   city1      City 1
     * @param   city2      City 2
     * @return  How many flights between
     */
    public int howManyFlightsBetween(String city1, String city2)
    {
        int flightsCount = 0;
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            boolean fromCity1 = this._flightsSchedule[i].getOrigin().equals(city1) && this._flightsSchedule[i].getDestination().equals(city2);
            boolean fromCity2 =this._flightsSchedule[i].getOrigin().equals(city2) && this._flightsSchedule[i].getDestination().equals(city1);
            if(fromCity1 || fromCity2)
                flightsCount++;
        }
        
        return flightsCount;
    }
    
    /**
     * Returns how many flights were from destination
     * @param   city      City
     * @return  How many flights between
     */
    private int countFlightsDestination(String city)
    {
        int count = 0;
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(this._flightsSchedule[i].getDestination().equals(city))
                count ++;
        }
        
        return count;
    }
    
    /**
     * Returns the most popular destination
     * @return  the most popular destination
     */
    public String mostPopularDestination()
    {
        int maxFligts = 0;
        String poupular = null;
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            int tempCount = countFlightsDestination(this._flightsSchedule[i].getDestination());
            if(maxFligts < tempCount)
            {
                maxFligts = tempCount;
                poupular = this._flightsSchedule[i].getDestination();
            }
        }
        
        return poupular;
    }
    
    /**
     * Returns the most expensive ticket
     * @return  the most expensive ticket
     */
    public Flight mostExpensiveTicket()
    {
        int maxPrice = 0;
        int maxPriceI = 0;
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(maxPrice < this._flightsSchedule[i].getPrice())
            {
                maxPrice = this._flightsSchedule[i].getPrice();
                maxPriceI = i;
            }
        }
        
        
        return this._flightsSchedule[maxPriceI];
    }
    
    /**
     * Returns the longest flight
     * @return  the longest flight
     */
    public Flight longestFlight()
    {
        int maxDuration = 0;
        int maxDurationI = 0;
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(maxDuration <this._flightsSchedule[i].getFlightDuration())
            {
                maxDuration = this._flightsSchedule[i].getFlightDuration();
                maxDurationI = i;
            }
        } 
        
        return this._flightsSchedule[maxDurationI];
    }
    
    /**
     * Returns airport data in string form
     * @return  airport data in string form
     */
    public String toString()
    {
        String results = "The flights for airport " + this._airport +" today are:";
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            results += "\n";
            results += this._flightsSchedule[i].toString();
        } 
        
        return results;
    }

}
