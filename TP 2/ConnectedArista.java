package tree;

import java.util.List;
import java.util.Queue;

public class ConnectedArista extends Arista {
    private Tree tree;

    public ConnectedArista( Tree tree ) {
        this.tree = tree;
    }

    public Arista setTree( Tree tree ) {
        this.tree = tree;
        return this;
    }

    public Tree connectedTree() { return tree; }

    public void subTreeDFS( List list ) { tree.dfsHelper(list); }

    public void subTreeBFS( List list, Queue<Tree> queue ) {
        queue.add( tree );
    }

}
