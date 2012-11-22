/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.ctddev.Romashchenko.calc.evaluation;

import ru.ifmo.ctddev.Romashchenko.calc.exeptions.EvaluationException;

/**
 *
 * @author Андрей
 */
public class Plus implements IEvaluation {

    private IEvaluation operand1;
    private IEvaluation operand2;
    public static final Integer PRIORITY = 1;

    public Plus(IEvaluation e1, IEvaluation e2) {
        this.operand1 = e1;
        this.operand2 = e2;
    }

    @Override
    public Integer evaluate(Integer num) throws EvaluationException {
        long op1 = (long) operand1.evaluate(num);
        long op2 = (long) operand2.evaluate(num);
        long k = op1 + op2;
        Integer res = null;
        if (k > Integer.MAX_VALUE) {
            throw new EvaluationException("overflow");
        }
        return (int) k;
    }

    public static Boolean is(String s) {
        return s.equals("+");
    }
}
