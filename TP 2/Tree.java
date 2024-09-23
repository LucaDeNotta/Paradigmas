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
        List aList = new ArrayList();
        dfsHelper( aList );
        return aList;
    }

    public void dfsHelper( List aList ) {
        aList.add( value );
        left.subTreeDFS( aList );
        right.subTreeDFS( aList );
    }

    public List bfs() {
        List aList = new ArrayList();
        Queue<Tree> aQueue = new ArrayDeque();
        aQueue.add( this );
        while ( !aQueue.isEmpty() ) {
            Tree actual = aQueue.poll();
            aList.add( actual.value );
            actual.left.subTreeBFS( aQueue );
            actual.right.subTreeBFS( aQueue );

        }
        return aList;
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
