/*
Built by Ran Mariuma
 */

import java.util.ArrayList;

public class Main {
    private static final boolean SHOW_LOG = false;

    private static void testFormalExamples(String s) {
        StringList sl = new StringList(s);

        System.out.println("s=\"" + s + "\"");
        System.out.println("s.length () \"" + sl.length() + "\"");
        System.out.println("s.chatAt(1)=\"" + sl.charAt(1) + "\"");
        System.out.println("s.indexOf('a')=\"" + sl.indexOf('a') + "\"");
        System.out.println("s.indexOf('a,2')=\"" + sl.indexOf('a', 2) + "\"");
        System.out.println("s.indexOf('d')=\"" + sl.indexOf('d') + "\"");
        System.out.println("s.compareTo(\"abcde\")=\"" + sl.compareTo(new StringList("abcde")) + "\"");
    }

    public static void main(String[] args) {

        String s = "abcae";
        testFormalExamples(s);

        testString("Hello World!");
        testString("abcdefg");
        testString("llllllll");
        testString("alllllla");
        testString("allallla");
        testString("allllll");
        testString("lllllla");
        testString("lll\nllla");
        testString("0101010");
        testString("מנסה לדפוק את המערכת");
        testString("aaabbbccc");
        testString("");
        testString(null);

    }

    private static void testString(String s) {
        boolean success = true;
        System.out.println("Testing: " + s);

        StringList lst = new StringList(s);

        // Copy constructor
        StringList lst2 = new StringList(lst);

        success = success && testToString(lst, s);
        success = success && checkListChanged(lst, s);

        success = success && testSubstring(lst, s);
        success = success && checkListChanged(lst, s);

        // Constructor relies on toString function to work properly
        success = success && testConstructors(s);
        success = success && checkListChanged(lst, s);

        success = success && testLength(s, lst, lst2);
        success = success && checkListChanged(lst, s);

        // CharAt
        success = success && testCharAt(s, lst, lst2);
        success = success && checkListChanged(lst, s);

        // Concat
        success = success && testConcat(s, lst, lst2);
        success = success && checkListChanged(lst, s);

        // Concat
        success = success && testIndexOf(s, lst, lst2);
        success = success && testIndexOf(s, lst, lst2, lst.length() / 2);
        success = success && testIndexOf(s, lst, lst2, lst.length() + 1);
        success = success && testIndexOf(s, lst, lst2, lst.length() + 100);
        success = success && checkListChanged(lst, s);

        // Equals
        success = success && testEquals(s, lst, lst2);
        success = success && checkListChanged(lst, s);

        // CompareTo
        success = success && testCompareTo(s);
        success = success && checkListChanged(lst, s);

        //Summarize
        if (success) {
            System.out.println("Success\n");
        }
    }

    private static boolean checkListChanged(StringList lst, String s) {
        // Make sure none of the above functions changed the original StringList
        log("Checking if the last function changed the original list");
        if (!lst.toString().equals("\"" + ((s == null) ? "" : s) + "\"")) {
            System.out.println("Original StringList changed! Fix it!");
            // If the list changed change it back to original for next functions
            return false;
        }

        // Means the list was not changed (it means success)
        return true;
    }

    private static boolean testCompareTo(String s) {
        boolean success = true;
        StringList lst = new StringList(s);
        String testedStr = s;
        success = success && testCompareTo(testedStr, lst);

        if (s != null && s.length() > 0) {
            for (int i = 0; i < s.length(); i++) {
                for (int charChange = -1; charChange < 2; charChange++) {
                    char replacementChar = (char) (s.charAt(i / 2) + charChange);
                    testedStr = s.substring(0, i) + replacementChar + s.substring(i + 1);
                    success = success && testCompareTo(testedStr, lst);
                }
                testedStr = s.substring(0, i);
                success = success && testCompareTo(testedStr, lst);
            }
        }
        return true;
    }

    private static boolean testCompareTo(String stringToTest, StringList lst) {
        StringList currentTested = new StringList(stringToTest);
        boolean success = true;
        success = success && testCompareTo(lst, currentTested);
        success = success && testCompareTo(currentTested, lst);
        return success;
    }

