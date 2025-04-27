public class MyBST<K extends Comparable<K>, V> implements Iterable<MyBST<K, V>.Node> {
    private Node root;
    private int size;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.left = this.right = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }

    public MyBST() {
        this.root = null;
        this.size = 0;
    }

    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        boolean[] wasAdded = new boolean[1];
        root = put(root, key, val, wasAdded);
        if (wasAdded[0]) size++;
    }

    private Node put(Node node, K key, V val, boolean[] wasAdded) {
        if (node == null) {
            wasAdded[0] = true;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val, wasAdded);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val, wasAdded);
        } else {
            node.val = val;
            wasAdded[0] = false;
        }
        return node;
    }

    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        Node node = get(root, key);
        return node == null ? null : node.val;
    }

    private Node get(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node;
    }

    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (get(key) != null) {
            root = delete(root, key);
            size--;
        }
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public int size() {
        return size;
    }

    @Override
    public java.util.Iterator<Node> iterator() {
        java.util.List<Node> nodes = new java.util.ArrayList<>();
        inOrder(root, nodes);
        return nodes.iterator();
    }

    private void inOrder(Node node, java.util.List<Node> nodes) {
        if (node == null) return;
        inOrder(node.left, nodes);
        nodes.add(node);
        inOrder(node.right, nodes);
    }
}