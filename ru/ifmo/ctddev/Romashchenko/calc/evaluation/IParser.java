/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.ctddev.Romashchenko.calc.evaluation;

/**
 *
 * @author Андрей
 */
public interface IParser {

    IEvaluation getOperator(String str);
}