    private static boolean testCompareTo(StringList lst1, StringList lst2) {
        int myCompareToResult = lst1.compareTo(lst2);
        String lst1StringNoFirstAndLastChars = lst1.toString().substring(1, lst1.toString().length() - 1);
        String lst2StringNoFirstAndLastChars = lst2.toString().substring(1, lst2.toString().length() - 1);
        int javaCompareToResult = lst1StringNoFirstAndLastChars.compareTo(lst2StringNoFirstAndLastChars);
        log("Testing compareTo(String str) on: " + lst1.toString() + " str=" + lst2.toString() + " Result: " + myCompareToResult);
        if (!isEqualCompareToResult(myCompareToResult, javaCompareToResult)) {
            System.out.println("compareTo(StringList str) returned " + myCompareToResult + " when comparing two StringLists " + lst1.toString() + " and " + lst2.toString() + " expected " + javaCompareToResult);
            return false;
        }
        return true;
    }

    private static boolean isEqualCompareToResult(int res1, int res2) {
        return (res1 < 0 && res2 < 0) || (res1 > 0 && res2 > 0) || res1 == res2;
    }

    private static boolean testSubstring(StringList lst, String s, int i, int j) {
        StringList lstNew = lst.substring(i, j);
        log("Testing Substring(int i,int j) on string:" + s + " for i=" + i + " j=" + j + " result: " + lstNew);
        if (j > s.length()) {
            j = s.length();
        }
        if (j < i) {
            j = i;
        }
        if (!lstNew.toString().equals("\"" + s.substring(i, j) + "\"")) {
            System.out.println("Substring(int i,int j) returned " + lstNew.toString() + " for i=" + i + ";j=" + j + " instead of " + "\"" + s.substring(i, j) + "\"");
            return false;
        }
        return true;
    }

    private static boolean testSubstring(StringList lst, String s, int i) {
        StringList lstNew = lst.substring(i);
        log("Testing Substring(int i) on string:" + s + " for i=" + i + " result: " + lstNew);
        if (!lstNew.toString().equals("\"" + s.substring(i) + "\"")) {
            System.out.println("Substring(int i) returned " + lstNew.toString() + " for i=" + i + " instead of " + "\"" + s.substring(i) + "\"");
            return false;
        }
        return true;
    }

    private static boolean testSubstring(StringList lst, String s) {
        boolean success = true;
        if (s != null) {
            if (s.length() > 0) {
                success = success && testSubstring(lst, s, 0, 0);
                success = success && testSubstring(lst, s, 0);
            }

            if (s.length() > 1) {
                success = success && testSubstring(lst, s, 1, 1);
                success = success && testSubstring(lst, s, 1, 1);
                success = success && testSubstring(lst, s, s.length() - 1, s.length());
            }

            if (s.length() > 2) {
                success = success && testSubstring(lst, s, s.length() / 2);
                success = success && testSubstring(lst, s, 0, s.length() / 2);
                success = success && testSubstring(lst, s, 1, 2);
            }
            success = success && testSubstring(lst, s, 0, s.length());

        }
        return success;
    }

    private static boolean testEquals(String s, StringList lst, StringList lst2) {
        boolean equals = lst.equals(lst2);
        log("Testing equals(StringList str) on string:" + s + " for this=" + lst.toString() + " str=" + lst2 + " result: " + equals);
        if (!equals) {
            System.out.println("Seems like " + lst.toString() + " is not equal its copy " + lst2.toString() + " Fix equals()");
            return false;
        }
        equals = lst.equals(null);
        log("Testing equals(StringList str) on string: " + s + " for this=" + lst.toString() + " str=null result: " + equals);
        if (equals) {
            System.out.println("Seems like " + lst.toString() + " is equal to null Fix equals()");
            return false;
        }
        return true;
    }

    private static boolean testIndexOf(String s, StringList lst, StringList lst2) {
        char currentChar;
        int index1OfChar;
        int index2OfChar;
        if (s == null) {
            log("Testing indexOf(int ch) on string: null for this=" + lst.toString() + " should always return -1");
            if ((lst.indexOf(-2) != -1) ||
                    (lst.indexOf(14) != -1) ||
                    (lst.indexOf(120) != -1) ||
                    (lst.indexOf(1200) != -1)) {
                System.out.println("indexOf(int ch) on empty string should always return -1 got something else");
                return false;
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                currentChar = s.charAt(i);
                index1OfChar = lst.indexOf(currentChar);
                log("Testing indexOf(int ch) on string:" + s + " for this=" + lst.toString() + " looking for char: '" + currentChar + "' result: " + index1OfChar);

                if (index1OfChar != s.indexOf(currentChar)) {
                    System.out.println("indexOf(int ch) returned: \"" + index1OfChar + "\" on char:'" + currentChar + "' instead of " + s.indexOf(currentChar));
                    return false;
                }
                index2OfChar = lst2.indexOf(currentChar);
                log("Testing indexOf(int ch) on string:" + s + " for this=" + lst.toString() + "(a copy of last StringList) looking for char: '" + currentChar + "' result: " + index1OfChar);
                if (index2OfChar != s.indexOf(currentChar)) {
                    System.out.println("indexOf(int ch) on copy object returned: \"" + index2OfChar + " on char:'" + currentChar + "' instead of " + s.indexOf(currentChar));
                    return false;
                }
            }

            if (lst.indexOf(-50) != -1) {
                System.out.println("indexOf() on -50 (input) returned: \"" + lst.indexOf(-50) + "\" instead of " + -1);
                return false;
            }
        }
        return true;
    }

