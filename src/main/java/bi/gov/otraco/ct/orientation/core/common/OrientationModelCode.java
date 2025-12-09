package bi.gov.otraco.ct.orientation.core.common;

public class OrientationModelCode {
    public static String generate(String lastCode) {
        char[] chars = lastCode.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < lastCode.length(); i++) {
            sb.append(chars[i]);
        }
        double number = (Double.parseDouble(String.valueOf(sb))) + 1;
        return "OR" + String.format("%.0f", number);
    }
}