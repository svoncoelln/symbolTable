public class SymbolTable<Key extends Comparable<Key>, Value> {
    private Node root;
    
    public void put(Key k, Value v) {
        root = put(root, k, v);
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

    public Key select(int i) {
        if (select(root, i) == null) {
            return null;
        }
        else {
            return select(root, i).key;
        } 
    }

    public int size() {
        return size(root);
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
    
    private int size(Node x) {
        if (x==null) {
            return 0;
        }
        return x.size;
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
    }
}