import java.util.*;

/* 
public class Main {
    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 75000, 100000, 200000, 300000, 500000, 750000, 1000000, 2000000, 5000000, 10000000, 20000000};
        
        for (int size : sizes) {
            System.out.println("\nTamanho: " + size);
            
            testInsertion("Conjunto A (Crescente, poucos repetidos)", generateAscending(size, 0.1));
            testInsertion("Conjunto B (Decrescente, poucos repetidos)", generateDescending(size, 0.1));
            testInsertion("Conjunto C (Aleatório, poucos repetidos)", generateRandom(size, 0.1));
            testInsertion("Conjunto D (Aleatório, 90% repetidos)", generateRandom(size, 0.9));
        }
    }
    
    private static void testInsertion(String label, List<Integer> dataset) {
        VPtree tree = new VPtree();
        long totalElapsedTime = 0;
        int totalRotations = 0;
        
        for (int i = 0; i < 5; i++) {
            VPtree tempTree = new VPtree();
            long startTime = System.nanoTime();
            
            for (int num : dataset) {
                tempTree.insert(num);
            }
            
            long elapsedTime = System.nanoTime() - startTime;
            totalElapsedTime += elapsedTime;
            totalRotations += tempTree.rotationCount;
        }
        
        double averageTime = (totalElapsedTime / 5.0) / 1e9;
        double averageRotations = totalRotations / 5.0;
        System.out.println(label + " - Tempo médio de inserção: " + averageTime + " s");
        System.out.println(label + " - Média de rotações: " + averageRotations);
    }
    
    private static List<Integer> generateAscending(int size, double duplicateRatio) {
        List<Integer> list = new ArrayList<>();
        int uniqueCount = (int) (size * (1 - duplicateRatio));
        for (int i = 1; i <= uniqueCount; i++) {
            list.add(i);
        }
        while (list.size() < size) {
            list.add(list.get(new Random().nextInt(uniqueCount)));
        }
        return list;
    }
    
    private static List<Integer> generateDescending(int size, double duplicateRatio) {
        List<Integer> list = generateAscending(size, duplicateRatio);
        Collections.reverse(list);
        return list;
    }
    
    private static List<Integer> generateRandom(int size, double duplicateRatio) {
        List<Integer> list = generateAscending(size, duplicateRatio);
        Collections.shuffle(list);
        return list;
    }
}
*/


public class Main {
    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 75000, 100000, 200000, 300000, 500000, 750000, 1000000, 2000000, 5000000, 10000000, 20000000};
        
        for (int size : sizes) {
            System.out.println("\nTamanho: " + size);
            
            testInsertion("Conjunto A (Crescente, poucos repetidos)", generateAscending(size, 0.1));
            testInsertion("Conjunto B (Decrescente, poucos repetidos)", generateDescending(size, 0.1));
            testInsertion("Conjunto C (Aleatório, poucos repetidos)", generateRandom(size, 0.1));
            testInsertion("Conjunto D (Aleatório com 90% repetidos)", generateRandom(size, 0.9));
        }
    }
    
    private static void testInsertion(String label, List<Integer> dataset) {
        long totalElapsedTime = 0;
        int totalRotations = 0;
        
        for (int i = 0; i < 5; i++) {
            TreapNode root = null;
            TREAPtree.resetRotationCount();
            long startTime = System.nanoTime();
            
            for (int num : dataset) {
                root = TREAPtree.insertNode(root, num);
            }
            
            long elapsedTime = System.nanoTime() - startTime;
            totalElapsedTime += elapsedTime;
            totalRotations += TREAPtree.rotationCount;
        }
        
        double averageTime = (totalElapsedTime / 5.0) / 1e9;
        double averageRotations = totalRotations / 5.0;
        System.out.println(label + " - Tempo médio de inserção: " + averageTime + " s");
        System.out.println(label + " - Média de rotações: " + averageRotations);
    }
    
    private static List<Integer> generateAscending(int size, double duplicateRatio) {
        List<Integer> list = new ArrayList<>();
        int uniqueCount = (int) (size * (1 - duplicateRatio));
        for (int i = 1; i <= uniqueCount; i++) {
            list.add(i);
        }
        while (list.size() < size) {
            list.add(list.get(new Random().nextInt(uniqueCount)));
        }
        return list;
    }
    
    private static List<Integer> generateDescending(int size, double duplicateRatio) {
        List<Integer> list = generateAscending(size, duplicateRatio);
        Collections.reverse(list);
        return list;
    }
    
    private static List<Integer> generateRandom(int size, double duplicateRatio) {
        List<Integer> list = generateAscending(size, duplicateRatio);
        Collections.shuffle(list);
        return list;
    }
}

/* 
public class Main {
    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 75000, 100000, 200000, 300000, 500000, 750000, 1000000};
        
        for (int size : sizes) {
            System.out.println("\nTamanho: " + size);
            
            testInsertion("Conjunto A (Crescente, poucos repetidos)", generateAscending(size, 0.1));
            testInsertion("Conjunto B (Decrescente, poucos repetidos)", generateDescending(size, 0.1));
            testInsertion("Conjunto C (Aleatório, poucos repetidos)", generateRandom(size, 0.1));
            testInsertion("Conjunto D (Aleatório com 90% repetidos)", generateRandom(size, 0.9));
        }
    }
    
    private static void testInsertion(String label, List<Integer> dataset) {
        long totalElapsedTime = 0;
        
        for (int i = 0; i < 5; i++) {
            BinaryTree tree = new BinaryTree();
            long startTime = System.nanoTime();
            
            for (int num : dataset) {
                tree.insert(num);
            }
            
            long elapsedTime = System.nanoTime() - startTime;
            totalElapsedTime += elapsedTime;
        }
        
        double averageTime = (totalElapsedTime / 5.0) / 1e9;
        System.out.println(label + " - Tempo médio de inserção: " + averageTime + " s");
    }
    
    private static List<Integer> generateAscending(int size, double duplicateRatio) {
        List<Integer> list = new ArrayList<>();
        int uniqueCount = (int) (size * (1 - duplicateRatio));
        for (int i = 1; i <= uniqueCount; i++) {
            list.add(i);
        }
        while (list.size() < size) {
            list.add(list.get(new Random().nextInt(uniqueCount)));
        }
        return list;
    }
    
    private static List<Integer> generateDescending(int size, double duplicateRatio) {
        List<Integer> list = generateAscending(size, duplicateRatio);
        Collections.reverse(list);
        return list;
    }
    
    private static List<Integer> generateRandom(int size, double duplicateRatio) {
        List<Integer> list = generateAscending(size, duplicateRatio);
        Collections.shuffle(list);
        return list;
    }
}
*/