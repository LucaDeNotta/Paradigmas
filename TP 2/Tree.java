package tree;

import java.util.*;

public class Tree{
    public static String nadaALaDiestra = "Nada a la diestra!";
    public static String nadaALaSiniestra = "Nada a la siniestra!";

    private Arista left;
    private Arista right;
    private Object value;

    public Tree( Object a ) {
        left = new NotConnectedArista( nadaALaSiniestra );
        right = new NotConnectedArista( nadaALaDiestra );
        value = a;
    }

    public List dfs() {
        List list = new ArrayList();
        dfsHelper( list );
        return list;
    }

    public void dfsHelper( List list ) {
        list.add( value );
        left.subTreeDFS( list );
        right.subTreeDFS( list );
    }

    public List bfs() {
        List list = new ArrayList();
        Queue<Tree> queue = new ArrayDeque();
        queue.add( this );
        while ( !queue.isEmpty() ) {
            Tree actual = queue.poll();
            list.add( actual.value );
            actual.left.subTreeBFS( list, queue );
            actual.right.subTreeBFS( list, queue );

        }
        return list;
    }

    public Tree atLeft( Tree b ) {
        left = left.setTree( b );
        return this;
    }

    public Tree atRight( Tree b ) {
        right = right.setTree( b );
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
