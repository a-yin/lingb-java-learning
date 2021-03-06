package com.lingb.mystudy.datastructure.day02;

/**
 * 内部排序算法
 *
 * Created by lingb on 2018/8/21
 */
public class Sort {


    /**
     * 验证 ++i 与 i++
     */
    public static void autoIncr() {
        int i = 0;
        int result = 0;
        // 简单理解，吃饭后干活，即先自增后赋值
        result = ++i;
        System.out.println("先自增-------------");
        System.out.print("i=" + i + "，");
        System.out.println("result=" + result);

        // 干活后吃饭，即先赋值后自增
        result = i++;
        System.out.println("后自增-------------");
        System.out.print("i=" + i + "，");
        System.out.println("result=" + result);
    }


    /**
     * Shell排序，基于直接插入排序
     * 1、初始化间隔（增量）h
     * 2、计算最大间隔 h = h * 3 + 1   1，4，13，40，...
     * 3、while循环，直接插入排序算法，h递减
     * 4、重复3步，直到h = 1（即排序完成）
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {

        System.out.println("shell排序----------");

        // 初始化一个间隔（增量）
        int h = 1;
        // 计算最大间隔（增量）
        while (h < arr.length - 1) {
            h = h * 3 + 1;
        }

//        while (h > 0) {
//            //进行插入排序
//            int tmp = 0;
//            for (int i = h; i < arr.length; i++) {
//                tmp = arr[i];
//                int j = i;
//                while (j > h - 1 && arr[j - h] >= tmp) {
//                    arr[j] = arr[j - h];
//                    j -= h;
//                }
//                arr[j] = tmp;
//            }
//            // 减小间隔（增量）
//            h = (h - 1) / 3;
//        }


        // 这段比较好理解
        // 当 h = 1, 就是直接插入排序
        while (h > 0) {
            int insertNum;

            for (int i = h; i < arr.length; i++) {
                insertNum = arr[i];
                int j = i - h;
                // 从后往前循环，将大于insertNum的数向后移动（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面)
                while (j >= 0 && arr[j] >= insertNum) {
                    // 向后移
                    arr[j + h] = arr[j];
                    j -= h;
                }

                // 找到位置，插入当前元素
                arr[j + h] = insertNum;
            }

            // 减小间隔（增量）
            h = (h - 1) / 3;
        }
    }


    /**
     * 直接插入排序
     * 1、首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，1个数的那次不用插入
     * 2、设定插入数和得到已经排好序列的最后一个数的位数。insertNum和j=i-1
     * 3、从最后一个数开始向前循环，如果插入数小于当前数，就将当前数向后移动一位
     * 4、将当前数放到已找到位置，即j+1
     * 5、重复2、3、4步，直到循环次数完毕（即排序完成）
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        System.out.println("直接插入排序---------");

//        // 要插入的元素
//        int insertNum;
//        // 1个数的那次不用插入，所以从1开始
//        for (int i = 1; i < arr.length; i++) {
//            insertNum = arr[i];
//            int j = i;
//            // 从后往前循环，将大于insertNum的数向后移动（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面)
//            while (j > 0 && arr[j - 1] >= insertNum) {
//                // 向后移
//                arr[j] = arr[j - 1];
//                j--;
//            }
//
//            // 找到位置，插入当前元素
//            arr[j] = insertNum;
//        }


        // 这段比较好理解
        // 要插入的元素
        int insertNum;
        // 因为第一次不用，所以从1开始
        for (int i = 1; i < arr.length; i++) {
            insertNum = arr[i];
            int j = i - 1;
            // 从后往前循环，将大于insertNum的数向后移动（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面)
            while (j >= 0 && arr[j] >= insertNum) {
                // 向后移
                arr[j + 1] = arr[j];
                j--;
            }

            // 找到位置，插入当前元素
            arr[j + 1] = insertNum;
        }
    }


    /**
     * 堆排序
     * 1、将一个待排序的数据序列，创建成堆（大堆->升序 小堆->降序）
     * 2、根据选择排序的思路，把根结点交换到后面，重复以此完成排序
     *
     */

    /**
     * 1、创建大堆
     *
     * @param arr   待排序的数据序列
     * @param size  待排序的数据序列的大小
     * @param index 从哪开始
     */
    public static void maxHeap(long[] arr, int size, int index) {
        // 左子结点
        int left = 2 * index + 1;
        // 右子节点
        int right = 2 * index + 2;
        // 最大结点
        int max = index;

        // 跟左右子结点比对，找出最大结点
        // 下面max 不断递归会越界，所以left < size 和 right < size
        if (left < size && arr[left] > arr[max]) {
            max = (int) left;
        }
        if (right < size && arr[right] > arr[max]) {
            max = (int) right;
        }

        // 交换位置，创建大堆
        if (max != index) {
            long temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;

            // 交换位置以后，可能破坏之前排好的堆，所以之前排好的堆需要重新调整
            maxHeap(arr, size, max);
        }

    }

    /**
     * 2、堆排序
     *
     * @param arr
     */
    public static void heapSort(long [] arr) {
        // 开始位置是最后一个非叶子结点，即最后一个结点的父节点
        int start = (arr.length - 1) / 2;
        // 调整为大堆（从后向前）
        for (int i = start; i >= 0; i--) {
            maxHeap(arr, arr.length, i);
        }

        // 先把数组中的第0个和最后1个交换位置，再把前面的处理成大堆
        for (int i = arr.length - 1; i > 0; i--) {
            long temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeap(arr, i, 0);
        }

    }


