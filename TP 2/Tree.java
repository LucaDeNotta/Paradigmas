package tree;

import java.util.*;

public class Tree{
    public static String nadaALaDiestra = "Nada a la diestra!";
    public static String nadaALaSiniestra = "Nada a la siniestra!";

    private Arista left;
    private Arista right;
    private Object value;

    public Tree( Object a ) {
        left = new NotConnectedArista(nadaALaSiniestra);
        right = new NotConnectedArista(nadaALaDiestra);
        value = a;
    }

    public List dfs() {
        List list = new ArrayList();
        dfshelper(list);
//        Deque<Tree> stack = new ArrayDeque();
//        stack.push(this);
//        while (!stack.isEmpty()) {
//            Tree node = stack.pop();
//            list.add( node.carga() );
//            if (node.right != null) {
//            stack.push(node.right);
//            }
//            if (node.left != null) {
//            stack.push(node.left);
//            }
//        }
        return list;
    }

    public void dfshelper(List list) {
        list.add(value);
        left.nodedfs(list);
        right.nodedfs(list);
    }



    public List bfs() {
        List list = new ArrayList();
        Queue<Tree> queue = new ArrayDeque();
        bfshelper(list);
//        queue.add(this);
//        while (!queue.isEmpty()) {
//            Tree node = queue.remove();
//            list.add( node.carga() );
//            if (node.left != null) {
//                queue.add(node.left);
//            }
//            if (node.right != null) {
//                queue.add(node.right);
//            }
//        }
        return list;
    }

public void bfshelper(List list) {
        list.add(value);
}

    public Tree atLeft( Tree b ) {
        left = left.setTree(b);
        return this;
    }

    public Tree atRight( Tree b ) {
        right = right.setTree(b);
        return this;
    }
    public Object carga() {
        return value;
    }

    public Tree left() {
        return left.connectedTree();
    }

    public Tree right() {
        return right.connectedTree();
    }
}
