import java.util.Random;

class TreapNode{
    int data;
    int priority;
    TreapNode left,right;

    TreapNode(int data){
        this.data = data;
        this.priority = new Random().nextInt(1000000);
        this.left = this.right = null;
    }
}


public class TREAPtree {

    public static int rotationCount = 0; // Contador de rotações

    /* Function to left-rotate a given treap
 
          r                         R
         / \      Left Rotate      / \
        L   R        ———>         r   Y
           / \                   / \
          X   Y                 L   X
    */
    private static TreapNode rotateLeft(TreapNode root){
        rotationCount++;
        TreapNode R = root.right;
        TreapNode X = root.right.left;

        R.left = root;
        root.right = X;

        return R;
    }

    /* Function to right-rotate a given treap
 
            r                        L
           / \     Right Rotate     / \
          L   R        ———>        X   r
         / \                          / \
        X   Y                        Y   R
    */
    public static TreapNode rotateRight(TreapNode root){
        rotationCount++;
        TreapNode L = root.left;
        TreapNode Y = root.left.right;

        L.right = root;
        root.left = Y;

        return L;
    }

    public static TreapNode insertNode(TreapNode root, int data){
        if(root == null){
            return new TreapNode(data);
        }

        if(data < root.data){
            root.left = insertNode(root.left, data);

            // rotate right if heap property is violated
            if(root.left != null && root.left.priority > root.priority){
                root = rotateRight(root);
            }
        }
        else{
            root.right = insertNode(root.right, data);

            // rotate left if heap property is violated
            if(root.right != null && root.right.priority > root.priority){
                root = rotateLeft(root);
            }
        }

        return root;
    }

    public static void resetRotationCount() {
        rotationCount = 0;
    }

    // Recursive function to search for a key in a given treap
    public static boolean searchNode(TreapNode root, int key)
    {
        // if the key is not present in the tree
        if (root == null) {
            return false;
        }
 
        // if the key is found
        if (root.data == key) {
            return true;
        }
 
        // if the key is less than the root node, search in the left subtree
        if (key < root.data) {
            return searchNode(root.left, key);
        }
 
        // otherwise, search in the right subtree
        return searchNode(root.right, key);
    }
 
    // Recursive function to delete a key from a given treap
    public static TreapNode deleteNode(TreapNode root, int key)
    {
        // base case: the key is not found in the tree
        if (root == null) {
            return null;
        }
 
        // if the key is less than the root node, recur for the left subtree
        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        }
 
        // if the key is more than the root node, recur for the right subtree
        else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        }
 
        // if the key is found
        else {
            // Case 1: node to be deleted has no children (it is a leaf node)
            if (root.left == null && root.right == null)
            {
                // deallocate the memory and update root to null
                root = null;
            }
 
            // Case 2: node to be deleted has two children
            else if (root.left != null && root.right != null)
            {
                // if the left child has less priority than the right child
                if (root.left.priority < root.right.priority)
                {
                    // call `rotateLeft()` on the root
                    root = rotateLeft(root);
 
                    // recursively delete the left child
                    root.left = deleteNode(root.left, key);
                }
                else {
                    // call `rotateRight()` on the root
                    root = rotateRight(root);
 
                    // recursively delete the right child
                    root.right = deleteNode(root.right, key);
                }
            }
 
            // Case 3: node to be deleted has only one child
            else {
                // choose a child node
                TreapNode child = (root.left != null)? root.left: root.right;
                root = child;
            }
        }
 
        return root;
    }
}
