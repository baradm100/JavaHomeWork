public class Ex14
{   
    /**
     * Returns the number of occurrence of x in a (sorted array)
     * 
     * Time complexity: O(log(n)):
     *  binarySearch = log(n)
     *  2binarySearch = 2log(n)
     *  O(2log(n)) = O(log(n))
     * Space complexity: O(1)
     * 
     * @param   a   Array of numbers (sorted)
     * @param   x   The number we need to find
     * @return  Number of occurrence of x in a
     */
    public static int count (int[] a, int x)
    {
        int startIndex = binarySearch(a, x, 0, true);
        if (startIndex == -1) // Isn't in the array
            return 0;
            
        int endIndex = binarySearch(a, x, startIndex, false);
        
        // Return the number of occurance of x in a by calcilation the diffrence between the first and last index
        return endIndex - startIndex + 1; // Adds 1 beacuse array starts at 0 (unlike in lua...)
    }
    
    /**
     * A Binary search or a sorted array
     * Time complexity: O(log(n))
     * Space complexity: O(1)
     * 
     * @param   a   Array of numbers (sorted)
     * @param   x   The number we need to find
     * @param   bottom  The bottom index to start with
     * @param   goUp    What type of search is needed, for the first or the last occurrence
     * @return  The index of the first/last occurrence, -1 if not found
     */
    private static int binarySearch(int[] a, int x, int bottom, boolean goUp)
    {
        int middle, top = a.length - 1;

        while (bottom <= top) {
            middle = bottom + (top - bottom) / 2;
            
            if(goUp)
            {
                // Search for the *first* time the number x is found
                if (x < a[middle] || (x==a[middle] && (middle > 0 && x == a[middle - 1]))) // Checks if x is smaller then current cell or not the first occurance
                    top = middle - 1; // Moving the top index, x is smaller
                else if (x > a[middle])
                    bottom = middle + 1; // Moving the lower index, x is bigger
                else // I found it master!!
                    return middle;
            }
            else
            {
                // Search for the *last* time the number x is found
                if (x > a[middle] || (x == a[middle] && (middle < a.length - 1 && x == a[middle + 1]))) // Checks if x is bigger then current cell or not the first occurance
                    bottom = middle + 1; // Moving the lower index, x is bigger
                else if (x < a[middle])
                    top = middle - 1; // Moving the top index, x is smaller
                else // I found it master!!
                    return middle;
            }           
        }
        // Wasn'f found :(
        return -1;
    }
    
    /**
     * Returns the lest number of swaps that need to be made to make the string alternating between 0 and 1
     * Time complexity: O(n):
     *  for loop = n/2
     *  O(n/2) = O(n)
     * Space complexity: O(1)
     * 
     * @param   s   The string we checking (size is always 2n)
     * @return  The lest number of swaps that need to be made to make the string alternating between 0 and 1
     */
    public static int alternating (String s)
    {
        // Declearing 2 counts
        int option1Count = 0, option2Count = 0;
        
        // Running on even cells
        for (int i = 0; i < s.length(); i+=2)
        {
            // Checking if the current or the next cell is '1':
            // * If current cell is 1 then need to change to 0
            // * If the next cell is 1 then need to change the current cell to 1
            if (s.charAt(i) == '1')
                option1Count++;
            if (s.charAt(i + 1) == '1')
                option2Count++;
        }
        
        int option1 = (s.length() / 2) - option1Count;
        int option2 = (s.length() / 2) - option2Count;
        
        // Returning the smaller amount of changes
        return Math.min(option1, option2); 
    }
    
    /**
     * Find if you can travel the array based on the values in the cells without visiting the same place twice or getting out of the array bounds
     * Time complexity: O(n)
     *  O(n) = Running on each cell only once because after visiting cell setting as 0
     * Space complexity: O(n)
     * 
     * @param   a   The array
     * @return  If you can travel in the array based on the values in the cells
     */
    public static boolean isWay(int[] a)
    {
        return isWay(a, 0);
    }
    
    /**
     * Find if you can travel the array based on the values in the cells without visiting the same place twice or getting out of the array bounds
     * Time complexity: O(n)
     *  O(n) = Running on each cell only once because after visiting cell setting as 0
     * Space complexity: O(n)
     * 
     * @param   a   The array
     * @param   currentI    The current cell's index
     * @return  If you can travel in the array based on the values in the cells
     */
    private static boolean isWay(int[] a, int currentI)
    {
        if((currentI < 0 || currentI > a.length - 1) || a[currentI] == 0) // Went out of the array bounds or visited a place that take you no where
            return false;
        else if(currentI == a.length - 1) // All the steps were correct
            return true;
        
        int steps = a[currentI]; // Saves the steps needed
        a[currentI] = 0; // Mark as visited
        
        boolean option1 = isWay(a, currentI + steps); // To the left (everything you own in the box to the left)
        boolean option2 = isWay(a, currentI - steps); // Goes right
        
        return option1 || option2;
    }
    
    /**
     * Print the path to the nearest hill (a cell that the value is bigger than the neighbor  cells) by "climbing" (going to cell only if the cell is bigger than the current one).
     * 
     * Time complexity: O(n)
     *  O(n) = Running on each cell only once because moving to different cell only if bigger the the current one.
     * Space complexity: O(n)
     * @param   mat The matrix
     */
    public static void printPath (int[][] mat)
    {
        System.out.println(printPath(mat, 0, 0)); // Starts in the begining of the array
    }
    
    /**
     * Print the path to the nearest hill (a cell that the value is bigger than the neighbor  cells) by "climbing" (going to cell only if the cell is bigger than the current one).
     * 
     * Time complexity: O(n)
     *  O(n) = Running on each cell only once because moving to different cell only if bigger the the current one.
     * Space complexity: O(n)
     * @param   mat The matrix
     */
    private static String printPath(int[][] mat, int i, int j)
    {
        String currentCell = "(" + i + "," + j + ")"; // Generating current position in readble formate
        
        // Ties to go diffrencet direction (up, down, right and left) to bigger cells (that in the array bound)
        // If can go then calling printPath agine.
        // If reach a hill or can't continue then return the currCell and terminate the current brach of the recursion

        // Goes up
        if (i > 0 && mat[i-1][j] > mat[i][j]) 
            return currentCell + ", " + printPath(mat, i - 1, j);
            
        // Goes down
        if (i < mat.length - 1 && mat[i + 1][j] > mat[i][j])
            return currentCell + ", " + printPath(mat, i + 1, j);
        
        // Goes right
        if (j < mat[i].length - 1 && mat[i][j + 1] > mat[i][j])
            return currentCell + ", " + printPath(mat, i, j + 1);
        
        // Goes left
        if (j > 0 && mat[i][j - 1] > mat[i][j])
            return currentCell + ", " + printPath(mat, i, j - 1);
            
        return currentCell;
    }
 }
