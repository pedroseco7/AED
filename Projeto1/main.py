import time
import random


def findNumber1(arr):
    maximo = max(arr)
    minimo = min(arr)
    flag = False
    for i in range(minimo, maximo+1):
        for j in arr:
            if i == j:
                flag = True
                break
            flag = False

        if flag == False:
            return i

def findNumber2(arr):
    array_ord = sorted(arr) #custo nLog(n)
    for i in range(len(array_ord)-1):
        if (array_ord[i+1] - array_ord[i] > 1):
            return array_ord[i]+1
        
def findNumber3(arr):
    
    expected_sum = (len(arr) + 1) * (max(arr) + min(arr)) / 2
    actual_sum = sum(arr)
    return expected_sum - actual_sum

def main():
    
    array = list(range(1, 100001))

    missing_element = random.choice(array)
    array.remove(missing_element)
    
    start_time = time.time()
    result = findNumber1(array)
    end_time = time.time()
    
    print(f"Resultado Teste 1: {result}")
    print(f"Execução Tempo 1: {end_time - start_time} segundos")

    start_time = time.time()
    result = findNumber2(array)
    end_time = time.time()

    print(f"Resultado Teste 2: {result}")
    print(f"Execução Tempo 2: {end_time - start_time} segundos")

    start_time = time.time()
    result = findNumber3(array)
    end_time = time.time()

    print(f"Resultado Teste 3: {result}")
    print(f"Execução Tempo 3: {end_time - start_time} segundos")


            
if __name__ == "__main__":
    main()

