public class TestStringList
{
    public static void tests()
    {
        boolean res = true;
        StringList sl1;
        StringList sl2;
        StringList sl3;
        StringList sl4 = new StringList("abbbbb");
        StringList sl5 = new StringList("abcdeee");
        StringList sl6 = new StringList("aaabcde");
        StringList slCopy;
        
        System.out.println("==== Test StringList Constructors ====");
        sl1 = new StringList("aaabcccccdd");
        sl2 = new StringList();
        sl3 = new StringList("aaaaaa");
        System.out.println("\"" + sl1.toString().substring(1, sl1.toString().length() - 1) + "\"");
        if (!sl1.toString().substring(1, sl1.toString().length() - 1).equals("aaabcccccdd"))
            System.out.println("Fail - expected StringList: \"aaabcccccdd\" Result: " + sl1.toString());
        else
            System.out.println("Ok - " + sl1.toString());
        if (!sl2.toString().substring(1, sl2.toString().length() - 1).equals(""))
            System.out.println("Fail - expected StringList: \"\" Result: " + sl2.toString());
        else
            System.out.println("Ok - " + sl2.toString());
        if (!sl3.toString().substring(1, sl3.toString().length() - 1).equals("aaaaaa"))
            System.out.println("Fail - expected StringList: \"aaaaaa\" Result: " + sl3.toString());
        else
            System.out.println("Ok - " + sl3.toString());
        slCopy = new StringList(sl1);
        if (!slCopy.toString().substring(1,slCopy.toString().length() - 1).equals("aaabcccccdd"))
            System.out.println("Copy Constructor fail - expected StringList: \"aaabcccccdd\" Result: " + slCopy.toString());
        slCopy = new StringList(sl2);
        if (!slCopy.toString().substring(1,slCopy.toString().length() - 1).equals(""))
            System.out.println("Fail - expected StringList: \"\" Result: " + slCopy.toString());
        slCopy = new StringList(sl3);
        if (!slCopy.toString().substring(1,slCopy.toString().length() - 1).equals("aaaaaa"))
            System.out.println("Fail - expected StringList: \"aaaaaa\" Result: " + slCopy.toString());
        System.out.println("Done");
            
            
        System.out.println("==== Test charAt(int i) ====");
        testCharAt(sl1);
        
        System.out.println("==== Test concat(String str) ====");
        testConcat(sl1);
        testConcat(sl2);
        
        System.out.println("\n==== Test indexOf(int ch) ====");
        System.out.println("Tested str: " + sl1.toString());
        testIndexOf1(sl1, 'a', 0);
        testIndexOf1(sl1, 'b', 3);
        testIndexOf1(sl1, 'c', 4);
        testIndexOf1(sl1, 'd', 9);
        sl1 = sl1.concat(new StringList("ee"));
        testIndexOf1(sl1, 'e', 11);
        testIndexOf1(sl1, 'z', -1);
        
        System.out.println("==== Test indexOf(int ch, int fromIndex) ====");
        testIndexOf2(sl1, 'a', 0, 0);
        testIndexOf2(sl1, 'a', 1, 1);
        testIndexOf2(sl1, 'a', 5, -1);
        testIndexOf2(sl1, 'b', 0, 3);
        testIndexOf2(sl1, 'b', 1, 3);
        testIndexOf2(sl1, 'b', 1, 3);
        testIndexOf2(sl1, 'c', 3, 4);
        testIndexOf2(sl1, 'c', 4, 4);
        testIndexOf2(sl1, 'd', 10, 10);
        sl1 = sl1.concat(new StringList("aaab"));
        testIndexOf2(sl1, 'a', 5, 13);
        testIndexOf2(sl1, 'b', 5, 16);
        testIndexOf2(sl1, 'b', 16, 16);
        testIndexOf2(sl1, 'z', 0, -1);
        testIndexOf2(sl1, 'z', 10, -1);
        testIndexOf2(sl1, 'z', 16, -1);
        
        System.out.println("\n==== Test substring(int i) ====\n");
        res = true;
        System.out.println("Test str: " + sl1.toString().substring(1,sl1.toString().length() - 1));
        for (int i = 0; i < sl1.toString().substring(1,sl1.toString().length() - 1).length(); i++)
            res = res && testSubstr1(sl1, i, sl1.toString().substring(1,sl1.toString().length() - 1).substring(i));
        System.out.println("Test str: " + sl2.toString().substring(1,sl2.toString().length() - 1));
        for (int i = 0; i < sl2.toString().substring(1,sl2.toString().length() - 1).length(); i++)
            res = res && testSubstr1(sl2, i, sl2.toString().substring(1,sl2.toString().length() - 1).substring(i));
        System.out.println("Test str: " + sl3.toString().substring(1,sl3.toString().length() - 1));
        for (int i = 0; i < sl3.toString().substring(1,sl3.toString().length() - 1).length(); i++)
            res = res && testSubstr1(sl3, i, sl3.toString().substring(1,sl3.toString().length() - 1).substring(i));
        System.out.println("Test str: " + sl4.toString().substring(1,sl4.toString().length() - 1));
        for (int i = 0; i < sl4.toString().substring(1,sl4.toString().length() - 1).length(); i++)
            res = res && testSubstr1(sl4, i, sl4.toString().substring(1,sl4.toString().length() - 1).substring(i));
        System.out.println("Test str: " + sl5.toString().substring(1,sl5.toString().length() - 1));
        for (int i = 0; i < sl5.toString().substring(1, sl5.toString().length() - 1).length(); i++)
            res = res && testSubstr1(sl5, i, sl5.toString().substring(1, sl5.toString().length() - 1).substring(i));
        System.out.println("Test str: " + sl6.toString().substring(1, sl6.toString().length() - 1));
        for (int i = 0; i < sl6.toString().substring(1, sl6.toString().length() - 1).length(); i++)
            res = res && testSubstr1(sl6, i, sl6.toString().substring(1, sl6.toString().length() - 1).substring(i));
        if (res == true)
            printSuccess();
        
        System.out.println("\n==== Test substring(int i, int j) ====\n");
        res = true;
        System.out.println("Test str: " + sl1.toString().substring(1, sl1.toString().length() - 1));
        for (int i = 0; i < sl1.toString().substring(1, sl1.toString().length() - 1).length(); i++)
        {
            for (int j = i; j < sl1.toString().substring(1, sl1.toString().length() - 1).length(); j++)
                res = res && testSubstr2(sl1, i, j, sl1.toString().substring(1, sl1.toString().length() - 1).substring(i,j));
        }
        System.out.println("Test str: " + sl2.toString().substring(1, sl2.toString().length() - 1));
        for (int i = 0; i < sl2.toString().substring(1, sl2.toString().length() - 1).length(); i++)
        {
            for (int j = i; j < sl2.toString().substring(1, sl2.toString().length() - 1).length(); j++)
                res = res && testSubstr2(sl2, i, j, sl2.toString().substring(1, sl2.toString().length() - 1).substring(i,j));
        }
        System.out.println("Test str: " + sl3.toString().substring(1, sl3.toString().length() - 1));
        for (int i = 0; i < sl3.toString().substring(1, sl3.toString().length() - 1).length(); i++)
        {
            for (int j = i; j < sl3.toString().substring(1, sl3.toString().length() - 1).length(); j++)
                res = res && testSubstr2(sl3, i, j, sl3.toString().substring(1, sl3.toString().length() - 1).substring(i,j));
        }
        System.out.println("Test str: " + sl4.toString().substring(1, sl4.toString().length() - 1));
        for (int i = 0; i < sl4.toString().substring(1, sl4.toString().length() - 1).length(); i++)
        {
            for (int j = i; j < sl4.toString().substring(1, sl4.toString().length() - 1).length(); j++)
                res = res && testSubstr2(sl4, i, j, sl4.toString().substring(1, sl4.toString().length() - 1).substring(i,j));
        }
        System.out.println("Test str: " + sl5.toString().substring(1, sl5.toString().length() - 1));
        for (int i = 0; i < sl5.toString().substring(1, sl5.toString().length() - 1).length(); i++)
        {
            for (int j = i; j < sl5.toString().substring(1, sl5.toString().length() - 1).length(); j++)
                res = res && testSubstr2(sl5, i, j, sl5.toString().substring(1, sl5.toString().length() - 1).substring(i,j));
        }
        System.out.println("Test str: " + sl6.toString().substring(1, sl6.toString().length() - 1));
        for (int i = 0; i < sl6.toString().substring(1, sl6.toString().length() - 1).length(); i++)
        {
            for (int j = i; j < sl6.toString().substring(1, sl6.toString().length() - 1).length(); j++)
                res = res && testSubstr2(sl6, i, j, sl6.toString().substring(1, sl6.toString().length() - 1).substring(i,j));
        }
        if(res == true)
            printSuccess();
            
        System.out.println("\n==== Test length() ====\n");
        res = true;
        res = testLength(sl1, sl1.toString().substring(1, sl1.toString().length() - 1).length()) && testLength(sl2, sl2.toString().substring(1, sl2.toString().length() - 1).length()) && testLength(sl3, sl3.toString().substring(1, sl3.toString().length() - 1).length());
        res = res && testLength(sl4, sl4.toString().substring(1, sl4.toString().length() - 1).length()) && testLength(sl5, sl5.toString().substring(1, sl5.toString().length() - 1).length()) && testLength(sl6, sl6.toString().substring(1, sl6.toString().length() - 1).length());
        if (res == true)
            printSuccess();
            
        System.out.println("\n==== Test toString() ====\n");
        res = true;
        res = testToString(sl1, "aaabcccccddeeaaab") && testToString(sl2, "") && testToString(sl3, "aaaaaa");
        res = res && testToString(sl4, "abbbbb") && testToString(sl5, "abcdeee") && testToString(sl6, "aaabcde");
        if (res == true)
            printSuccess();
            
        System.out.println("\n==== Test equals(StringList str) ====\n");
        res = true;
        res = testEquals(sl1, new StringList(sl1), true) && testEquals(sl1, new StringList(sl1.toString().substring(1, sl1.toString().length() - 1) + "a"), false);
        res = res && testEquals(sl2, new StringList(sl2), true) && testEquals(sl3, new StringList(sl3), true);
        res = res && testEquals(sl4, new StringList(sl4), true) && testEquals(sl5, new StringList(sl5), true);
        res = res && testEquals(new StringList(""), new StringList(""), true) && testEquals(sl1, new StringList(sl1.toString().substring(1, sl1.toString().length() - 1) + "aaa"), false);
        res = res && testEquals(new StringList("a"), new StringList("a"), true) && testEquals(sl1, new StringList(sl1.toString().substring(1, sl1.toString().length() - 1) + "abc"), false);
        res = res && testEquals(new StringList("a"), new StringList("aa"), false) && testEquals(new StringList("ab"), new StringList("a"), false);
        res = res && testEquals(new StringList("ab"), new StringList("b"), false) && testEquals(new StringList("ab"), new StringList("ba"), false);
        if (res == true)
            printSuccess();
            
        System.out.println("\n==== Test compareTo(StringList str) ====\n");
        res = true;
        res = testCompareTo(sl1, new StringList(sl1)) && testCompareTo(sl2, new StringList(sl2));
        res = res && testCompareTo(sl3, new StringList(sl3)) && testCompareTo(sl4, new StringList(sl4));
        res = res && testCompareTo(sl5, new StringList(sl5)) && testCompareTo(sl6, new StringList(sl6));
        res = res && testCompareTo(new StringList("ape"), new StringList("zebra")) && testCompareTo(new StringList("ape"), new StringList("apple"));
        res = res && testCompareTo(new StringList("aaab"), new StringList("aaa")) && testCompareTo(new StringList("aaa"), new StringList("aaab"));
        res = res && testCompareTo(new StringList("bababa"), new StringList("bababaa")) && testCompareTo(new StringList("aaaabbs"), new StringList("aaaabs"));
        res = res && testCompareTo(new StringList(""), new StringList("bababaa")) && testCompareTo(new StringList("aaaabbssss"), new StringList("aaaabb"));
        res = res && testCompareTo(new StringList("cat"), new StringList("catfish")) && testCompareTo(new StringList(""), new StringList(""));
        if (res == true)
            printSuccess();
    }
    
