package tree;

import java.util.List;
import java.util.Queue;

public class ConnectedArista extends Arista {
    private Tree tree;

    public ConnectedArista(Tree tree) {
        this.tree = tree;
    }

    public Arista setTree(Tree tree) {
        this.tree = tree;
        return this;
    }

    public Tree connectedTree() { return tree; }

    public void nodedfs(List list) { tree.dfshelper(list); }

    public void nodebfs(List list, Queue<Tree> queue) { tree.bfshelper(list); }

}
