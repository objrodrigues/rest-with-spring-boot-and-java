package br.com.objrodrigues.math;

public class SimpleMath {

    public Double sum (
        Double numberOne,
        Double numberTwo
    ) {
        return numberOne + numberTwo;
    }

    public Double subtraction (
        Double numberOne,
        Double numberTwo
    ) {
        return numberOne - numberTwo;
    }

    public Double multiplication (
        Double numberOne,
        Double numberTwo
    ) {
        return numberOne * numberTwo;
    }

    public Double division (
        Double numberOne,
        Double numberTwo
    ) {
        return numberOne / numberTwo;
    }

    public Double mean (
        Double numberOne,
        Double numberTwo
    ) {
        return (numberOne + numberTwo)/2;
    }


    public Double square (
        Double numberOne
    ) {
        return Math.sqrt(numberOne);
    }
}
