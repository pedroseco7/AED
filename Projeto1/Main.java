import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static int findNumber1(int[] arr) {
        int maximo = Arrays.stream(arr).max().getAsInt();
        int minimo = Arrays.stream(arr).min().getAsInt();
        boolean flag = false;
        for (int i = minimo; i <= maximo; i++) {
            for (int j : arr) {
                if (i == j) {
                    flag = true;
                    break;
                }
                flag = false;
            }
            if (!flag) {
                return i;
            }
        }
        return -1; // Caso não encontre, retorna -1
    }

    public static int findNumber2(int[] arr) {
        Arrays.sort(arr); // custo nLog(n)
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] > 1) {
                return arr[i] + 1;
            }
        }
        return -1; // Caso não encontre, retorna -1
    }

    public static int findNumber3(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        long expectedSum = (long) (arr.length + 1) * (max + min) / 2;
        long actualSum = Arrays.stream(arr).sum();
        return (int) (expectedSum - actualSum);
    }

    public static double measureTime(Runnable func) {
        long startTime = System.nanoTime();
        func.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e9; // Converter para segundos
    }

    public static void main(String[] args) {
        int[] sizes = {1000000};
        int numTests = 10;
        ArrayList<Double> results1 = new ArrayList<>();
        ArrayList<Double> results2 = new ArrayList<>();
        ArrayList<Double> results3 = new ArrayList<>();

        for (int size : sizes) {
            System.out.printf("\nTamanho do Array: %d\n", size);
            for (int testNum = 1; testNum <= numTests; testNum++) {
                // Gerar o array e remover um elemento aleatório
                int[] array = new int[size - 1];
                Random random = new Random();
                int missingElement = random.nextInt(size) + 1;
                int index = 0;
                for (int i = 1; i <= size; i++) {
                    if (i != missingElement) {
                        array[index++] = i;
                    }
                }
                shuffleArray(array);

                // Teste 1 - findNumber1
                double time1 = measureTime(() -> findNumber1(array));
                results1.add(time1);
                System.out.printf("  Teste 1 - Medição %d: %.6f segundos\n", testNum, time1);

                // Teste 2 - findNumber2
                double time2 = measureTime(() -> findNumber2(array));
                results2.add(time2);
                System.out.printf("  Teste 2 - Medição %d: %.6f segundos\n", testNum, time2);

                // Teste 3 - findNumber3
                double time3 = measureTime(() -> findNumber3(array));
                results3.add(time3);
                System.out.printf("  Teste 3 - Medição %d: %.6f segundos\n", testNum, time3);
            }

            // Exibir resultados médios
            double avgTime1 = results1.subList((results1.size() - numTests), results1.size()).stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double avgTime2 = results2.subList((results2.size() - numTests), results2.size()).stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double avgTime3 = results3.subList((results3.size() - numTests), results3.size()).stream().mapToDouble(Double::doubleValue).average().orElse(0);

            System.out.printf("\nMédia dos tempos para o Array de %d elementos:\n", size);
            System.out.printf("  Teste 1 (findNumber1) - Tempo médio: %.6f segundos\n", avgTime1);
            System.out.printf("  Teste 2 (findNumber2) - Tempo médio: %.6f segundos\n", avgTime2);
            System.out.printf("  Teste 3 (findNumber3) - Tempo médio: %.6f segundos\n", avgTime3);
        }
    }

    // Método para embaralhar o array
    private static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}