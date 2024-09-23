package tree;

import java.util.List;
import java.util.Queue;

public class ConnectedArista extends Arista {
    private Tree connectedTree;

    public ConnectedArista( Tree tree ) {
        connectedTree = tree;
    }

    public Tree connectedTree() { return connectedTree; }

    public void subTreeDFS( List list ) { connectedTree.dfsHelper( list ); }

    public void subTreeBFS( Queue<Tree> queue ) {
        queue.add(connectedTree);
    }

}
