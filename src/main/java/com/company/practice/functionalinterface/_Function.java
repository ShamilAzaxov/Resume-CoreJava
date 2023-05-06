package com.company.practice.functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class _Function {

    public static void main(String[] args) {

        int increment = increment(2);
        System.out.println(increment);

        Function<Integer, Integer> incrementFunction = number -> {
            return number + 1;
        };
        Function<Integer, Integer> multiplyBy10 = number -> number * 10;

        System.out.println("Functional way");
        Function<Integer, Integer> twoActs = incrementFunction.andThen(multiplyBy10);
        System.out.println(twoActs.apply(4));
        System.out.println("BiFunctional way");
        System.out.println(biFunction.apply(3,2));

        System.out.println("BiConsumer");
        biConsumer.accept("Maria", 29);

        System.out.println("Predicate");
        System.out.println(isNumBiggerThan5.test(10));

    }
      static   BiFunction<Integer, Integer, Integer> biFunction =
                (num1, num2)-> (num1 + 1) *num2;

    static BiConsumer<String, Integer> biConsumer = (name, age) ->
            System.out.println("Hello " + name + "\nAre you " + age + " years old?");


    static int increment(int number){
        return number + 1;
    }

   static Predicate<Integer> isNumBiggerThan5 = number -> number>5 ? true:false;
}
