package Day2.Insuranceclaim;


import java.util.Arrays;

public class MergeClaimLists {

    static int[] merge(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {

            if (a[i] <= b[j]) {

                result[k] = a[i];
                i++;

            } else {

                result[k] = b[j];
                j++;
            }

            k++;
        }

        while (i < a.length) {

            result[k] = a[i];

            i++;
            k++;
        }

        while (j < b.length) {

            result[k] = b[j];

            j++;
            k++;
        }

        return result;
    }

    public static void main(String[] args) {

        int[] office1 = {10, 30, 50, 70};

        int[] office2 = {20, 40, 60, 80};

        System.out.println("REGIONAL CLAIM MERGING");

        System.out.println("Office 1: " + Arrays.toString(office1));

        System.out.println("Office 2: " + Arrays.toString(office2));

        int[] merged = merge(office1, office2);

        System.out.println("Merged list: " + Arrays.toString(merged));
    }
}

