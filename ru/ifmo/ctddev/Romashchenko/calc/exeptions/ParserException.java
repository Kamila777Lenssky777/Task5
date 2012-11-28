/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.ctddev.Romashchenko.calc.exeptions;

/**
 *
 * @author Андрей
 */
public class ParserException extends Exception {

    public ParserException(String message) {
        super("ParserException : " + message);
    }
}
