package tree;

import java.util.List;
import java.util.Queue;

public class NotConnectedArista extends Arista {
    private String errorNotConnected;

    NotConnectedArista(String Direccion) {
        errorNotConnected = Direccion;
    }

    public Arista setTree(Tree tree) {
        return new ConnectedArista(tree);
    }

    public Tree connectedTree() {
        throw new RuntimeException(errorNotConnected);
    }

    public void nodedfs(List list){}

    public void nodebfs(List list, Queue<Tree> queue){}
}
