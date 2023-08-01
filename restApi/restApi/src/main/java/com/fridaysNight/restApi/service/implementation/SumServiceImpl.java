package com.fridaysNight.restApi.service.implementation;

import com.fridaysNight.restApi.model.SumRequest;
import com.fridaysNight.restApi.service.SumService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SumServiceImpl implements SumService {
    @Override
    public int sumPositiveNumbers(List<Integer> numbers) {

        int sum = 0;
        boolean hasPositiveNumber = false;

        for (Integer number : numbers) {
            if (number < 0 ){
                throw new RuntimeException("No permite numero negativos");
            } else if (number > 0) {
                sum += number;
                hasPositiveNumber = true;

            }
        }
        if (!hasPositiveNumber){
            throw new RuntimeException("La lista no contiene numeros positivos");
        }
        return sum;


    }

    @Override
    public Integer findLargestNumber(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new RuntimeException("Lista Vacia");
        }
        Integer biggestNumber = Integer.MIN_VALUE;
        boolean hasPositiveNumber = false;

        for (Integer number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException("No permite numero negativos");
            } else if (number > 0 ) {
                hasPositiveNumber = true;
                biggestNumber = Math.max( biggestNumber, number);
            }
        }
        if (!hasPositiveNumber) {
            throw new IllegalArgumentException("No Contiene Numeros Positivos");
        }
        return biggestNumber;
    }

    @Override
    public Integer findSmallestNumber(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()){
            throw new RuntimeException("Lista vacia");
        }
        Integer smallestNumber = Integer.MAX_VALUE;
        boolean hasPositiveNumber = false;

        for (Integer number : numbers) {
            if (number < 0){
                throw new IllegalArgumentException("No permite numero Negativos");
            } else if (number > 0) {
                hasPositiveNumber = true;
                smallestNumber = Math.min(smallestNumber, number);
            }
        }
        if (!hasPositiveNumber) {
            throw new IllegalArgumentException("No tiene numeros positivos");
        }
        return smallestNumber;
    }

    @Override
    public Integer repeatedNumber(List<Integer> numbers) {
        Map<Integer, Integer> repeatMap = new HashMap<>();

        for (Integer number : numbers) {
            if (number < 0 ) {
                throw new IllegalArgumentException("La lista no puede contener numeros negativos");
            } else if (number > 0) {
                repeatMap.put(number, repeatMap.getOrDefault(number, 0) + 1);
            }
        }
        if (repeatMap.isEmpty()) {
            throw new IllegalArgumentException("La lista no contiene numeros");
        }

        int repeatedNumber = 0;
        int count = 0;

        for (Map.Entry<Integer,Integer> entry : repeatMap.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                repeatedNumber = entry.getKey();
            }
        }
        return repeatedNumber;
    }

    public Integer repeatedNumberFromRequest(SumRequest listRequest) {
        List<Integer> numbers = listRequest.getList();

        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Lista Vacia");
        }

        return repeatedNumber(numbers);
    }

    @Override
    public Integer repeatedNumberPlus(List<Integer> numbers) {
        Map<Integer,Integer> countMapPlus = new HashMap<>();

        for (Integer number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException("No debe contener numeros negativos");
            } else if (number > 0) {
                countMapPlus.put(number, countMapPlus.getOrDefault(number, 0) + 1);
            }
        }
        if (countMapPlus.isEmpty()) {
            throw new IllegalArgumentException("No contiene numeros positivos");
        }

        int mostRepeatedNumber = 0;
        int maxCount = 0;

        for (Map.Entry<Integer,Integer> entry : countMapPlus.entrySet()) {
            int currentCount = entry.getValue();
            int currentNumber = entry.getKey();

            if (currentCount > maxCount || (currentCount == maxCount && currentNumber > mostRepeatedNumber)){
                maxCount = currentCount;
                mostRepeatedNumber = currentNumber;
            }
        }
        return mostRepeatedNumber;
    }

    public int mostRepeatedNumberFromRequest(SumRequest listRequest) {
        List<Integer> numbers = listRequest.getList();

        if (numbers == null || numbers.isEmpty()){
            throw new IllegalArgumentException("La lista esta Vacia");
        }
        return repeatedNumberPlus(numbers);
    }
}
