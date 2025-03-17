import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    
    private Node root;

    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(int data){
        root = new Node(data);
    }

    public void insert(int data){
        if(root == null){
            root = new Node(data); //root = 10
            return;
        }

        Queue<Node> queue = new LinkedList<>(); //10 20 30 40 50 60 70, root = 10
        queue.add(root); //queue = 10

        while(!queue.isEmpty()){
            
            Node current = queue.poll(); //queue = ; current = 10

            // Verifica se o valor já existe
            if(current.getData() == data){
                return;
            }

            // Insere à esquerda se possível
            if(current.getLeft() == null){
                current.setLeft(new Node(data));
                return;
            } else {
                queue.add(current.getLeft());
            }

            // Insere à direita se possível
            if(current.getRight() == null){
                current.setRight(new Node(data));
                return;
            } else {
                queue.add(current.getRight());
            }
        }
    }

    // Função para imprimir a árvore
    public void printTree() {
        printTree(root, 0);
    }

    // Função auxiliar recursiva para imprimir a árvore
    private void printTree(Node node, int level) {
        if (node == null) {
            return;
        }

        // Imprime o nó atual com indentação baseada no nível
        for (int i = 0; i < level; i++) {
            System.out.print("   "); 
        }
        System.out.println("└── " + node.getData());

        // Recursivamente imprime os filhos à esquerda e à direita
        printTree(node.getLeft(), level + 1);
        printTree(node.getRight(), level + 1);
    }
}