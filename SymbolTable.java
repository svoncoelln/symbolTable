public class SymbolTable<Key extends Comparable<Key>, Value> {
    private Node root = new Node(null, null, null, null);
    
    
    public void put(Key k, Value v) {
        root = root.put(root, k, v);
    }

    public Value get(Key k) {
        return (Value) root.get(root, k);
    }

    public Value min() {
        return (Value) root.min(root).val;
    }

    public Value max() {
        return (Value) root.max(root).val;
    }

    public Value floor(Key k) {
        Node x = root.floor(root, k);
        if (x==null) {
            return null;
        }
        return (Value) x.val;
    }

    public Value ceiling(Key k) {
        Node x = root.ceiling(root, k);
        if (x==null) {
            return null;
        } 
        return (Value) x.val;
    }

    private class Node<Key extends Comparable<Key>, Value> {
        private Key ky;
        private Value val;
        private Node left, right;
        
        private Node(Key k, Value v, Node l, Node r) {
            ky = k;
            val = v;
        }

        private Node put(Node x, Key k, Value v) {
            if (x == null) {
                return new Node(k, v, null, null);
            }
            int cmp = k.compareTo(x.ky);
            if (cmp < 0) {
                put(x.left, k, v);
            }
            if (cmp > 0) {
                put(x.right, k, v);
            }
            if(cmp == 0) {
                x.val = v;
            }
            return x;
        }

        private Value get(Node x, Key k) {
            if (x == null) {
                return null;
            }
            int cmp = k.compareTo(x.ky);
            if (cmp < 0) {
                return get(x.left, k);
            }
            if (cmp > 0) {
                return get(x.right, k); 
            }
            return (Value) x.val;
        }

        private Node floor(Node x, Key k) {
            if (x == null) {
                return null;
            }
            int cmp = k.compareTo(x.ky);
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
            int cmp = k.compareTo(x.ky);
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
    }

    public static void main(String[] args) {
        SymbolTable<String, Integer> table = new SymbolTable<>();
        
        table.put("S", 1);
        table.put("E", 2);
        table.put("X", 3);
        table.put("A", 4);
        table.put("R", 5);
        table.put("C", 6);
        table.put("H", 6);
        System.out.println(table.max());
        System.out.println(table.min());
    }
}