    private static void testCharAt(StringList sl)
    {
        System.out.print("Tested String: " + sl.toString().substring(1, sl.toString().length() - 1));
        char c = sl.charAt(0);
        System.out.println("charAt(0) - Expected: 'a' Result: '" + c + "'");
        c = sl.charAt(1);
        System.out.println("charAt(1) - Expected: 'a' Result: '" + c + "'");
        c = sl.charAt(2);
        System.out.println("charAt(2) - Expected: 'a' Result: '" + c + "'");
        c = sl.charAt(3);
        System.out.println("charAt(3) - Expected: 'b' Result: '" + c + "'");
        c = sl.charAt(4);
        System.out.println("charAt(4) - Expected: 'c' Result: '" + c + "'");
        c = sl.charAt(8);
        System.out.println("charAt(8) - Expected: 'c' Result: '" + c + "'");
        c = sl.charAt(9);
        System.out.println("charAt(9) - Expected: 'd' Result: '" + c + "'");
        c = sl.charAt(10);
        System.out.println("charAt(10) - Expected: 'd' Result: '" + c + "'\n");
    }
    
    private static void testConcat(StringList sl)
    {
        System.out.println("Tested String: " + sl.toString());
        StringList s1 = new StringList("a");
        StringList s2 = new StringList("abc");
        StringList s3 = new StringList("aaabc");
        StringList s4 = new StringList("aaabbccc");
        StringList s5 = new StringList("ddd");
        StringList s6 = new StringList("dddaaab");
        //System.out.println("concat('a') - Expected: '" + sl.toString().substring(1, sl.toString().length() - 1) + "a" + "' Result: '" + sl.concat(s1).toString().substring(1, sl.concat(s1).toString().length() - 1) + "'");
        //System.out.println("concat('abc') - Expected: '" + sl.toString().substring(1, sl.toString().length() - 1) + "abc" + "' Result: '" + sl.concat(s2).toString().substring(1, sl.concat(s2).toString().length() - 1) + "'");
        //System.out.println("concat('aaabc') - Expected: '" + sl.toString().substring(1, sl.toString().length() - 1) + "aaabc" + "' Result: '" + sl.concat(s3).toString().substring(1, sl.concat(s3).toString().length() - 1) + "'");
        //System.out.println("concat('aaabbccc') - Expected: '" + sl.toString().substring(1, sl.toString().length() - 1) + "aaabbccc" + "' Result: '" + sl.concat(s4).toString().substring(1, sl.concat(s4).toString().length() - 1) + "'");
        //System.out.println("concat('ddd') - Expected: '" + sl.toString().substring(1, sl.toString().length() - 1) + "ddd" + "' Result: '" + sl.concat(s5).toString().substring(1, sl.concat(s5).toString().length() - 1) + "'");
        //System.out.println("concat('dddaaab') - Expected: '" + sl.toString().substring(1, sl.toString().length() - 1) + "dddaaab" + "' Result: '" + sl.concat(s6).toString().substring(1, sl.concat(s6).toString().length() - 1) + "'");
    }
    
