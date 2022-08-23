package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        calculaturus();
    }
    private static void calculaturus() {
        String str1 = input();
        String[] massOrig = str1.split(" ");
        check1(massOrig);
        String[] mssOp = {checkNumber(massOrig[0]), checkNumber(massOrig[1]), checkNumber(massOrig[2])};
        check2(mssOp);
        arabcount(mssOp, massOrig);
        romecount(mssOp, massOrig);
    }

    private static void romecount(String[] mssOp, String[] massOrig) {
        String[] massDig;
        if (mssOp[0].equals("rome") && mssOp[1].equals("sign") && mssOp[2].equals("rome")) {
            int chisl1 = convertRom2Dig(massOrig[0]);

            int chisl2 = convertRom2Dig(massOrig[2]);
            if (chisl1 == -1 || chisl2 == -1) System.out.println("Одно из чисел странное");

            massDig = new String[]{String.valueOf(chisl1), massOrig[1], String.valueOf(chisl2)};
            String[] result = calculate1(massDig);

            try {
                if (Integer.parseInt(result[1]) < 1)
                    throw new Exception("в римской системе нет отрицательных чисел");
            } catch (Exception e) {
                e.printStackTrace();
            }
//            if (!result[0].equalsIgnoreCase("eror13")&&  (Integer.parseInt(result[1]) >= 1   )) System.out.println("Output:\n" + convertDig2Rom(result[1]));
            if (!result[0].equalsIgnoreCase("eror13")) System.out.println("Output:\n" + convertDig2Rom(result[1]));
        }
    }
    private static void arabcount(String[] mssOp, String[] massOrig) {
        String[] massDig;
        if (mssOp[0].equals("arab") && mssOp[1].equals("sign") && mssOp[2].equals("arab")) {
            massDig = massOrig;
            String[] result = calculate1(massDig);
            if (!result[0].equalsIgnoreCase("eror13")) System.out.println("Output:\n" + result[1]);
        }
    }

    private static String convertDig2Rom(String s) {
        String str;
        //TODO recode - результат в рим цифрах больше 20?
        String[] romeDig = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
        //TODO 0 в римском счете...
        str = romeDig[Integer.parseInt(s) - 1];
        return str;
    }
    private static int convertRom2Dig(String s) {
//        String[] romeDig = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] romeDig = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
        int ind = -1;
        for (int i = 0; i < romeDig.length; i++) {
            if (s.equalsIgnoreCase(romeDig[i])) {
                ind = i + 1;
            }
        }
        return ind;
    }

    private static void check1(String[] massOrig) {
        try {
            if (massOrig.length > 3)
                throw new Exception("  формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            if (massOrig.length < 3) throw new Exception("  строка не является математической операцией");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void check2(String[] mssOp) {
        try {
            if (mssOp[0].equals("arab") && mssOp[2].equals("rome") || mssOp[2].equals("arab") && mssOp[0].equals("rome"))
                throw new Exception(" используются одновременно разные системы счисления");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String checkNumber(String s) {
        boolean cifr1dig = true;
        boolean cifr1rome = false;
//        boolean cifr1eror = false;
        boolean sign = false;

        for (int i = 0; i < s.toCharArray().length; i++) {
            if (!Character.isDigit(s.charAt(i))) cifr1dig = false;
        }

        //TODO чек ввода римских чисел > 10  ?  или чек билеберды
        String[] romeDig = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};

        for (String value : romeDig) {
            if (s.equals(value)) {
                cifr1rome = true;
                break;
            }
        }

        String[] signStr = {"+", "-", "/", "*"};

        for (String value : signStr) {
            if (s.equals(value)) {
                sign = true;
                break;
            }
        }


        if (cifr1dig) return "arab";
        else if (cifr1rome) return "rome";
        else if (sign) return "sign";
        else {
            try {
                throw new Exception("eror 321 "); // если вывод 0 в римск цифр
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "eror";
        }
    }
    private static String check10(int cif1, int cif2) {
        String str0 = "no error";
        try {
            String str = "";

            if (cif1 > 10 && cif2 > 10) {
                str = " оба числа > 10";
                str0 = "eror226";
            } else if (cif1 > 10) {
                str = "1число > 10";
                str0 = "eror226";
            } else if (cif2 > 10) {
                str = "2число > 10";
                str0 = "eror226";
            }

            if (cif1 < 1 && cif2 < 1) {
                str = " оба числа < 1";
                str0 = "eror226";
            } else if (cif2 < 1) {
                str = "2число < 1";
                str0 = "eror226";
            } else if (cif1 < 1) {
                str = "1число < 1";
                str0 = "eror226";
            }

            if (cif1 < 1 || cif2 < 1 || cif2 > 10 || cif1 > 10) throw new Exception(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str0;
    }

    private static String[] calculate1(String[] inp) {
        int reslt;
        int inpCifr1 = Integer.parseInt(inp[0]);
        int inpCifr2 = Integer.parseInt(inp[2]);
        String[] str = new String[]{"eror13", "666"}; // если рисмские и поставить 0 - будет доп выбраывать что резт отрицат...

        String strCh = check10(inpCifr1, inpCifr2);
        if (strCh.equalsIgnoreCase("no error")) {

            if (inp[1].equalsIgnoreCase("+")) {
                reslt = sum(inpCifr1, inpCifr2);
                str[0] = "Sum is :";
                str[1] = String.valueOf(reslt);
            } else if (inp[1].equalsIgnoreCase("-")) {
                reslt = sub(inpCifr1, inpCifr2);
                str[0] = "Sub is :";
                str[1] = String.valueOf(reslt);
            } else if (inp[1].equalsIgnoreCase("*")) {
                reslt = multi(inpCifr1, inpCifr2);
                str[0] = "Multi is :";
                str[1] = String.valueOf(reslt);

            } else if (inp[1].equalsIgnoreCase("/")) {
                reslt = devI(inpCifr1, inpCifr2);
                str[0] = "devI is : ";
                str[1] = String.valueOf(reslt);
            }
        }
        return str;
    }

    private static int sum(int cifr1, int cifr2) {
        return cifr1 + cifr2;
    }
    private static int sub(int cifr1, int cifr2) {
        return cifr1 - cifr2;
    }
    private static int multi(int cifr1, int cifr2) {
        return cifr1 * cifr2;
    }
    private static int devI(int cifr1, int cifr2) {
        return cifr1 / cifr2;
    }

    private static String input() {

        System.out.println("Calculator is greeting you!");
        System.out.println("please, input expression: \nInput:");


        String str0 = "";

        try (BufferedReader bufI = new BufferedReader(new InputStreamReader(System.in))) {
            str0 = bufI.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return str0;


    }
}
