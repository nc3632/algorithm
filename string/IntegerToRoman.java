/**
 * Given an integer, convert it to a roman numeral.
 */
public class IntegerToRoman {
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

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (RomanNumberType type : RomanNumberType.values()) {
            while (num >= type.getValue()) {
                sb.append(type);
                num -= type.getValue();
            }
        }

        return sb.toString();
    }
}
