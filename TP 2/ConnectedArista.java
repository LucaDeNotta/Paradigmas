package tree;

import java.util.List;
import java.util.Queue;

public class ConnectedArista extends Arista {
    private Tree connectedTree;

    public ConnectedArista( Tree aTree ) {
        connectedTree = tree;
    }

    public Tree connectedTree() { return connectedTree; }

    public void subTreeDFS( List aList ) { connectedTree.dfsHelper( aList ); }

    public void subTreeBFS( Queue<Tree> aQueue ) {
        queue.add( connectedTree );
    }

}
