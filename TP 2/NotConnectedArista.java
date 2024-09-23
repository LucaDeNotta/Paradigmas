package tree;

import java.util.List;
import java.util.Queue;

public class NotConnectedArista extends Arista {
    private String errorNotConnected;

    public NotConnectedArista( String errorMessage ) {
        errorNotConnected = errorMessage;
    }

    public Arista connectTree(Tree tree ) {
        return new ConnectedArista( tree );
    }

    public Tree connectedTree() {
        throw new RuntimeException( errorNotConnected );
    }

    public void subTreeDFS( List list ){}

    public void subTreeBFS( Queue<Tree> queue ){}

}
