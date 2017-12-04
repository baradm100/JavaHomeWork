// TODO Comment
public class Airport
{
    // TODO Comment
    private Flight [] _flightsSchedule;
    private int _noOfFlights;
    private String _airport;
    private static final int FLIGHTS_MAX = 200;

    // TODO Comment
    public Airport(String airport)
    {
        // initialise instance variables
        this._airport = airport;
        this._flightsSchedule = new Flight[FLIGHTS_MAX];
        this._noOfFlights = 0;
    }
    
    // TODO Comment
    public boolean addFlight(Flight addFlight)
    {
        if(addFlight.getDestination() != this._airport && addFlight.getOrigin() != this._airport)
            return false; // The flight isn't origin or detination of the current airport, so dosn't add
         
        if(this._noOfFlights >= FLIGHTS_MAX)
            return false; // The flightsSchedule is full
        
        this._flightsSchedule[this._noOfFlights] = new Flight(addFlight);
        this._noOfFlights++;
        return true;
    }
    
    // TODO Comment
    public boolean removeFlight(Flight removeFlight)
    {
        boolean flightWasRemoved = false;
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(this._flightsSchedule[i].toString().equals(removeFlight.toString()))
                flightWasRemoved = true; // Marking as found
            
            if(flightWasRemoved)
                this._flightsSchedule[i] = this._flightsSchedule[i + 1]; // Changing to current cell in the array to the next one
        }
        
        if(flightWasRemoved)
            this._noOfFlights--;
        
        return flightWasRemoved;
    }
    
    // TODO Comment
    public Time1 firstFlightFromDestination(String place)
    {
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(this._flightsSchedule[i].getDestination().equals(place))
                return this._flightsSchedule[i].getDeparture();
        }
        // Returns null if the flight wasn't found
        return null;
    }
    
    // TODO Comment
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
    
    // TODO Comment
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
    
    // TODO Comment
    private int countFlightsFrom(String city)
    {
        int count = 0;
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(this._flightsSchedule[i].getDestination().equals(city))
                count ++;
        }
        
        return count;
    }
    
    // TODO Comment
    public String mostPopularDestination()
    {
        int maxFligts = 0;
        String poupular = null;
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            if(maxFligts < countFlightsFrom(this._flightsSchedule[i].getDestination()))
            {
                maxFligts = countFlightsFrom(this._flightsSchedule[i].getDestination());
                poupular = this._flightsSchedule[i].getDestination();
            }
        }
        
        return poupular;
    }
    
    // TODO Comment
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
    
    // TODO Comment
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
    
    // TODO Comment
    public String toString()
    {
        String results = "The flights for airport " + this._airport +" today are:\n";
        
        for(int i = 0; i <this._noOfFlights; i++)
        {
            results += this._flightsSchedule[i].toString() + "\n";
        } 
        
        return results;
    }

}
