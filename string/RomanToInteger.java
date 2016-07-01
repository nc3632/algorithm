/**
 * Given a roman numeral, convert it to an integer.
 */
public class RomanToInteger {
    enum RomanNumberType {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        private int value;

        RomanNumberType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    public int romanToInt(String s) {
        int index = 0, num = 0;
        for (RomanNumberType type : RomanNumberType.values()) {
            for (; index < s.length(); index += type.name().length()) {
                if (!s.substring(index).startsWith(type.name())) {
                    break;
                }

                num += type.getValue();
            }
        }

        return num;
    }
}