    private static boolean testIndexOf(String s, StringList lst, StringList lst2, int startFrom) {
        char currentChar;
        int index1OfChar;
        int index2OfChar;
        if (s == null) {
            log("Testing indexOf(int ch, int fromIndex) on string: null for this=" + lst.toString() + " should always return -1");
            if ((lst.indexOf(-2, startFrom) != -1) ||
                    (lst.indexOf(14, startFrom) != -1) ||
                    (lst.indexOf(120, startFrom) != -1) ||
                    (lst.indexOf(1200, startFrom) != -1)) {
                System.out.println("indexOf() on empty string should always return -1 got something else");
                return false;
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                currentChar = s.charAt(i);
                index1OfChar = lst.indexOf(currentChar, startFrom);
                log("Testing indexOf(int ch, int fromIndex) on string:" + s + " for this=" + lst.toString() + " starting from:" + startFrom + " looking for char: '" + currentChar + "' result: " + index1OfChar);

                if (index1OfChar != s.indexOf(currentChar, startFrom)) {
                    System.out.println("indexOf(int ch, int fromIndex) returned: \"" + index1OfChar + "\" on char:'" + currentChar + "' starting from:" + startFrom + " instead of " + s.indexOf(currentChar, startFrom));
                    return false;
                }
                index2OfChar = lst2.indexOf(currentChar, startFrom);
                log("Testing indexOf(int ch, int fromIndex) on string:" + s + " for this=" + lst2.toString() + "(copy of first StringList) starting from:" + startFrom + " looking for char: '" + currentChar + "' result: " + index2OfChar);
                if (index2OfChar != s.indexOf(currentChar, startFrom)) {
                    System.out.println("indexOf(int ch, int fromIndex) on copy object returned: \"" + index2OfChar + "\" on char:'" + currentChar + "' starting from:" + startFrom + "  instead of " + s.indexOf(currentChar, startFrom));
                    return false;
                }
            }

            if (lst.indexOf(-50, startFrom) != -1) {
                System.out.println("indexOf(int ch, int fromIndex) on -50 (input) returned: \"" + lst.indexOf(-50, startFrom) + "\" instead of " + -1);
                return false;
            }
        }
        return true;
    }

    private static boolean testConcat(String s, StringList lst, StringList lst2) {

        final String additional = "AdditionalString!";
        StringList additionalString = new StringList(additional);
        StringList concatResult1AdditionalLast = lst.concat(additionalString);
        StringList concatResult1AdditionalFirst = additionalString.concat(lst);
        StringList concatResult2AdditionalLast = lst2.concat(additionalString);
        StringList concatResult2AdditionalFirst = additionalString.concat(lst2);
        if (s == null) {
            String representation = getStringRepresentation(additional);
            if (!concatResult1AdditionalLast.toString().equals(representation)) {
                System.out.println("concat() returned: \"" + concatResult1AdditionalLast + "\" instead of " + representation);
                return false;
            }
            if (!concatResult2AdditionalLast.toString().equals(representation)) {
                System.out.println("concat() on copy object returned: \"" + concatResult2AdditionalLast + "\" instead of " + representation);
                return false;
            }
            if (!concatResult1AdditionalFirst.toString().equals(representation)) {
                System.out.println("concat() returned: \"" + concatResult1AdditionalFirst + "\" instead of " + representation);
                return false;
            }
            if (!concatResult2AdditionalFirst.toString().equals(representation)) {
                System.out.println("concat() on copy object returned: \"" + concatResult2AdditionalFirst + "\" instead of " + representation);
                return false;
            }
        } else {
            if (concatResult1AdditionalLast.toString().equals(s + additional)) {
                System.out.println("concat() should have returned \"" + s + additional + "\" But it returned " + concatResult1AdditionalLast.toString());
                return false;
            }
            if (concatResult1AdditionalFirst.toString().equals(additional + s)) {
                System.out.println("concat() should have returned \"" + additional + s + "\" But it returned " + concatResult1AdditionalFirst.toString());
                return false;
            }
            if (concatResult2AdditionalLast.toString().equals(s + additional)) {
                System.out.println("concat() should have returned \"" + s + additional + "\" But it returned " + concatResult2AdditionalLast.toString());
                return false;
            }
            if (concatResult2AdditionalFirst.toString().equals(additional + s)) {
                System.out.println("concat() should have returned \"" + additional + s + "\" But it returned " + concatResult2AdditionalFirst.toString());
                return false;
            }
        }
        return true;
    }


