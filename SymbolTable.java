public class SymbolTable<Key extends Comparable<Key>, Value> {
    private Node root;

    public SymbolTable() {}
    
    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    private Node put(Node x, Key k, Value v) {
        if (x == null) {
            return new Node(k, v);
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, k, v);
        }
        else if (cmp > 0) {
            x.right = put(x.right, k, v);
        }
        else {
            x.val = v;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key k) {
        return get(root, k);
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

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    public Key floor(Key k) {
        Node x = floor(root, k);
        if (x==null) {
            return null;
        }
        return x.key;
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

    public Key ceiling(Key k) {
        Node x = ceiling(root, k);
        if (x==null) {
            return null;
        } 
        return x.key;
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

    public Key select(int i) {
        if (select(root, i) == null) {
            return null;
        }
        else {
            return select(root, i).key;
        } 
    }

    private Node select(Node x, int i) {
        if (x==null) {
            return null;
        }
        int s = size(x.left);
        if (s > i) {  
            return select(x.left, i);
        }
        if (s < i) {
            return select(x.right, (i-s-1));
        }
        return x;
    }

    private int rank(Key k) {
        return rank(root, k);
    }

    private int rank(Node x, Key k) {
        if (x==null) {
            return 0;
        }
        int cmp = k.compareTo(x.key);
            if(cmp < 0) {
            return rank(x.left, k);
        }
        if (cmp > 0) {
            return size(x.left) + 1 + rank(x.right, k);
        }
        return size(x.left);
    }

    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node x) {
        if (x==null) {
            return null;
        }
        if (x.left==null) {
            return x.right;
        }
        x.left = delMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delMax() {
        root = delMax(root);
    }

    private Node delMax(Node x) {
        if(x==null) {
            return null;
        }
        if (x.right==null) {
            return x.left;
        }
        x.right = delMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key k) {
        root = delete(root, k);
    }

    private Node delete(Node x, Key k) {
        if(x==null) {
            return null;
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, k);
        }
        else if (cmp > 0) {
            x.right = delete(x.right, k);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node t = x;
            x = min(t.right);
            delMin(t.right);
            x.left = t.left;
            x.right = t.right;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size() {
        return size(root);
    }
 
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public boolean contains(Key k) {
        return contains(root, k);
    }

    private boolean contains(Node x, Key k) {
        if (x == null) {
            return false;
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            return contains(x.left, k);
        }
        if (cmp > 0) {
            return contains(x.right, k); 
        }
        return true;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size = 1;
        
        private Node(Key k, Value v) {
            key = k;
            val = v;
        }
    }

    public static void main(String[] args) {
        SymbolTable<String, Integer> table = new SymbolTable<String, Integer>();
        
        table.put("S", 1);
        table.put("E", 2);
        table.put("A", 3);
        table.put("R", 4);
        table.put("C", 5);
        table.put("H", 6);
        table.put("E", 7);
        table.put("X", 8);
        table.put("T", 9);

        System.out.println(table.min());
        System.out.println(table.max());

    }
}