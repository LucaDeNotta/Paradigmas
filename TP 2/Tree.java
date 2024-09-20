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
        dfsHelper(list);
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

    public void dfsHelper(List list) {
        list.add(value);
        left.subTreeDFS(list);
        right.subTreeDFS(list);
    }

    public List bfs() {
        List list = new ArrayList();
        Queue<Tree> queue = new ArrayDeque();
        //bfsQueue( queue );
        queue.add( this );
        //bfsHelper( list, queue );
        while (!queue.isEmpty()) {
            Tree actual = queue.poll();
            list.add( actual.value );
            actual.left.subTreeBFS( list, queue );
            actual.right.subTreeBFS( list, queue );

        }
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


    //public void bfsHelper( List list, Queue<Tree> queue ) {
        //Tree actual = queue.poll();
            //list.add( actual.value );
            //actual.bfsQueue( list, queue );
            //actual.left.subTreeBFS( list, queue );
            //actual.right.subTreeBFS( list, queue );

    //}

    //public void bfsQueue( List list, Queue<Tree> queue ) {
        //left.subTreeBFS( list, queue );
        //right.subTreeBFS( list, queue );
        //bfsHelper( list, queue );
    //}

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