    private static boolean testCharAt(String s, StringList lst, StringList lst2) {
        if (s == null) {
            lst.charAt(0);
            lst.charAt(lst.length());
            lst2.charAt(lst2.length());
        } else {
            for (int i = 0; i < s.length(); i++) {
                char actual = s.charAt(i);
                char charAt1 = lst.charAt(i);
                char charAt2 = lst2.charAt(i);
                if (actual != charAt1) {
                    System.out.println("charAt() returned: \"" + charAt1 + "\" instead of " + actual);
                    return false;
                }
                if (charAt1 != charAt2) {
                    System.out.println("charAt() of first object: \"" + charAt1 + "\" doesn't match second: " + charAt2);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean testToString(StringList lst, String s) {
        String representation = getStringRepresentation(s);
        String lstString = lst.toString();
        if (!representation.equals(lstString)) {
            System.out.println("toString() returned: \"" + lst.toString() + "\" instead of " + representation + " Could be also a problem with constructor StringList(String s)");
            return false;
        }
        return true;
    }

    private static boolean testLength(String s, StringList lst, StringList lst2) {
        int length1 = lst.length();
        int length2 = lst.length();
        if (s != null && s.length() != length1) {
            System.out.println("length() returned: \"" + length1 + "\" instead of " + s.length());
            return false;
        }
        if (length1 != length2) {
            System.out.println("length() of first object: \"" + length1 + "\" doesn't match second: " + length2);
            return false;
        }
        return true;
    }

    private static boolean testConstructors(String s) {
        boolean success = true;

        success = success && testConstructorsForAliasing(s);
        success = success && testConstructorsForData(s);

        return success;
    }

    private static boolean testConstructorsForData(String s) {
        StringList lst1 = new StringList(s);
        StringList lst2 = new StringList(lst1);
        if (!lst1.toString().equals(lst2.toString())) {
            System.out.println("Constructor StringList(StringList other) doesn't copy the data correctly");
            return false;
        }
        return true;
    }

    private static boolean testConstructorsForAliasing(String s) {
        ArrayList<CharNode> nodes = new ArrayList<CharNode>();
        CharNode currentNode;
        if (s != null && s.length() > 0) {
            nodes.add(new CharNode(s.charAt(0), 1, null));

            for (int charIndex = 1; charIndex < s.length(); charIndex++) {
                int lastNodeIndex = nodes.size() - 1;
                CharNode lastCharNode = nodes.get(lastNodeIndex);
                if (s.charAt(charIndex) == nodes.get(lastNodeIndex).getData()) {
                    lastCharNode.setValue(lastCharNode.getValue() + 1);
                    nodes.set(lastNodeIndex, lastCharNode);
                } else {
                    currentNode = new CharNode(s.charAt(charIndex), 1, null);
                    lastCharNode.setNext(currentNode);
                    nodes.add(currentNode);
                }
            }
        }

        CharNode head;
        if (nodes.size() > 0) {
            head = nodes.get(0);
        } else {
            head = null;
        }
        StringList stringList = new StringList(head);
        StringList stringList2 = new StringList(stringList);

        for (int i = 0; i < nodes.size(); i++) {
            CharNode node = nodes.get(i);
            node.setData((char) (node.getData() + 1));
        }
        if (!("\"" + ((s != null) ? s : "") + "\"").equals(stringList.toString())) {
            System.out.println("Constructor StringList(CharNode node) creates aliasing");
            return false;
        }
        if (!stringList2.toString().equals(stringList.toString())) {
            System.out.println("Constructor StringList(StringList other) creates aliasing with inner char nodes");
            return false;
        }
        if (stringList2 == stringList) {
            System.out.println("Constructor StringList(StringList other) creates aliasing with other");
            return false;
        }
        return true;
    }

    private static String getStringRepresentation(String s) {
        return (s == null) ? "\"\"" : "\"" + s + "\"";
    }

    private static void log(String s) {
        if (SHOW_LOG) {
            System.out.println(s);
        }
    }
}