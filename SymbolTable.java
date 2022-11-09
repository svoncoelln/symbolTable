public class SymbolTable<Key extends Comparable<Key>, Value> {
    private Node root;
    
    
    public void put(Key k, Value v) {
        root = put(root, k, v);
        System.out.println("public root (key, val): ");
        System.out.println(root.key);
        System.out.println(root.val);
        System.out.println("public new (key, value): ");
        System.out.println(k);
        System.out.println(v);
    }

    public Value get(Key k) {
        return get(root, k);
    }

    public Value min() {
        return min(root).val;
    }

    public Value max() {
        return max(root).val;
    }

    public Value floor(Key k) {
        Node x = floor(root, k);
        if (x==null) {
            return null;
        }
        return x.val;
    }

    public Value ceiling(Key k) {
        Node x = ceiling(root, k);
        if (x==null) {
            return null;
        } 
        return x.val;
    }

    private Node put(Node x, Key k, Value v) {
        if (x == null) {
            System.out.println("private new node (key, value): ");
            System.out.println(k);
            System.out.println(v);
            return new Node(k, v);
        }
        int cmp = k.compareTo(x.key);
        System.out.println("cmp: " + cmp);
        x.size++;
        if (cmp < 0) {
            System.out.println("left");
            put(x.left, k, v);
        }
        if (cmp > 0) {
            System.out.println("right");
            put(x.right, k, v);
        }
        if(cmp == 0) {
            System.out.println("replace");
            x.val = v;
        }
        System.out.println("private return x (key, value): ");
        System.out.println(k);
        System.out.println(v);
        return x;
    }

    private Value get(Node x, Key k) {
        if (x == null) {
            return null;
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, k);
        }
        if (cmp > 0) {
            return get(x.right, k); 
        }
        return x.val;
    }

    private Node floor(Node x, Key k) {
        if (x == null) {
            return null;
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, k);
        }
        if (cmp > 0) {
            Node t = floor(x.right, k);
            if (t != null) {
                return t;
            }
        }
        return x;
    }

    private Node ceiling(Node x, Key k) {
        if (x == null) {
            return null;
        }
        int cmp = k.compareTo(x.key);
        if (cmp > 0) {
            return ceiling(x.right, k);
        }
        if (cmp < 0) {
            Node t = ceiling(x.left, k);
            if (t != null) {
                return t;
            }
        }
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    public String toString() {
        String result = "";
        result += root.key + " left " + root.left.key + " right " + root.right.key;
        return result; 
    }

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;
        
        private Node(Key k, Value v) {
            key = k;
            val = v;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        SymbolTable<String, Integer> table = new SymbolTable<>();
        
        table.put("S", 1);
        table.put("E", 2);
        System.out.println("testing");
        System.out.println(table.toString());
    }
}