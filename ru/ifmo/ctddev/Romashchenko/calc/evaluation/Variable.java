/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.ctddev.Romashchenko.calc.evaluation;

/**
 *
 * @author Андрей
 */
public class Variable implements IEvaluation {
    
    private String variable;
    public static final Integer PRIORITY = 0;
    
    public Variable(String str) {
        this.variable = str;
    }
    
    @Override
    public Integer evaluate(Integer num) {
        return num;
    }
    
    public static Boolean is(String s) {
        return !(Division.is(s) || Times.is(s) || Minus.is(s) || Plus.is(s) || Const.is(s) || s.equals(" "));
    }
}
