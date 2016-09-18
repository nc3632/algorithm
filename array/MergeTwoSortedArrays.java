/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements
 * from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */

public class MergeTwoSortedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos = m + n - 1;
        int idx1 = m - 1;
        int idx2 = n - 1;
        while (idx1 >= 0 && idx2 >= 0) {
            if (nums2[idx2] > nums1[idx1]) {
                nums1[pos--] = nums2[idx2--];
            } else if (nums2[idx2] < nums1[idx1]) {
                nums1[pos--] = nums1[idx1--];
            } else {
                nums1[pos--] = nums1[idx1--];
                nums1[pos--] = nums2[idx2--];
            }
        }

        while (idx2 >= 0) {
            nums1[pos--] = nums2[idx2--];
        }
    }
}