    private static void testIndexOf1(StringList sl, char chr, int expected)
    {
        System.out.println("Tested String: " + sl.toString().substring(1, sl.toString().length() - 1) + " Searched chr: '" + chr + "'");
        int res = sl.indexOf(chr);
        if (res == expected)
            printSuccess();
        else
            System.out.println("Failure! expected index: '" + expected + "', result: '" + res + "'" );
    }
    
    private static void testIndexOf2(StringList sl, char chr, int from, int expected)
    {
        System.out.println("Tested String: " + sl.toString().substring(1, sl.toString().length() - 1) + " Searched chr: '" + chr + "' Search from index: " + from);
        int res = sl.indexOf(chr, from);
        if (res == expected)
            printSuccess();
        else
            System.out.println("Failure! expected index: '" + expected + "', result: '" + res + "'" );
    }
    
    private static boolean testSubstr1(StringList sl, int from, String expected)
    {
        String res = sl.substring(from).toString().substring(1, sl.substring(from).toString().length() - 1);
        if (res.equals(expected))
            return true;
        else
        {
            System.out.println("Tested String: " + sl.toString().substring(1, sl.toString().length() - 1) + " Substr from index: " + from);
            System.out.println("Failure! expected substring: '" + expected + "', result: '" + res + "'" );
        }
        return false;
    }
    
