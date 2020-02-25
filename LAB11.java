package lab11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class LAB11 {

    public static void main(String[] args) throws IOException {
        File A1 = new File("A1.txt");
        File A2 = new File("A2.txt");
        File A3 = new File("A3.txt");

        System.out.println("n: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger arr[] = new BigInteger[n];
        BigInteger arr2[] = new BigInteger[n];

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(A1))) { // Escribe el archivo A1
            bw.write("");
            for (int i = 0; i < n; i++) {
                BigInteger bigint = getRandomBigInteger(1000);
                bw.write("Linea [" + (i + 1) + "]: " + bigint.toString());
                arr[i] = bigint; // Añade big integer al vector arr
                arr2[i] = bigint; // Añade big integer al vector arr2
                bw.newLine();
            }
        }
        long t1, t2, tt;
        t1 = System.nanoTime();
        //Bubble(n, arr);
        Burbuja(arr, false, n);
        t2 = System.nanoTime();
        tt = t2 - t1;
        System.out.println("Tiempo BS (en nano-segundos): " + tt);

        t1 = System.nanoTime();
        mergeSort(arr2, n);
        t2 = System.nanoTime();
        tt = t2 - t1;
        System.out.println("Tiempo BS (en nano-segundos): " + tt);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(A2))) { //Guarda BS
            bw.write("");
            for (int i = 0; i < n; i++) {
                bw.write("Linea [" + (i + 1) + "]: " + arr[i].toString());
                bw.newLine();
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(A3))) { //Guarda BS
            bw.write("");
            for (int i = 0; i < n; i++) {
                bw.write("Linea [" + (i + 1) + "]: " + arr2[i].toString());
                bw.newLine();
            }
        }

    }

    /*public static void Bubble(int n, BigInteger arr[]) {

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) == 1) {
                    BigInteger temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }*/
    public static void Burbuja(BigInteger vec[], boolean band, int n) {
        int aux;
        if (!band) {
            band = true;
            for (int i = 0; i < n - 1; i++) {
                if (vec[i].compareTo(vec[i + 1]) == 1) {
                    BigInteger temp = vec[i];
                    vec[i] = vec[i + 1];
                    vec[i + 1] = temp;
                    band = false;
                }

            }
            Burbuja(vec, band, n);
        }
    }

    public static void mergeSort(BigInteger[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        BigInteger[] l = new BigInteger[mid];
        BigInteger[] r = new BigInteger[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(BigInteger[] a, BigInteger[] l, BigInteger[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].compareTo(r[j]) == 0 || l[i].compareTo(r[j]) == -1) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static BigInteger getRandomBigInteger(int n) {
        Random rand = new Random();
        String max = "";
        for (int i = 0; i < n; i++) {
            max = max + "9";
        }
        BigInteger upperLimit = new BigInteger(max);
        BigInteger result;
        do {
            result = new BigInteger(upperLimit.bitLength(), rand);
        } while (result.compareTo(upperLimit) >= 0);
        return result;
    }

}
