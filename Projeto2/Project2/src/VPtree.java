class VPNode {
    int data;
    VPNode left, right, parent;
    boolean isBlack;

    public VPNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.isBlack = false; // Sempre começa vermelho
    }
}

public class VPtree {
    public VPNode root;
    public int rotationCount = 0; // Contador de rotações
    private static final VPNode NIL = new VPNode(-1) {
        {
            isBlack = true;
            left = right = null;
        }
    };

    public VPtree() {
        root = NIL;
    }

    public void insert(int value) {
        VPNode newNode = new VPNode(value);
        newNode.isBlack = false; // Um nó quando é inserido, é sempre vermelho
        newNode.left = newNode.right = newNode.parent = NIL;
        
        if (root == NIL) {
            newNode.isBlack = true; //raíz é sempre preta
            root = newNode;
        } else {
            insert(root, newNode);
            fixTree(newNode);
        }
        root.parent = NIL;
    }

    private void insert(VPNode root, VPNode newNode) {
        if (root.data < newNode.data) {
            if (root.right == NIL) {
                root.right = newNode;
                newNode.parent = root;
            } else {
                insert(root.right, newNode);
            }
        } else if (root.data > newNode.data) {
            if (root.left == NIL) {
                root.left = newNode;
                newNode.parent = root;
            } else {
                insert(root.left, newNode);
            }
        }
    }

    private void fixTree(VPNode newNode) {
        while (newNode.parent != NIL && !newNode.parent.isBlack) {
            if (newNode.parent == newNode.parent.parent.left) {
                VPNode uncle = newNode.parent.parent.right;

                if (uncle != NIL && !uncle.isBlack) {
                    newNode.parent.isBlack = true;
                    uncle.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent;
                        rotateLeft(newNode);
                    }

                    newNode.parent.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    rotateRight(newNode.parent.parent);
                }
            } else {
                VPNode uncle = newNode.parent.parent.left;

                if (uncle != NIL && !uncle.isBlack) {
                    newNode.parent.isBlack = true;
                    uncle.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rotateRight(newNode);
                    }

                    newNode.parent.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    rotateLeft(newNode.parent.parent);
                }
            }

            if (newNode == root) {
                break;
            }
        }
        root.isBlack = true;
        root.parent = NIL; // Garante que a raiz não tenha pai incorreto
    }

    private void rotateLeft(VPNode x) {
        rotationCount++;
        VPNode y = x.right;
        x.right = y.left;

        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;

        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(VPNode x) {
        rotationCount++;
        VPNode y = x.left;
        x.left = y.right;

        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;

        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;
    }
}
