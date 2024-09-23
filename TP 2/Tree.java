package tree;

import java.util.*;

public class Tree{
    public static String nadaALaDiestra = "Nada a la diestra!";
    public static String nadaALaSiniestra = "Nada a la siniestra!";

    private Object value;
    private Arista left = new NotConnectedArista( nadaALaSiniestra );
    private Arista right = new NotConnectedArista( nadaALaDiestra );

    public Tree( Object aValue ) { value = aValue; }

    public List dfs() {
        List list = new ArrayList();
        dfsHelper( list );
        return list;
    }

    public void dfsHelper( List aList ) {
        list.add( value );
        left.subTreeDFS( aList );
        right.subTreeDFS( aList );
    }

    public List bfs() {
        List list = new ArrayList();
        Queue<Tree> queue = new ArrayDeque();
        queue.add( this );
        while ( !queue.isEmpty() ) {
            Tree actual = queue.poll();
            list.add( actual.value );
            actual.left.subTreeBFS( queue );
            actual.right.subTreeBFS( queue );

        }
        return list;
    }

    public Tree atLeft( Tree aTree ) {
        left = new ConnectedArista( aTree );
        return this;
    }

    public Tree atRight( Tree aTree ) {
        right = new ConnectedArista( aTree );
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
