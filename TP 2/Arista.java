package tree;

import java.util.List;
import java.util.Queue;

public abstract class Arista {
    public abstract Arista setTree(Tree tree);

    public abstract Tree connectedTree();

    public abstract void nodedfs(List list);

    public abstract void nodebfs(List list, Queue<Tree> queue);
}
