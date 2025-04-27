public class BSTTest {
    public static void main(String[] args) {
        MyBST<Integer, String> tree = new MyBST<>();
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(1, "One");
        tree.put(4, "Four");

        System.out.println("Size: " + tree.size());
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}