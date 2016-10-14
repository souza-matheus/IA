package CU;

import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author eduardofigueiredo
 */
class No implements Comparator<No>{
    public int no;
    public int custo;
 
    public No(){
 
    }
 
    public No(int no, int custo){
        this.no = no;
        this.custo = custo;
    }
 
    @Override
    public int compare(No no1, No no2){
        if (no1.custo < no2.custo)
            return -1;
        if (no1.custo > no2.custo)
            return 1;
        if (no1.no < no2.no)
            return -1;
        return 0;
    }
 
    @Override
    public boolean equals(Object obj){
        if (obj instanceof No){
            No no = (No) obj;
            if (this.no == no.no){
                return true;
            }
        }
        return false;
    }
}

public class buscaUniforme {
    private PriorityQueue<No> filaPrioridade;
    private Set<Integer> settled;
    private int distancias[];
    private int numerodeNos;
    private int matrizDeAjacencia[][];
    private LinkedList<Integer> caminho;
    private int[] parente;
    private int inicial, destino;
    public static final int MAX_VALUE = 999; 
 
    public buscaUniforme(int numerodeNos){
        this.numerodeNos = numerodeNos;
        this.settled = new HashSet<Integer>();
        this.filaPrioridade = new PriorityQueue<>(numerodeNos, new No());
        this.distancias = new int[numerodeNos + 1];
        this.caminho = new LinkedList<Integer>();
        this.matrizDeAjacencia = new int[numerodeNos + 1][numerodeNos + 1]; 
        this.parente = new int[numerodeNos + 1];
    }
 
    public int buscaUniforme(int matrizDeAjacencia[][], int inicial, int destino){
        int avaliadoNO;
        this.inicial = inicial;
        this.destino = destino;
 
        for (int i = 1; i <= numerodeNos; i++){
            distancias[i] = MAX_VALUE;
        }
 
        for (int inicialvertex = 1; inicialvertex <= numerodeNos; inicialvertex++){
            for (int destinovertex = 1; destinovertex <= numerodeNos; destinovertex++){
                this.matrizDeAjacencia[inicialvertex][destinovertex] = matrizDeAjacencia[inicialvertex][destinovertex];
	    }
        }
 
        filaPrioridade.add(new No(inicial, 0));
        distancias[inicial] = 0;
 
        while (!filaPrioridade.isEmpty()){
            avaliadoNO = getMenorDistancia();
            //System.out.println("The eval No is " + avaliadoNO);
            if (avaliadoNO == destino){
                return distancias[destino];
            } 
            settled.add(avaliadoNO);
            adicionarFronteiras(avaliadoNO);
        }
        return distancias[destino];
    }
 
    private void adicionarFronteiras(int avaliadoNO){
        for (int destino = 1; destino <= numerodeNos; destino++){
            if (!settled.contains(destino)){
                if (matrizDeAjacencia[avaliadoNO][destino] != MAX_VALUE){
                    if (distancias[destino] > matrizDeAjacencia[avaliadoNO][destino]  
                                    + distancias[avaliadoNO]){
                        distancias[destino] = matrizDeAjacencia[avaliadoNO][destino]	
                                               + distancias[avaliadoNO]; 				 		
                        parente[destino] = avaliadoNO;
                    }
 
                    No no = new No(destino, distancias[destino]);
                    if (filaPrioridade.contains(no)){
                        filaPrioridade.remove(no);
                    }
                    filaPrioridade.add(no);
                }
            }
        }
    }
 
    private int getMenorDistancia(){
        No no = filaPrioridade.remove();
        return no.no;
    }
 
    public void imprimir(){
        caminho.add(destino);
        boolean procurar = false;
        int vertice = destino;
        while (!procurar){
            if (vertice == inicial){
                procurar = true;
                continue;
            }
            caminho.add(parente[vertice]);
            vertice = parente[vertice];
        }
 
        System.out.println("O caminho entre " + inicial + " e " + destino+ " é ");
        Iterator<Integer> iterator = caminho.descendingIterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "\t");
        }
    }
    
    public static void main(String... arg){
        int matriz_adjacente[][];
        int numero_de_vertices;
        int inicial = 0;
        int destino = 0;
        int distancia;
        Scanner scan = new Scanner(System.in);
        try{
            System.out.println("Entre com número de vertices:");
            numero_de_vertices = scan.nextInt();
 
            matriz_adjacente = new int[numero_de_vertices + 1][numero_de_vertices + 1];
            System.out.println("Entre com a matriz de adjacencia");
            for (int i = 1; i <= numero_de_vertices; i++){
                for (int j = 1; j <= numero_de_vertices; j++){
                    matriz_adjacente[i][j] = scan.nextInt();
                    if (i == j){
                        matriz_adjacente[i][j] = 0;
                        continue;
                    }
                    if (matriz_adjacente[i][j] == 0){
                        matriz_adjacente[i][j] = MAX_VALUE;
                    }
                }
            }
 
            System.out.println("Entre com o ponto inicial");
            inicial = scan.nextInt();
 
            System.out.println("Entre com o ponto de destino");
            destino = scan.nextInt();
 
            buscaUniforme busca = new buscaUniforme(numero_de_vertices);
            distancia = busca.buscaUniforme(matriz_adjacente,inicial, destino);
            busca.imprimir();
 
            System.out.println("\nA distancia entre o ponto inicial " + inicial +
                          " e o destino " + destino + " é " + distancia);
 
        } catch (InputMismatchException inputMismatch){
            System.out.println("Formato errado");
        }
        scan.close();
    }
}

//matriz exemplo
/*
0 5 4 0 0 0 0 0
0 0 0 3 2 0 0 0
0 0 0 0 0 3 0 0
0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 3
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0

*/
