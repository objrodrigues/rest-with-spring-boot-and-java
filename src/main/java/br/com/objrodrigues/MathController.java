package br.com.objrodrigues;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.objrodrigues.converters.NumberConverter;
import br.com.objrodrigues.exceptions.UnsupprotedMathOperationException;
import br.com.objrodrigues.math.SimpleMath;
import ch.qos.logback.classic.net.SimpleSSLSocketServer;

@RestController
public class MathController {

    private final SimpleMath math = new SimpleMath();
    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum (
        @PathVariable String numberOne,
        @PathVariable String numberTwo
    ) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupprotedMathOperationException("Please set a numeric value");
        } 
        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double subtraction (
        @PathVariable String numberOne,
        @PathVariable String numberTwo
    ) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupprotedMathOperationException("Please set a numeric value");
        } 
        return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping("/mult/{numberOne}/{numberTwo}")
    public Double multiplication (
        @PathVariable String numberOne,
        @PathVariable String numberTwo
    ) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupprotedMathOperationException("Please set a numeric value");
        } 
        return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double division (
        @PathVariable String numberOne,
        @PathVariable String numberTwo
    ) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupprotedMathOperationException("Please set a numeric value");
        } 
        return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public Double media (
        @PathVariable String numberOne,
        @PathVariable String numberTwo
    ) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupprotedMathOperationException("Please set a numeric value");
        } 
        return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping("/squ/{numberOne}")
    public Double square (
        @PathVariable String numberOne
    ) throws Exception {
        if (!NumberConverter.isNumeric(numberOne)) {
            throw new UnsupprotedMathOperationException("Please set a numeric value");
        } 
        return math.square(NumberConverter.convertToDouble(numberOne));
    }
}
