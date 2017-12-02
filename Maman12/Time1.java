/**
 * Represents time - hours:minutes. Coordinates cannot be negative
 */ 
public class Time1
{
    // instance variables
    private int _hour;
    private int _minute;

    /**
     * Constructs a Time1 object. Construct a new time instance with the specified hour and minute . hour should be between 0-23, otherwise it should be set to 0. minute should be between 0-59, otherwise it should be set to 0..
     * @param   h   hour (must be between 0 and 23)
     * @param   m   minute (must be between 0 and 59)
     */
    public Time1(int h, int m)
    {
        // Initialise instance variables
        this._hour = (h >= 0 && h <= 23) ? h : 0;
        this._minute = (m >= 0 && m <= 59) ? m : 0;
    }
    
    /**
     * Copy constructor for Time1. Construct a time with the same instance variables as another time.
     * @param   t   The time object from which to construct the new time
     */
    public Time1(Time1 t)
    {
        // Initialise instance variables
        this._hour = t.getHour();
        this._minute = t.getMinute();
    }
    
    /**
     * Returns the hour of the time
     * @return  The hour of the time
     */
    public int getHour()
    {
        return this._hour;
    }
    
    /**
     * Returns the minute of the time
     * @return The minute of the time
     */
    public int getMinute()
    {
        return this._minute;
    }
    
    /**
     * Changes the hour of the time. If an illegal number is received hour will be unchanged
     * @param   num The new hour (must be between 0 and 23)
     */
    public void setHour(int num)
    {
        if(num >= 0 && num <= 23)
            this._hour = num;
    }
    
    /**
     * Changes the minute of the time. If an illegal number is received minute will be unchanged
     * @param   num The new minute (must be between 0 and 59)
     */
    public void setMinute(int num)
    {
        if(num >= 0 && num <= 59)
            this._minute = num;
    }
    
    /**
     * Return a string representation of this time (hh:mm)
     * @return  String representation of this time (hh:mm)
     */
    public String toString()
    {
        String formatedDate = "";
        // hours
        if(this._hour < 10)
            formatedDate += "0";
            
        formatedDate += Integer.toString(_hour);
        
        formatedDate += ":";
        
        // minutes
        if(this._minute < 10)
            formatedDate += "0";
            
        formatedDate += Integer.toString(_minute);
        
        return formatedDate;
    }
    
    /**
     * Return the amount of minutes since midnight
     * @return  amount of minutes since midnight
     */
    public int minFromMidnight()
    {
        int minSum = this._minute;
        
        minSum += this._hour * 60; // 60 min in a hour
        
        return minSum;
    }
    
    /**
     * Check if the received time is equal to this time
     * @params other   The time to be compared with this time
     * @return  True if the received time is equal to this time
     */
    public boolean equals (Time1 other)
    {
        return this.minFromMidnight() == other.minFromMidnight();
    }
    
    /**
     * Check if this time is before a received time.
     * @param   other   The time to check if this point is before
     * @return  True if this time is before other time
     */
    public boolean before (Time1 other) 
    {
        return this.minFromMidnight() < other.minFromMidnight();
    }
    
    /**
     * Check if this time is after a received time
     * @param   other   The time to check if this point is after
     * @return  True if this time is after other time
     */
    public boolean after (Time1 other) 
    {
        return other.before(this);
    }
    
    /**
     * Calculates the difference (in minutes) between two times. Assumption: this time is after other time
     * @param   other   The time to check the difference to
     * @return  difference in minutes
     */
    public int difference(Time1 other) 
    {
        if(this.after(other))
            return this.minFromMidnight() - other.minFromMidnight();
            
        return other.minFromMidnight() - this.minFromMidnight();
    }
}
