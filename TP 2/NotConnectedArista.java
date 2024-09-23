package tree;

import java.util.List;
import java.util.Queue;

public class NotConnectedArista extends Arista {
    private String errorNotConnected;

    public NotConnectedArista( String errorMessage ) {
        errorNotConnected = errorMessage;
    }

    public Tree connectedTree() {
        throw new RuntimeException( errorNotConnected );
    }

    public void subTreeDFS( List aList ){}

    public void subTreeBFS( Queue<Tree> aQueue ){}

}
