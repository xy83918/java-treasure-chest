package cc.adaoz.parse.string;

/**
 * @author albert on 1/15/20
 */
public class ParseStringTest {

    public static void main(String[] args) {


        String s = "1231.20248";

        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            Double.valueOf(s);
        }

        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            strToObject(s);
        }

        System.out.println(System.nanoTime() - start);
    }

    public static Object strToObject(final String str) {

        if (str == null || str.length() > 17) {  //out of Long range

            return str;

        }

        if (str.equals("")) {
            return ""; //ensure interned string is returned
        }

        if (str.length() == 1) {
            return str.charAt(0); //return Character
        }

        //if starts with zero - support only "0" and "0.something"

        if (str.charAt(0) == '0') {

            if (str.equals("0")) {
                return 0;
            }

            if (!str.startsWith("0."))  //this may be a double

            {
                return str;
            }

        }


        long res = 0;

        int sign = 1;

        for (int i = 0; i < str.length(); ++i) {

            final char c = str.charAt(i);

            if (c <= '9' && c >= '0') {
                res = res * 10 + (c - '0');
            } else if (c == '.') {

                //too lazy to write a proper Double parser, use JDK one

                try {

                    final Double val = Double.valueOf(str);

                    //check if value converted back to string equals to an original string

                    final String reverted = val.toString();

                    return reverted.equals(str) ? val : str;

                } catch (NumberFormatException ex) {

                    return str;

                }

            } else if (c == '-') {

                if (i == 0) {
                    sign = -1; //switch sign at first position
                } else {
                    return str; //otherwise it is not numeric
                }

            } else if (c == '+') {

                if (i == 0) {
                    sign = 1; //sign at first position
                } else {
                    return str; //otherwise it is not numeric
                }

            } else //non-numeric

            {
                return str;
            }

        }

        //cast to int if value is in int range

        if (res < Integer.MAX_VALUE) {
            return (int) res * sign;
        }

        //otherwise return Long

        return res * sign;
    }
}
