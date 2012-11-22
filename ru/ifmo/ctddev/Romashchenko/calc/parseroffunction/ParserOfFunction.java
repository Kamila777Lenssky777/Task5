/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.ctddev.Romashchenko.calc.parseroffunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.ifmo.ctddev.Romashchenko.calc.exeptions.EvaluationException;
import ru.ifmo.ctddev.Romashchenko.calc.exeptions.ParserException;
import ru.ifmo.ctddev.Romashchenko.calc.reversePolishRecord.RPR;

/**
 *
 * @author Андрей
 */
public class ParserOfFunction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EvaluationException {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        System.out.print("f(x) = ");
        if (scanner.hasNextLine()) {
            str = scanner.nextLine();
        }
        RPR rpr = new RPR();
        try {
            rpr.fillOutputLine(getTokens(str));
            tabulationOfFunction(rpr);
        } catch (ParserException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //tabulates function in the interval [0,10]
    public static void tabulationOfFunction(RPR rpr) throws ParserException {
        for (int i = 0; i <= 10; i++) {
            System.out.print(i + "\t");
            try {
                System.out.println(rpr.calculation(i));
            } catch (EvaluationException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //splits input string into tokens
    public static List<String> getTokens(String str) {
        String regexp = "\\d{1,}|\\+|\\-|\\/|\\*|\\(|\\)|\\w+";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(str);

        List<String> list = new ArrayList<>();
        while (m.find()) {
            if (m.group().length() != 0) {
                list.add(m.group().trim());
            }
        }
        return list;
    }
}
