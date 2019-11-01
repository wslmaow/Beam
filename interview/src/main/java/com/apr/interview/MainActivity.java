package com.apr.interview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //选择排序，第一次遍历选择最小值放到第一位，第二次遍历选择剩下数中最小值放到第二位
    int[] selectSort(int[] sourceData){
        for (int i = 0;i<sourceData.length;i++){
            int min = i;
            for (int j = i+1;j<sourceData.length;j++){
                if (sourceData[min]>sourceData[j]){
                    min = j;
                }
            }
            if (i!=min){
                int tmp = sourceData[i];
                sourceData[i] = sourceData[min];
                sourceData[min] = tmp;
            }
        }
        return sourceData;
    }
    //冒泡排序，两两比较、交换
    int[] bubbleSort(int[] sourceData){
        for (int i=0;i<sourceData.length;i++){
            for (int j=sourceData.length-1;j>i;j--){
                if (sourceData[j-1]>sourceData[j]){
                    int tmp = sourceData[j-1];
                    sourceData[j-1] = sourceData[j];
                    sourceData[j] = tmp;
                }
            }
        }
        return sourceData;
    }

    //插入排序，把目标数插入到已排好序的数组中
    int[] insertSort(int[] sourceData){
        for (int i = 1 ;i<sourceData.length;i++){
            int insertValue = sourceData[i];
            for (int j=i-1;j>=0;j--){
                if (insertValue < sourceData[j]){
                    sourceData[j+1] = sourceData[j];
                    if (j==0){
                        sourceData[0] = insertValue;
                    }
                }else {
                    sourceData[j+1] = insertValue;
                    break;
                }
            }
        }
        return sourceData;
    }

    //快速排序，从第一个数开始，先查找到它在数组中正确的序号，然后递归调用
    int[] quickSort(int[] sourceData,int low,int high){
        if (low<high){
            int index = getRightIndex(sourceData,low,high);
            quickSort(sourceData,0,index-1);
            quickSort(sourceData,index+1,high);
        }
        return sourceData;
    }

    int getRightIndex(int[] sourceData,int low,int high){
        int tmp = sourceData[low];
        while (low<high){
            while (low<high && tmp<sourceData[high]){
                high--;
            }
            sourceData[low] = sourceData[high];
            while (low<high && tmp>sourceData[low]){
                low++;
            }
            sourceData[high] = sourceData[low];
        }
        sourceData[low] = tmp;
        return low;
    }

    int binarySearch(int target, int[] sourceData){
        int low = 0;
        int high = sourceData.length;
        while (low<high){
            int min = (low+high)/2;
            if (target<sourceData[min]){
                high = min-1;
            }else if (target>sourceData[min]){
                low = min+1;
            }else {
                return min;
            }
        }
        return -1;
    }

    //递归调用 字符串反序
    String reverseStr(String target){
        if (target.length()>0){
            return reverseStr(target.substring(1))+target.charAt(0);
        }
        return "";
    }
}
