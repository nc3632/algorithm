import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular
 * region.
 *
 * Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is
 * represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 *
 * Example 1:
 *
 * rectangles = [
 *  [1,1,3,3],
 *  [3,1,4,2],
 *  [3,2,4,4],
 *  [1,3,2,4],
 *  [2,3,3,4]
 * ]
 *
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 *
 * Example 2:
 *
 * rectangles = [
 *  [1,1,2,3],
 *  [1,3,2,4],
 *  [3,1,4,2],
 *  [3,2,4,4]
 * ]
 *
 * Return false. Because there is a gap between the two rectangular regions.
 *
 * Example 3:
 *
 * rectangles = [
 *  [1,1,3,3],
 *  [3,1,4,2],
 *  [1,3,2,4],
 *  [3,2,4,4]
 * ]
 *
 * Return false. Because there is a gap in the top center.
 *
 * Example 4:
 *
 * rectangles = [
 *  [1,1,3,3],
 *  [3,1,4,2],
 *  [1,3,2,4],
 *  [2,2,4,4]
 * ]
 *
 * Return false. Because two of the rectangles overlap with each other.
 */

public class PerfectRectangle {
    private enum CornerType {
        LOWER_LEFT(1),
        LOWER_RIGHT(2),
        TOP_RIGHT(4),
        TOP_LEFT(8);

        private int val;

        CornerType(int val) {
            this.val = val;
        }

        int getVal() {
            return val;
        }
    }

    private static class Point {
        int x;
        int y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }

            Point point = (Point) o;

            if (x != point.x) {
                return false;
            }
            return y == point.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public boolean isRectangleCover(int[][] rectangles) {
        Point lowerLeftCorner = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point topRightCorner = new Point(0, 0);
        Map<Point, Integer> map = new HashMap<>();
        int area = 0;
        for (int[] rect : rectangles) {
            // Check how each of the corners been touched
            // A point can be touched by rectangles from different directions.
            // For example, a point could be the lower-left corner of one rectangle,
            // and it could be the lower-right corner of another rectangle.
            // However, it could NOT be the lower-left corner of two rectangles,
            // which means overlapping.
            if (overlap(map, new Point(rect[0], rect[1]), CornerType.LOWER_LEFT)) {
                return false;
            }
            if (overlap(map, new Point(rect[2], rect[1]), CornerType.LOWER_RIGHT)) {
                return false;
            }
            if (overlap(map, new Point(rect[2], rect[3]), CornerType.TOP_RIGHT)) {
                return false;
            }
            if (overlap(map, new Point(rect[0], rect[3]), CornerType.TOP_LEFT)) {
                return false;
            }

            // Adjust the covering rectangle
            lowerLeftCorner.x = Math.min(lowerLeftCorner.x, rect[0]);
            lowerLeftCorner.y = Math.min(lowerLeftCorner.y, rect[1]);
            topRightCorner.x = Math.max(topRightCorner.x, rect[2]);
            topRightCorner.y = Math.max(topRightCorner.y, rect[3]);

            // Adding up the areas of these small rectangles
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
        }

        // If there is such a covering rectangle, then the sum of areas of all rectangles
        // must be the same as the rectangle we obtained.
        if (area != (topRightCorner.x - lowerLeftCorner.x) * (topRightCorner.y - lowerLeftCorner.y)) {
            return false;
        }

        // The number of point that has only been touched once must be 4
        int count = 0;
        for (Map.Entry<Point, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1 || entry.getValue() == 2 || entry.getValue() == 4 || entry.getValue() == 8) {
                count++;
            }
        }

        return count == 4;
    }

    private boolean overlap(Map<Point, Integer> map, Point corner, CornerType cornerType) {
        int val = map.getOrDefault(corner, 0);
        if ((val & cornerType.getVal()) != 0) {
            return true;
        } else {
            val |= cornerType.getVal();
            map.put(corner, val);
            return false;
        }
    }

    public static void main(String[] args) {
        PerfectRectangle sol = new PerfectRectangle();
        int[][] rectangles = new int[][] {
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
                {1, 3, 2, 4},
                {2, 3, 3, 4}
        };
        System.out.println(sol.isRectangleCover(rectangles));

        rectangles = new int[][] {
                {1, 1, 2, 3},
                {1, 3, 2, 4},
                {3, 1, 4, 2},
                {3, 2, 4, 4}
        };
        System.out.println(sol.isRectangleCover(rectangles));

        rectangles = new int[][] {
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {1, 3, 2, 4},
                {3, 2, 4, 4}
        };
        System.out.println(sol.isRectangleCover(rectangles));

        rectangles = new int[][] {
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {1, 3, 2, 4},
                {2, 2, 4, 4}
        };
        System.out.println(sol.isRectangleCover(rectangles));
    }
}
