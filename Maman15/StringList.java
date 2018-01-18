public class StringList
{
    private CharNode _head;   
   
    /**
     * Constactor for StringList
     * 
     * Time complexity: O(1)
     * Space complexity: O(1)
     */
    public StringList() {
        _head = null;
    }
    
    /**
     * Constactor for StringList that convert String into StringList
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   s   The string to convert
     */
    public StringList (String s) {
        if(s.length() == 0) // If the string is empty then exits the constractor
            return;
            
        CharNode node = new CharNode(s.charAt(0), s.charAt(0), null);
        _head = node;
        for(int i = 1; i < s.length(); i++) {
            node.setNext(new CharNode(s.charAt(i), s.charAt(i), null));
            node = node.getNext();
        }
    }
    
    /**
     * Constractor that build new StringList from StringList
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   other   The StringList to copy
     */
    public StringList (StringList other) {
        CharNode node = other.getHead();
        if(node == null)
            _head = null;
        else
        {
            _head = new CharNode(node.getData(),node.getValue(),null);
            for(CharNode ptr= node.getNext(),last=_head;ptr!= null; ptr = ptr.getNext())
            {
                last.setNext(new CharNode(ptr.getData(),ptr.getValue(),
                ptr.getNext()));
                last = last.getNext();
            }
        }
    }
    
    /**
     * Constractor that copy nodes
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   node    The node to set as head and copy from
     */
    public StringList(CharNode node) {
        if(node == null)
            _head = null;
        else
        {
            _head = new CharNode(node.getData(),node.getValue(),null);
            for(CharNode ptr= node.getNext(),last=_head;ptr!= null; ptr = ptr.getNext())
            {
                last.setNext(new CharNode(ptr.getData(),ptr.getValue(),
                ptr.getNext()));
                last = last.getNext();
            }
        }
    }
    
    /**
     * Returns the head of the list
     * 
     * Time complexity: O(1)
     * Space complexity: O(1)
     * 
     * @return The head
     */
    public CharNode getHead() {
        return this._head;
    }
        
    /**
     * Set the head of the list
     * 
     * Time complexity: O(1)
     * Space complexity: O(1)
     * 
     * @param   head    the head node to set
     */
    public void setHead(CharNode head) {
        this._head = head;
    }
    
    /**
     * Convert the list into string and surround with "
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @return  The list in string and surrounded with "
     */
    public String toString() {
        String res = "";
        CharNode node = _head;
        
        while(node != null) {
            res += node.getData();
            node = node.getNext();
        }      
        
        return "\"" + res + "\"";
    }
    
    /**
     * Returns the char at the index (assuming that the index is correct)
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   i   The index of the char
     * @return  The char at the index
     */
    public char charAt(int i) {
        CharNode node = _head;
        for(int j = 0; j < i; j++) {
            node = node.getNext();
        }
        return node.getData();
    }
    
    /**
     * Conecting 2 StringList to one
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     * 
     * @param   str the StringList to concatenate
     * @return  The current list with str concated after
     */
    public StringList concat (StringList str) {
        StringList newList = new StringList(this);
        StringList strCopy = new StringList(str);
        CharNode newListHead = newList.getHead();
        
        while(newListHead.getNext() != null)
            newListHead = newListHead.getNext();
        
        newListHead.setNext(strCopy.getHead());
        
        return newList;
    }
    
    /**
     *  Returns the first index of the ch in the list
     *  
     *  Time complexity: O(n)
     * Space complexity: O(1)
     *  
     *  @param  ch  The char to find
     *  @return The first index if the ch in the list, -1 if not found
     */
    public int indexOf (int ch) {
        CharNode node = _head;
        int i = 0;
        
        while(node != null) {
            if(node.getData() == ch)
                return i;
            node = node.getNext();
            i++;
        }
        
        return -1;
    }
    
    /**
     * Returns the first index of the ch in the list starting from fromIndex
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   ch  The char to find
     * @param   fromIndex   the index to start looking from
     * @return  The first index if the ch in the list (starting from fromIndex), -1 if not found
     */
    public int indexOf (int ch, int fromIndex) {
        CharNode node = _head;
        for(int i = 0; i < fromIndex; i++) {
            node = node.getNext();
            if(node == null)
                return -1;
        }
        int i = fromIndex;
        
        while(node != null) {
            if(node.getData() == ch)
                return i;
            node = node.getNext();
            i++;
        }
        
        return -1;
    }
    
    /**
     * Check if the list and str are equal
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   str The list to check
     * @return  If the StringList are equal
     */
    public boolean equals (StringList str) {
        return equals(_head, str.getHead());
    }
    
    /**
     * Comparing the StringLists, if the current list (this) is smaller then return -1, if current list (this) is bigger then return 1, if equals then return 0
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   str The list to compare to
     * @return  if the current list (this) is smaller then return -1, if current list (this) is bigger then return 1, if equals then return 0
     */
    public int compareTo(StringList str) {
        CharNode node1 = _head;
        CharNode node2 = str.getHead();
        
        while(node1 != null || node2 != null) {
            if(node1 == null) // this is smaller
                return 1;
            else if(node2 == null) // str is bigger
                return -1;
            else if(node1.getValue() < node2.getValue()) // this is smaller
                return -1;
            else if(node1.getValue() > node2.getValue()) // str is bigger
                return 1;
                
            node1 = node1.getNext();
            node2 = node2.getNext();
        }
        
        return 0;
    }
    
    /**
     * Returns new list that starts from i
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     * 
     * @param   i   The place to start from
     * @return  New list that starts from i
     */
    public StringList substring(int i) {
        CharNode node = _head;
        for(int j = 0; j < i; j++) {
            node = node.getNext();
            if(node == null)
                return null;
        }
        
        return new StringList(node);
    }
    
    /**
     * Returns new list that starts from i and ends in j
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     * 
     * @param   i   The place to start from
     * @param   j   The place to end
     * @return  New list that starts from i and ends in j
     */
    public StringList substring(int i, int j) {
        StringList newList = new StringList(this);
        CharNode node = newList.getHead();
        for(int k = 0; k < i; k++) {
            node = node.getNext();
            if(node == null)
                return null;
        }
        
        CharNode lastNode = node;
        for(int k = i + 1; k < j; k++) {
            lastNode = lastNode.getNext();
            if(lastNode == null)
                return null;
        }
        lastNode.setNext(null);
        newList.setHead(node);
        
        return newList;
    }
    
    /**
     * Returns the length of the StringList
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @retun   The length of the StringList
     */
    public int length() {
        int i = 0;
        CharNode node = _head;
        while(node != null) {
            i++;
            node = node.getNext();
        }
        return i;
    }
    
    /**
     * Check if the list and str are equal
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param   node1   The node from the current list
     * @param   node2   The node from the second list
     * @return  If the StringList are equal
     */
    private boolean equals (CharNode node1, CharNode node2) {
        if(node1 == null && node2 == null) // If we checked the all node lists
            return true;
        else if((node1 != null && node2 == null) || node1 == null && node2 != null) // One of the nodes is null and the other isn't
            return false;
        else if(node1.getData() != node2.getData()) // if they are diffrent
            return false;
        else // if they are equals and not null
            return equals(node1.getNext(), node2.getNext());
    }
} 