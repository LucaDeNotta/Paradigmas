package tree;

import java.util.*;

public class Tree{
    public static String nadaALaDiestra = "Nada a la diestra!";
    public static String nadaALaSiniestra = "Nada a la siniestra!";

    private Tree left;
    private Tree right;
    private Object value;

    public Tree( Object a ) {
        left = null;
        right = null;
        value = a;
    }

    public List dfs() {
        List list = new ArrayList();
        Deque<Tree> stack = new ArrayDeque();
        stack.push(this);
        while (!stack.isEmpty()) {
            Tree node = stack.pop();
            list.add( node.carga() );
            if (node.right != null) {
            stack.push(node.right);
            }
            if (node.left != null) {
            stack.push(node.left);
            }
        }
        return list;
    }



    public List bfs() {
        Queue<Tree> queue = new ArrayDeque();
        List list = new ArrayList();
        queue.add(this);
        while (!queue.isEmpty()) {
            Tree node = queue.remove();
            list.add( node.carga() );
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return list;
    }

    public Tree atLeft( Tree b ) {
        left = b;
        return this;
    }

    public Tree atRight( Tree b ) {
        right = b;
        return this;
    }
    public Object carga() {
        return value;
    }

    public Tree left() {
        if (left == null) {
            throw new RuntimeException(nadaALaSiniestra);
        }
        return left;
    }

    public Tree right() {
        if (right == null) {
            throw new RuntimeException(nadaALaDiestra);
        }
        return right;
    }
}
