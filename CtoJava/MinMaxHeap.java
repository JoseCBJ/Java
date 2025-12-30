import java.util.Scanner;

public class MinMaxHeap{
    public static boolean  MaxHeap (int heap[], int n){
        int esq, dir;
        for(int i = 0; i<n/2;i++)
        {
            esq=2*i+1;
            dir=2*i+2;
            if((esq < n || dir < n) && (heap[i] < heap[esq] || heap[i] < heap[dir]))
                return false;   
        }
        return true; 
    }
    

    public static boolean MinHeap (int heap[], int n)
    {
        int dir, esq;
        for (int i = 0;i<n/2;i++)
        {
            esq=2*i+1;
            dir=2*i+2;
            if((esq < n || dir < n) && (heap[i] > heap[esq] || heap[i] > heap[dir]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int Entrada = entrada.nextInt();
        int[] heap = new int[Entrada];

        for(int i = 0;i<Entrada;i++)
        {
             heap[i]= entrada.nextInt();
        }

        if(MaxHeap(heap, Entrada))
            System.out.println("Max");
        else
            if(MinHeap(heap, Entrada))
                System.out.println("Min");
            else 
                System.out.println("Nada");
        entrada.close();
    }
}