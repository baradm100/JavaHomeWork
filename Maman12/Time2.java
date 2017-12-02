
/**
 * Represents time - hours:minutes. Values must represent a proper time
 */
public class Time2
{
    // instance variables
    private int _minFromMid;
    
    /**
     * Constructs a Time2 object. Construct a new time instance with the specified hour and minute . hour should be between 0-23, otherwise it should be set to 0. minute should be between 0-59, otherwise they should be set to 0.
     * @param   h   hour (must be between 0 and 23)
     * @param   m   minute (must be between 0 and 59)
     */
    public Time2(int h, int m)
    {
        // Initialise instance variables
        this._minFromMid = ((h >= 0 && h <= 23) ? h : 0)*60; // Adds hours as minutes
        this._minFromMid += (m >= 0 && m <= 59) ? m : 0;
    }
    
    /**
     * Constructs a Time2 object. Construct a new time instance with the specified hour and minute . hour should be between 0-23, otherwise it should be set to 0. minute should be between 0-59, otherwise they should be set to 0.
     * @param   h   hour (must be between 0 and 23)
     * @param   m   minute (must be between 0 and 59)
     */
    public Time2(Time2 other)
    {
        // Initialise instance variables
        this._minFromMid = (other.getHour() * 60) + other.getMinute();
    }
    
    /**
     * Returns the hour of the time
     * @return  The hour of the time
     */
    public int getHour()
    {
        return this._minFromMid / 60;
    }
    
    /**
     * Returns the minute of the time
     * @return The minute of the time
     */
    public int getMinute()
    {
        return this._minFromMid % 60;
    }
    
    /**
     * Changes the hour of the time. If an illegal number is received hour will be unchanged
     * @param   num The new hour (must be between 0 and 23)
     */
    public void setHour(int num)
    {
        int minBefore = this.getMinute();
        if(num >= 0 && num <= 23)
            this._minFromMid = (num * 60) + minBefore;
    }
    
    /**
     * Changes the minute of the time. If an illegal number is received minute will be unchanged
     * @param   num The new minute (must be between 0 and 59)
     */
    public void setMinute(int num)
    {
        int hourBefore = this.getHour();
        if(num >= 0 && num <= 59)
            this._minFromMid = (hourBefore * 60) + num;
    }
    
    /**
     * Return the amount of minutes since midnight
     * @return  amount of minutes since midnight
     */
    public int minFromMidnight()
    {       
        return this._minFromMid;
    }
    
    /**
     * Check if the received time is equal to this time
     * @params other   The time to be compared with this time
     * @return  True if the received time is equal to this time
     */
    public boolean equals (Time2 other)
    {
        return this._minFromMid == other.minFromMidnight();
    }
    
    /**
     * Check if this time is before a received time.
     * @param   other   The time to check if this point is before
     * @return  True if this time is before other time
     */
    public boolean before (Time2 other) 
    {
        return this.minFromMidnight() < other.minFromMidnight();
    }
    
    /**
     * Check if this time is after a received time
     * @param   other   The time to check if this point is after
     * @return  True if this time is after other time
     */
    public boolean after (Time2 other) 
    {
        return other.before(this);
    }
    
    /**
     * Calculates the difference (in minutes) between two times. Assumption: this time is after other time
     * @param   other   The time to check the difference to
     * @return  difference in minutes
     */
    public int difference(Time2 other) 
    {
        if(this.after(other))
            return this.minFromMidnight() - other.minFromMidnight();
            
        return other.minFromMidnight() - this.minFromMidnight();
    }
    
    /**
     * Return a string representation of this time (hh:mm)
     * @return  String representation of this time (hh:mm)
     */
    public String toString()
    {
        String formatedDate = "";
        // hours
        if(this.getHour() < 10)
            formatedDate += "0";
            
        formatedDate += Integer.toString(this.getHour());
        
        formatedDate += ":";
        
        // minutes
        if(this.getMinute() < 10)
            formatedDate += "0";
            
        formatedDate += Integer.toString(this.getMinute());
        
        return formatedDate;
    }
}
