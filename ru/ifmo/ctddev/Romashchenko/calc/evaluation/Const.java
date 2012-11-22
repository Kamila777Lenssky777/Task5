/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.ctddev.Romashchenko.calc.evaluation;

/**
 *
 * @author Андрей
 */
public class Const implements IEvaluation {

    private Integer constanta;
    private static final Integer PRIORITY = 0;

    public Const(Integer constanta) {
        this.constanta = constanta;
    }

    @Override
    public Integer evaluate(Integer num) {
        return constanta;
    }

    public static Boolean is(String s) {
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
