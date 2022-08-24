package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dec2Roman100 {

    public static String[][] romeDig13 = {
            {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
            {"X",
                    "XX",
                    "XXX",
                    "XL",
                    "L",
                    "LX",
                    "LXX",
                    "LXXX",
                    "XC",
//            "C"
            },
            {"C", "CC", "CCC"}
    };


    public static String convertDig2Rom2(String str0) {
        String romeChisl = "";

        int cnt = 0;
        for (int i = str0.length() - 1; i > -1; i--) {

            int cifrLast = Integer.parseInt(str0.substring(i));
//            str00= new StringBuilder(str00.substring(0,i));
            str0 = str0.substring(0, i);

            if (cnt == 0) {
                if (cifrLast > 0) romeChisl = (romeDig13[0][cifrLast - 1]).concat(romeChisl);

            }
            if (cnt == 1)
                if (cifrLast > 0) romeChisl = (romeDig13[1][cifrLast - 1]).concat(romeChisl);
            if (cnt == 2)
                if (cifrLast > 0) romeChisl = (romeDig13[2][cifrLast - 1]).concat(romeChisl);

            cnt++;

        }


        return romeChisl;
    }


    public static void main(String[] args) {

        System.out.println("please, input number:  ");

        String str0 = "";

        try (BufferedReader bufI = new BufferedReader(new InputStreamReader(System.in))) {
            str0 = bufI.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(convertDig2Rom2(str0));

    }
}