    private static boolean testSubstr2(StringList sl, int from, int to, String expected)
    {
        String res = sl.substring(from, to).toString().substring(1, sl.substring(from, to).toString().length() - 1);
        if (!res.equals(expected))
        {
            System.out.println("Tested String: " + sl.toString().substring(1, sl.toString().length() - 1) + " Substr from index: " + from + " to index: " + to);
            System.out.println("Failure! expected substring: '" + expected + "', result: '" + res + "'" );
            return false;
        }
        return true;
    }
    
    private static boolean testLength(StringList sl, int expected)
    {
        int res = sl.length();
        if (res != expected)
        {
            System.out.println("Tested String: " + sl.toString().substring(1, sl.toString().length() - 1));
            System.out.println("Failure! expected length: " + expected + ", result: " + res);
            return false;
        }
        return true;
    }
    
    private static boolean testToString(StringList sl, String expected)
    {
        String res = sl.toString();
        if (!res.equals("\"" + expected + "\""))
        {
            System.out.println("Tested string: " + sl.toString().substring(1, sl.toString().length() - 1));
            System.out.println("Failure! expected: " + "\"" + expected + "\"" + ", result: " + res);
            return false;
        }
        return true;
    }
    
    private static boolean testEquals(StringList sl1, StringList sl2, boolean expected)
    {
        boolean res = sl1.equals(sl2);
        if (res != expected)
        {
            System.out.println("Tested strings: " + sl1.toString() + " and " + sl2.toString());
            System.out.println("Failure! expected bool result: " + expected);
            return false;
        }
        return true;
    }
    
    private static boolean testCompareTo(StringList sl1, StringList sl2)
    {
        int res = sl1.compareTo(sl2);
        int expected = sl1.toString().substring(1, sl1.toString().length() - 1).compareTo(sl2.toString().substring(1, sl2.toString().length() - 1));
        if (res != expected)
        {
            System.out.println("Tested strings: " + sl1.toString() + " and " + sl2.toString() + " expected: " + expected + " res: " + res);
            System.out.println("Failure! expected compare result: " + expected + " actual result: " + res);
            return false;
        }
        return true;
    }
    
    private static void printSuccess(){System.out.println("Success");}
}
