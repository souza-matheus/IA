package BG;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;
 
public class gulosa
{
    private PriorityQueue<Vertice> prioridade;
    private int valorHeuristica[];
    private int numeroNos;
 
    public static final int MAX_VALUE = 999;
 
    public gulosa(int numeroNos)
    {
        this.numeroNos = numeroNos;
        this.prioridade = new PriorityQueue<Vertice>(this.numeroNos,
        new Vertice());
    }
 
    public void buscaGulosa(int matriz[][], int[] valorHeuristica,int origem)
    {
        int avalNO;
        int destinoNO;
        int visitado[] = new int [numeroNos + 1];
        this.valorHeuristica = valorHeuristica;

        System.out.println("Ordem de visitação: ");
        prioridade.add(new Vertice(origem, this.valorHeuristica[origem]));
        visitado[origem] = 1;
        
        while (!prioridade.isEmpty())
        {
        	avalNO = getMenorHeuristica();
            destinoNO = 1;
 
            System.out.print(avalNO + "\t");			
            while (destinoNO <= numeroNos)
            {
                Vertice vertice = new Vertice(destinoNO,this.valorHeuristica[destinoNO]);
                if ((matriz[avalNO][destinoNO] != MAX_VALUE 			                                           
                      && avalNO != destinoNO)&& visitado[destinoNO] == 0)
                {
                    prioridade.add(vertice);
                    visitado[destinoNO] = 1;
                }
                destinoNO++;
            }
        }
    }

    public void buscaGulosaOD(int matriz[][], int[] valorHeuristica,int origem, int destino)
    {
        int avalNO, exit = 0;
        int destinoNO;
        int visitado[] = new int [numeroNos + 1];
        this.valorHeuristica = valorHeuristica;
 
        prioridade.add(new Vertice(origem, this.valorHeuristica[origem]));
        visitado[origem] = 1;
  
        System.out.println("\nSolução ótima: ");
        while (!prioridade.isEmpty())
        {

            if(exit==0){
        	avalNO = getMenorHeuristica();
            destinoNO = 1;
            if(destino != avalNO){
            	System.out.print(avalNO + "\t");
            }else{
            	exit = 1;
            	System.out.print(avalNO + "\t");
                break;	
            }
            while (destinoNO <= numeroNos)
            {
                Vertice vertice = new Vertice(destinoNO,this.valorHeuristica[destinoNO]);
                if ((matriz[avalNO][destinoNO] != MAX_VALUE 			                                           
                      && avalNO != destinoNO)&& visitado[destinoNO] == 0)
                {
                    prioridade.add(vertice);
                    visitado[destinoNO] = 1;
                }
                destinoNO++;
            }}
        }
    }
    
    private int getMenorHeuristica()
    {
        Vertice vertice = prioridade.remove();
        return vertice.no;
    }
 
    public static void main(String... arg)
    {
        int matriz[][];
        int numeroVertices;
        int origem = 0;
        int valorHeuristica[];
 
        Scanner scan = new Scanner(System.in);
        try
        {
            System.out.println("Entre com numero de vertices: ");
            numeroVertices = scan.nextInt();
            matriz = new int[numeroVertices + 1][numeroVertices + 1];
            valorHeuristica = new int[numeroVertices + 1];
 
            System.out.println("Entre com o peso dos vertices: ");
            for (int i = 1; i <= numeroVertices; i++)
            {
                for (int j = 1; j <= numeroVertices; j++)
                {
                    matriz[i][j] = scan.nextInt();
                    if (i == j)
                    {
                        matriz[i][j] = 0;
                        continue;
                    }
                    if (matriz[i][j] == 0)
                    {
                        matriz[i][j] = MAX_VALUE;
                    }
                }
            }
            for (int i = 1; i <= numeroVertices; i++)
            {
                for (int j = 1; j <= numeroVertices; j++)
                {
                    if (matriz[i][j] == 1 && matriz[j][i] == 0)
                    {
                        matriz[j][i] = 1;
                    }
                }
            }
 
            System.out.println("Entre com a heuristica: ");
            for (int vertice = 1; vertice <= numeroVertices; vertice++)
            {
                System.out.print(vertice + ".");
                valorHeuristica[vertice] = scan.nextInt();
                System.out.println();
            }
 
            System.out.println("Entre com a origem ");
            origem = scan.nextInt();
            System.out.println("Entre com o destino ");
            int destino = scan.nextInt();

            gulosa buscaGulosa = new gulosa(numeroVertices);
            buscaGulosa.buscaGulosa(matriz, valorHeuristica,origem);
            buscaGulosa.buscaGulosaOD(matriz, valorHeuristica,origem,destino);
            
       } catch (InputMismatchException inputMismatch)
       {
           System.out.println("Wrong Input Format");
       }
       scan.close();
   }
}
 
class Vertice implements Comparator<Vertice>
{
    public int valorHeuristica;
    public int no;
 
    public Vertice(int no, int valorHeuristica)
    {
        this.valorHeuristica = valorHeuristica;
        this.no = no;
    }
 
    public Vertice()
    {
 
    } 
 
    @Override 
    public int compare(Vertice vertice1, Vertice vertice2)
    {
        if (vertice1.valorHeuristica < vertice2.valorHeuristica)
            return -1;
        if (vertice1.valorHeuristica > vertice2.valorHeuristica)
            return 1;
        return 0;
    }
 
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vertice)
        {
            Vertice no = (Vertice) obj;
            if (this.no == no.no)
            {
                return true;
            }
        }
        return false;
    }
}