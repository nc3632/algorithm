/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */

public class MedianTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if ((totalLength & 1) != 0) {
            return findKthSmallest(nums1, 0, nums2, 0, totalLength / 2 + 1);
        } else {
            return (findKthSmallest(nums1, 0, nums2, 0, totalLength / 2) + findKthSmallest(nums1, 0, nums2, 0,
                    totalLength / 2 + 1)) / 2.0;
        }
    }

    private int findKthSmallest(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (nums2.length - start2 < nums1.length - start1) {
            // nums1 has to be the short one, otherwise, mid2 might be out of bound below.
            return findKthSmallest(nums2, start2, nums1, start1, k);
        }

        if (start1 >= nums1.length) {
            return nums2[k + start2 - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int len1 = Math.min(k / 2, nums1.length - start1);
        int len2 = k - len1;
        int mid1 = start1 + len1 - 1;
        int mid2 = start2 + len2 - 1;
        if (nums1[mid1] == nums2[mid2]) {
            return nums1[mid1];
        } else if (nums1[mid1] < nums2[mid2]) {
            return findKthSmallest(nums1, mid1 + 1, nums2, start2, len2);
        } else {
            return findKthSmallest(nums1, start1, nums2, mid2 + 1, len1);
        }
    }

    public static void main(String[] args) {
        MedianTwoSortedArrays sol = new MedianTwoSortedArrays();

        int[] nums1 = {1};
        int[] nums2 = {2, 3, 4, 5, 6};
        System.out.println(sol.findMedianSortedArrays(nums1, nums2));

    }
}
