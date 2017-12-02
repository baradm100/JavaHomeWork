
/**
 * Represents a flight. A Flight object is represented by the flight's origin,destination,departure time, flight duration, no of passengers,if it is full and the price.
 */
public class Flight
{
    // instance variables
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;
    private static final int MAX_CAPACITY = 250;

    /**
     * Constructor for a Flight object.
     * If the number of passengers exceeds the maximum capacity the number of passengers will be set to the maxmum capacity If the number of passengers is negative the number of passengers will be set to zero.
     * If the flight duration is negative the flight duration will be set to zero. If the price is negative the price will be set to zero.
     * @param   origin      The city the flight leaves from
     * @param   destination The city the flight lands at
     * @param   departureHour   The departure hour (should be between 0-23)
     * @param   departureMin    The departure minute (should be between 0-59)
     * @param   flightDuration  The duration time in minutes(should not be negative).
     * @param   noOfPassengers  The number of passengers (should be between 0-maximum capacity)
     * @param   price   The price (should not be negative).
     */
    public Flight(String origin,String destination, int departureHour, int departureMin, int flightDuration, int noOfPassengers, int price)
    {
        this._origin = origin;
        this._destination = destination;
        this._departure = new Time1(departureHour, departureMin);
        
        // set the flight duration, min price is 0
        this._flightDuration = (flightDuration > 0) ? flightDuration : 0;
        
        // set the number of passengers, max is +MAX_CAPACITY+, min is 0
        if(noOfPassengers > MAX_CAPACITY)
            this._noOfPassengers = MAX_CAPACITY;
        else if(noOfPassengers < 0)
            this._noOfPassengers = 0;
        else
            this._noOfPassengers = noOfPassengers;
        
        // set the price, min price is 0
        this._price = (price > 0) ? price : 0;
        
        // set if the flight is full
        this._isFull = this._noOfPassengers == MAX_CAPACITY;
    }
    
    /**
     * Copy constructor for a Flight object. Construct a Flight object with the same attributes as another Flight object.
     * @param   other   The Flight object from which to construct the new Flight
     */
    public Flight(Flight other)
    {
        this._origin = other.getOrigin();
        this._destination = other.getDestination();
        this._departure = other.getDeparture();
        this._flightDuration = other.getFlightDuration();
        this._noOfPassengers = other.getNoOfPassengers();
        this._price = other.getPrice();
        this._isFull = other.getIsFull();
    }
    
    /**
     * Returns the flight origin.
     * @return  The flight origin.
     */
    public String getOrigin()
    {
        return this._origin;
    }
    
    /**
     * Returns the flight destination.
     * @return  The flight destination.
     */
    public String getDestination()
    {
        return this._destination;
    }
    
     /**
     * Returns the flight departure time.
     * @return  The flight departure time.
     */
    public Time1 getDeparture()
    {
        return new Time1(this._departure);
    }
    
    /**
     * Returns the flight duration time in minutes.
     * @return  The flight duration time in minutes.
     */
    public int getFlightDuration()
    {
        return this._flightDuration;
    }
    
    /**
     * Returns the number of passengers on the flight.
     * @return  The number of passengers on the flight.
     */
    public int getNoOfPassengers()
    {
        return this._noOfPassengers;
    }
    
    /**
     * Returns whether the flight is full or not.
     * @return  True if the flight is full.
     */
    public boolean getIsFull()
    {
        return this._isFull;
    }
    
    /**
     * Returns the price of the flight.
     * @return  The price of the flight.
     */
    public int getPrice()
    {
        return this._price;
    }
    
    /**
     * Changes the flight's origin.
     * @param  origin   The flight's new origin.
     */
    public void setOrigin(String origin)
    {
        this._origin = origin;
    }
    
    /**
     * Changes the flight's destination.
     * @param  destination   The flight's new destination.
     */
    public void setDestination(String destination)
    {
        this._destination = destination;
    }
    
