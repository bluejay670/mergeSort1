/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort1;
import javax.swing.*;
/**
 *
 * @author guest123
 */
public class MergeSort1 {

    public static int loopCounter, comparisonCounter, shiftCounter;

    public static void main(String[] args) {
        loopCounter = 0;
        comparisonCounter = 0;
        shiftCounter = 0;
        int arraySize;
        String myInput = JOptionPane.showInputDialog("How many random numbers do you wish to generate?");
        arraySize = Integer.parseInt(myInput);
        
        int left = 0, right = arraySize-1;

        int nums[] = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            nums[i] = (int) (Math.random() * 1000);
        }

        //Display the unsorted list
        System.out.println("The unsorted list is:");
        for (int i = 0; i < arraySize; i++) {
            System.out.println(nums[i]);
        }
        System.out.println("\n------------------------------------");

        int target = Integer.parseInt(JOptionPane.showInputDialog("What number do you want to search for?"));

        long time = -1; //timer variable

        boolean isFound = false;

        System.out.println("Perfoming Linear Search (unsorted list)");
        time = System.currentTimeMillis();
        isFound = unsortedLinearSearch(nums, target);
        time = System.currentTimeMillis() - time;

        System.out.println("Search returned : " + isFound);
        System.out.println("Processing Time: " + time + "ms");
        System.out.println("loopCounter = " + loopCounter);
        System.out.println("comparisonCounter = " + comparisonCounter);
        System.out.println("\n------------------------------------");
        loopCounter = 0;
        comparisonCounter = 0;
                
        System.out.println("Performing Linear Search (sorted list)");
        mergeSort(nums, nums.length);
        time = System.currentTimeMillis();
        isFound = linearSearch(nums, target);
        time = System.currentTimeMillis() - time;
        
        

        System.out.println("Search returned : " + isFound);
        System.out.println("Processing Time: " + time + "ms");
        System.out.println("loopCounter = " + loopCounter);
        System.out.println("comparisonCounter = " + comparisonCounter);
        
        System.out.println("\nThe sorted list is:");
        for (int i = 0; i < arraySize; i++) {
            System.out.println(nums[i]);
        }
        
        System.out.println("\n------------------------------------");
        loopCounter = 0;
        comparisonCounter = 0;

        System.out.println("Performing Binary Search");
        time = System.currentTimeMillis();
        isFound = binarySearch(nums, 0, nums.length - 1, target);
        time = System.currentTimeMillis() - time;

        System.out.println("Search returned : " + isFound);
        System.out.println("Processing Time: " + time + "ms");
        System.out.println("loopCounter = " + loopCounter);
        System.out.println("comparisonCounter = " + comparisonCounter);
    }

    public static boolean unsortedLinearSearch(int myArray[], int v) {
        int k;
        for (k = 0; k < myArray.length; k++) {
            loopCounter++;
            comparisonCounter++;
            if (myArray[k] == v) {
                return true;
            }
        }
        return false;
    }

    public static boolean linearSearch(int myArray[], int v) {
        //The array must be sorted.
        int k;
        for (k = 0; k < myArray.length; k++) {
            loopCounter++;
            comparisonCounter++;
            if (myArray[k] == v) {
                return true;
            }
            comparisonCounter++;
            if (myArray[k] > v) {
                return false;
            }
        }
        return false;
    }

    public static boolean binarySearch(int myArray[], int left, int right, int v) {
        loopCounter++;
        int middle;
        comparisonCounter++;
        if (left > right) {
            return false;
        }
        middle = (left + right) / 2;
        comparisonCounter++;
        if (myArray[middle] == v) {
            return true;
        }
        comparisonCounter++;
        if (v < myArray[middle]) {
            return binarySearch(myArray, left, middle - 1, v);
        } else {
            return binarySearch(myArray, middle + 1, right, v);
        }
    }
    
    public static void mergeSort(int[] arr, int i){
      if (i < 2){
          return;
      }
      int mid = i/2;
      int[] left = new int[mid];
      int[] right = new int[i-mid];
      
      for (int j = 0; j < left.length; j++){
          left[j] = arr[j]; 
      }
      for (int k = mid; k < i; k++){
          right[k-mid] = arr[k]; 
      }
      
      mergeSort(left,mid);
      mergeSort(right,i-mid);
      merge(arr, left, right, mid, i-mid);
    }
    
    public static void merge (int[] a, int[] l, int[] r, int left, int right) {
       int i = 0, j = 0, k = 0;

       while (i < left && j < right){
            if (l[i] <= r[j]){
            a[k++] = l[i++];
            } 
            else {
            a[k++] = r[j++];
            }
       }

       while (i < left){
            a[k++] = l[i++];
       }       

       while (j < right){
        a[k++] = r[j++];
       }
 
    }
}