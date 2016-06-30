/*
 * Copyright 2016 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
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
