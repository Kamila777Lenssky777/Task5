package ru.ifmo.ctddev.Romashchenko.calc.reversePolishRecord;

import ru.ifmo.ctddev.Romashchenko.calc.exeptions.*;
import ru.ifmo.ctddev.Romashchenko.calc.evaluation.*;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Кристина
 */
//reversed polish record -- algorithm for parsing function
public class RPR {

    private Stack<String> outputline = new Stack<>();   //stack for reversed record

    //fills outputline
    public void fillOutputLine(List<String> list) throws ParserException {

        Stack<String> stack = new Stack<>();
        try {
            for (String e : list) {
                Integer pr = getPriority(e);
                if (pr == 1) {
                    outputline.push(e);
                } else if (pr == 4) {
                    stack.push(e);
                } else if (pr == 5) {
                    while (!stack.isEmpty() && getPriority(stack.peek()) != 4) {
                        outputline.push(stack.pop());
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && pr <= getPriority(stack.peek()) && getPriority(stack.peek()) != 4) {
                        outputline.push(stack.pop());
                    }
                    stack.push(e);
                }
            }
        } catch (Exception ex) {
            throw new ParserException("mistake in spliting into tokens");
        }

        while (!stack.isEmpty()) {
            outputline.push(stack.pop());
        }
    }

    //defines priority of the token
    private Integer getPriority(String ex) {
        if (ex.equals("*") || ex.equals("/")) {
            return 3;
        } else if (ex.equals("-") || ex.equals("+")) {
            return 2;
        } else if (ex.equals("(")) {
            return 4;
        } else if (ex.equals(")")) {
            return 5;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "RPR{" + "outputline=" + outputline + '}';
    }

    //calculates the function in the point num with filled before outputline
    public Integer calculation(Integer num) throws EvaluationException, ParserException {
        Stack<IEvaluation> stack = new Stack<>();
        for (String s : outputline) {
            Integer r = 0;
            if (Const.is(s)) {
                r = Integer.parseInt(s);
            } else if (Variable.is(s)) {
                r = new Variable(s).evaluate(num);
            } else {
                IEvaluation op1 = null;
                IEvaluation op2 = null;
                try {
                    op1 = stack.pop();
                    op2 = stack.pop();
                } catch (EmptyStackException ex) {
                    throw new ParserException("binary operators have been used incorrectly");
                }

                if (Minus.is(s)) {
                    r = -new Minus(op1, op2).evaluate(num);
                } else if (Plus.is(s)) {
                    r = new Plus(op1, op2).evaluate(num);
                } else if (Times.is(s)) {
                    try {
                        r = new Times(op2, op1).evaluate(num);
                    } catch (EvaluationException ex) {
                        ex.fillInStackTrace();
                        throw ex;
                    }
                } else if (Division.is(s)) {
                    try {
                        r = new Division(op2, op1).evaluate(num);
                    } catch (EvaluationException ex) {
                        ex.fillInStackTrace();
                        throw ex;
                    }

                } else {
                    throw new ParserException("unrecognized token");
                }
            }
            stack.push(new Const(r));
        }

        if (stack.isEmpty()
                || stack.size() > 1) {
            throw new ParserException("function has been entered incorrect!");
        }
        return stack.pop().evaluate(num);
    }
}
