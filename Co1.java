import java.util.*;

class AVLNode {
    long ts;
    String trackId;
    AVLNode left, right;
    int height = 1;

    AVLNode(long ts, String tid) {
        this.ts = ts;
        this.trackId = tid;
    }
}

public class SpotifyAVL {

    public static List<String> topKDescending(AVLNode root, int k) {
        List<String> result = new ArrayList<>(k);
        inorder(root, k, result);
        return result;
    }

    private static void inorder(AVLNode node,
                                int k,
                                List<String> result) {

        if (node == null || result.size() >= k)
            return;

        // Left subtree contains larger timestamps
        inorder(node.left, k, result);

        if (result.size() < k)
            result.add(node.trackId);

        if (result.size() >= k)
            return;

        inorder(node.right, k, result);
    }

    public static void main(String[] args) {
        System.out.println("Top-K traversal implementation ready.");
    }
}
