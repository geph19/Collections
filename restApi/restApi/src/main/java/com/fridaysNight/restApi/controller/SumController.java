package com.fridaysNight.restApi.controller;

import com.fridaysNight.restApi.model.SumRequest;
import com.fridaysNight.restApi.service.SumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class SumController {
    private final SumService sumService;

    public SumController(SumService sumService){
        this.sumService = sumService;
    }
    @GetMapping("/")
    public String welcome() {
        return "Hola mundo";
    }

    @PostMapping("/api/sum")
    public ResponseEntity<Object> sumNumbers(@RequestBody SumRequest request){
        List<Integer> numbers = request.getList();

        if (numbers == null || numbers.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La lista esta vacia");
        }

        try {
            int sum = sumService.sumPositiveNumbers(numbers);
            return ResponseEntity.ok(sum);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/largest")
    public ResponseEntity<Object> getLargestNumber(@RequestBody SumRequest numberList){
        List<Integer> numbers = numberList.getList();

        if (numbers == null || numbers.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La lista esta vacia");
        }
        try {
            int largestNumber = sumService.findLargestNumber(numberList.getList());
            return ResponseEntity.ok(Collections.singletonList(largestNumber));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/smallest")
    public ResponseEntity<Object> getSmallestNumber(@RequestBody SumRequest numberList){
        List<Integer> numbers = numberList.getList();

        if (numbers == null || numbers.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La lista esta vacia");
        }
        try {
            int smallestNumber = sumService.findSmallestNumber(numberList.getList());
            return ResponseEntity.ok(Collections.singletonList(smallestNumber));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/count")
    public ResponseEntity<Object> repeatedNumbers(@RequestBody SumRequest numberList){
        try {
            int repeatedNumber = sumService.repeatedNumber(numberList.getList());
            return ResponseEntity.ok(Collections.singletonList(repeatedNumber));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/countplus")
    public ResponseEntity<Object> repeatedNumbersPlus(@RequestBody SumRequest numberList) {
        try {
            int repeatedNumber = sumService.repeatedNumberPlus(numberList.getList());
            return ResponseEntity.ok(Collections.singletonList(repeatedNumber));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