    /**
     * 选择排序
     * 1、首先确定循环次数，并且记住当前数字和当前位置
     * 2、将当前位置后面所有的数与当前数字进行对比，小数赋值给tmp，并记住小数的位置
     * 3、比对完成后，将最小的值与第一个数的值交换
     * 4、重复2、3步，直到循环次数完毕（即排序完成）
     *
     * @param arr
     */
    public static void selectionSort(long[] arr) {
        System.out.println("选择排序---------");
        // 指向最小元素
        int k = 0;
        long tmp = 0;
        // 遍历的次数（跑多少趟）
        for (int i = 0; i < arr.length - 1; i++) {
            // 初始化，即默认指向当前循环index = i的元素（下标为i）
            k = i;
            // 遍历arr选择最小元素
            for (int j = i; j < arr.length; j++) {
                // 找到最小元素
                if (arr[j] < arr[k]) {
                    // 赋值指向最小元素
                    k = j;
                }
            }

            // 找到最小元素再交换（共arr.length次）
            tmp = arr[i];
            arr[i] = arr[k];
            arr[k] = tmp;
        }
    }


    /**
     * 快速排序
     * 1、设定关键字，划分数据
     * 2、实现快速排序
     */

    /**
     * 划分数据
     *
     * @param arr   数组
     * @param left  左数组
     * @param right 右数组
     * @param point 关键字
     * @return
     */
    public static int partition(long arr[],int left, int right, long point) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while(true) {
            // 先左边扫描
            // 循环,将比关键字小的留在左端
            while (leftPtr < rightPtr && arr[++leftPtr] < point) {};
            // 后右边扫描
            // 循环，将比关键字大的留在右端
            while(rightPtr > leftPtr && arr[--rightPtr] > point) {};

            if(leftPtr >= rightPtr) {
                break;

            } else {
                long tmp = arr[leftPtr];
                arr[leftPtr] =  arr[rightPtr];
                arr[rightPtr] = tmp;
            }
        }
        // 将关键字和当前leftPtr所指的这一个进行交换
        long tmp = arr[leftPtr];
        arr[leftPtr] =  arr[right];
        arr[right] = tmp;
        return leftPtr;
    }

    /**
     * 显示数据
     *
     * @param arr
     */
    public static void displayArr(long[] arr) {
        System.out.print("[");
        for(long num : arr) {
            System.out.print(num + " ");
        }
        System.out.print("]");
        System.out.println();
    }


    /**
     * 实现快速排序
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(long[] arr, int left, int right) {
        if(right - left <= 0) {
            return;

        } else {
            // 设置关键字（右边）
            long point = arr[right];
            // 获得切入点，同时对数组进行划分
            int partition = partition(arr, left, right, point);
            // 对左边的子数组进行快速排序
            quickSort(arr,left,partition - 1);
            // 对右边的子数组进行快速排序
            quickSort(arr,partition + 1, right);
        }
    }

    /**
     * 实现快速排序： 基准数归位的过程
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort1(long[] arr, int left, int right) {
        int i = 0;
        int j = 0;
        long refer = 0;
        long tmp = 0;

        if(left >= right) {
            return;
        }

        i = left;
        j = right;
        // refer存储基准数（初始化为数组第一个数，即左边）
        refer = arr[left];

        // 当i = j （即相遇）时跳出循环
        while (i < j) {

            // 注意找的顺序，右边扫描
            // 先从右往左找(找到小于基准数)
            while (i < j && refer <= arr[j]) {
                // 往左找
                j--;
            }

            // 再从左往右找（找到大于基准数）
            while (i < j && refer >= arr[i]) {
                // 往右找
                i++;
            }

            // 当i 与 j 没有相遇
            if (i < j) {
                // 交换两个数的位置
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

            }
        }

        // 此时i = j 相遇了
        // 最终将基准数的位置与i=j相遇的位置交换
        arr[left] = arr[i];
        // 基准数归位
        arr[i] = refer;

        // 递归排序，对左边的子数组进行快速排序
        quickSort1(arr,left, i - 1);
        // 对右边的子数组进行快速排序
        quickSort1(arr,i + 1, right);
    }


    /**
     * 实现快速排序： 基准数归位的过程
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort2(long[] arr, int left, int right) {

        int i = 0;
        int j = 0;
        long refer = 0;
        long tmp = 0;

        if(left >= right) {
            return;
        }

        i = left;
        j = right;
        // refer存储基准数（初始化为数组第一个数，即左边）
        refer = arr[left];

        // 当i = j （即相遇）时跳出循环
        while (i < j) {

            // 注意找的顺序，右边扫描
            // 先从右往左找(找到小于基准数)
            while (i < j && refer <= arr[j]) {
                // 往左找
                j--;
            }

            // 交换
            arr[i] = arr[j];

            // 再从左往右找（找到大于基准数）
            while (i < j && refer >= arr[i]) {
                // 往右找
                i++;
            }

            // 交换(包括了最终将基准数的位置与i=j相遇的位置交换)
            arr[j] = arr[i];

        }

        // 此时i = j 相遇了
        // 基准数归位
        arr[i] = refer;

        // 递归排序，对左边的子数组进行快速排序
        quickSort2(arr,left, i - 1);
        // 对右边的子数组进行快速排序
        quickSort2(arr,i + 1, right);
    }


    /**
     * 冒泡排序
     * 1、设置循环次数
     * 2、设置开始比较的位数，和结束的位数
     * 3、两两比较，将最小的放到前面去
     * 4、重复2、3步，直到循环次数完毕（即排序完成）
     *
     * @param arr
     */
    public static void bubbleSort(long[] arr) {
        System.out.println("冒泡排序---------");
        long tmp = 0;
        // 遍历的次数（跑多少趟）
        for (int i = 0; i < arr.length - 1; i++) {
            // 两两比较
            for (int j = arr.length - 1; j > i; j--) {
                // 交换（次数无法确定）
                if (arr[j - 1] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }
}
