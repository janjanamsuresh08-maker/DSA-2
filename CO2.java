class PSegNode {

    long sum;
    PSegNode left;
    PSegNode right;

    PSegNode(long s, PSegNode l, PSegNode r) {
        sum = s;
        left = l;
        right = r;
    }
}

public class PersistentSegmentTree {

    static PSegNode build(int[] arr, int lo, int hi) {

        if (lo == hi)
            return new PSegNode(arr[lo], null, null);

        int mid = (lo + hi) / 2;

        PSegNode left = build(arr, lo, mid);
        PSegNode right = build(arr, mid + 1, hi);

        return new PSegNode(left.sum + right.sum, left, right);
    }

    static PSegNode pointUpdate(
            PSegNode node,
            int lo,
            int hi,
            int idx,
            long newVal) {

        // Leaf reached
        if (lo == hi) {
            return new PSegNode(newVal, null, null);
        }

        int mid = (lo + hi) / 2;

        PSegNode newLeft;
        PSegNode newRight;

        if (idx <= mid) {

            // Left side changes
            newLeft = pointUpdate(
                    node.left,
                    lo,
                    mid,
                    idx,
                    newVal);

            // Right side shared
            newRight = node.right;

        } else {

            // Left side shared
            newLeft = node.left;

            // Right side changes
            newRight = pointUpdate(
                    node.right,
                    mid + 1,
                    hi,
                    idx,
                    newVal);
        }

        return new PSegNode(
                newLeft.sum + newRight.sum,
                newLeft,
                newRight);
    }

    static long rangeSum(
            PSegNode node,
            int lo,
            int hi,
            int l,
            int r) {

        if (r < lo || hi < l)
            return 0;

        if (l <= lo && hi <= r)
            return node.sum;

        int mid = (lo + hi) / 2;

        return rangeSum(node.left, lo, mid, l, r)
                + rangeSum(node.right, mid + 1, hi, l, r);
    }

    public static void main(String[] args) {

        int[] stock = {
                12, 7, 25, 18,
                9, 14, 6, 30
        };

        PSegNode v0 = build(stock, 0, stock.length - 1);

        // Example:
        PSegNode v1 = pointUpdate(
                v0,
                0,
                stock.length - 1,
                2,
                75);

        System.out.println("Version created.");
    }
}
