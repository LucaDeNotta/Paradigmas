package tree;

import java.util.List;
import java.util.Queue;

public abstract class Arista {

    public abstract Tree connectedTree();

    public abstract void subTreeDFS( List aList );

    public abstract void subTreeBFS( Queue<Tree> aQueue );

}