    /**
     * Changes the flight's departure time.
     * @param  departure   The flight's new departure time.
     */
    public void setDeparture(Time1 departure)
    {
        this._departure = departure;
    }
    
    /**
     * Changes the flight's duration time.
     * @param  flightDuration   The flight's new duration time.
     */
    public void setFlightDuration(int flightDuration)
    {
        if(flightDuration > 0)
            this._flightDuration = flightDuration;
    }
    
    /**
     * Changes the flight's number of passengers.
     * @param  noOfPassengers   The flight's new number of passengerse.
     */
    public void setNoOfPassengers(int noOfPassengers)
    {
        this._noOfPassengers = noOfPassengers;
    }
    
    /**
     * Changes the flight's price.
     * @param  price   The flight's new price.
     */
    public void setPrice(int price)
    {
        this._price = price;
    }
    
    /**
     * Check if the received flight is equal to this flight. Flights are considered equal if the origin, destination and departure times are the same.
     * @param   other   The flight to be compared with this flight
     * @return  True if the received flight is equal to this flight
     */
    public boolean equals(Flight other)
    {
        return this.getDestination().equals(other.getDestination()) && this.getOrigin().equals(other.getOrigin()) && this.getDeparture().equals(this.getDeparture());
    }
    
    /**
     * Returns the arrival time of the flight
     * @return  The arrival time of this flight
     */
    public Time1 getArrivalTime()
    {
        int minFromMidArrival = this._departure.minFromMidnight() + this._flightDuration;
        Time1 arrivalTime = new Time1(minFromMidArrival / 60, minFromMidArrival % 60);
        
        return arrivalTime;
    }
    
    /**
     * Add passengers to this flight.
     * If the number of passengers exceeds he maximum capacity, no passengers are added and alse is returned.
     * If the flight becomes full, the boolean attribute describing whether the flight if full becomes true.
     * Assume parameter is positive
     * @return  True if the passengers were added to the flight
     */
    public boolean addPassengers(int num)
    {
        if(this._noOfPassengers + num > MAX_CAPACITY)
            return false;
        
        this._noOfPassengers += num; // updating noOfPassengers 
        this._isFull = this._noOfPassengers == MAX_CAPACITY; // updating isFull
        
        return true;
    }
    
    /**
     * Check if this flight is cheaper than another flight
     * @param    another The flight whose price is to be compared with this flight's price
     * @return  True if this flight is cheaper than the received flight
     */
    public boolean isCheaper(Flight another)
    {
        return this._price < another.getPrice();
    }
    
    /**
     * Calculate the total price of the flight
     * @return  The total price of the flight
     */
    public int totalPrice()
    {
        return this._price * this._noOfPassengers;
    }
    
    /**
     * Check if this flight lands before another flight.
     * Note - the flights may land on different days, the method checks which flight lands first
     * @param   other   The flight whose arrival time to be compared with this flight's arrival time
     * @return  True if this flight arrives before the received flight
     */
    public boolean landsEarlier(Flight other)
    {
        int thisMinFromMid = this.getArrivalTime().minFromMidnight();
        int otherMinFromMid = other.getArrivalTime().minFromMidnight();
        
         // adds another day
        if(thisMinFromMid <= this._departure.minFromMidnight())
            thisMinFromMid += 24*60;
        
        if(otherMinFromMid <= other.getDeparture().minFromMidnight())
            otherMinFromMid += 24*60;
            
        return this.getArrivalTime().minFromMidnight() < other.getArrivalTime().minFromMidnight();
    }
    
    /**
     * Return a string representation of this flight (for example: "Flight from London to Paris departs at 09:24.Flight is full.")
     * @return  String representation of this flight (for example: "Flight from London to Paris departs at 09:24.Flight is full.")
     */
    public String toString()
    {
        String msg = "Flight from " + this._origin + " to " + this._destination + " departs at " + this._departure + ".";
        
        if(this._isFull)
            msg += " Flight is full.";
        else
            msg += " Flight is not full.";
        
        return msg;
    }
}

