package com.fridaysNight.restApi.service;

import java.util.List;

public interface SumService {
    int sumPositiveNumbers(List<Integer> numbers);
    Integer findLargestNumber(List<Integer> numbers);
    Integer findSmallestNumber(List<Integer> numbers);
    Integer repeatedNumber(List<Integer> numbers);
    Integer repeatedNumberPlus(List<Integer> numbers);

}
