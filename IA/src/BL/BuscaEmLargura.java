package BL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BuscaEmLargura {
    private Queue<No> espera;  
    static ArrayList<No> no = new ArrayList<No>();  
    
    static class No{  
        char data;  
        boolean visitado;
  
        No(char data){  
            this.data=data;  
        }  
    }  
  
    public BuscaEmLargura(){  
        espera = new LinkedList<No>();  
    }  
  
    public ArrayList<No> ProcuraVizinho(int matriz_adjacente[][],No x){  
        int noIndex=-1;  

        ArrayList<No> vizinhos = new ArrayList<No>();  
        for (int i = 0; i < no.size(); i++) {  
            if(no.get(i).equals(x)){  
                noIndex=i;  
                break;  
            }  
        }  
  
        if(noIndex!=-1){  
            for (int j = 0; j < matriz_adjacente[noIndex].length; j++) {  
                if(matriz_adjacente[noIndex][j] > 0){  
                    vizinhos.add(no.get(j));  
                }  
            }  
        }  
        return vizinhos;  
    }  
  
    public void buscarLargura(int matriz_adjacente[][], No no){  
        espera.add(no);  
        no.visitado=true;  
        while (!espera.isEmpty()){  
            No elemento = espera.remove();  
            System.out.print(elemento.data + "\t");  
            ArrayList<No> vizinhos = ProcuraVizinho(matriz_adjacente,elemento);  
            for (int i = 0; i < vizinhos.size(); i++) {  
                No n = vizinhos.get(i);  
                if(n!=null && !n.visitado){  
                    espera.add(n);  
                    n.visitado=true;  
                }  
            }  
        }  
    }
    
    public static void main(String[] args) {
        No no10 =   new No('A');  
        No no20 =   new No('B');  
        No no30 =   new No('C');  
        No no40 =   new No('D');  
        No no50 =   new No('E');  
        No no60 =   new No('F');  
        No no70 =   new No('G'); 
        No no80 =   new No('H');
  
        no.add(no10);  
        no.add(no20);  
        no.add(no30);  
        no.add(no40);  
        no.add(no50);  
        no.add(no60);  
        no.add(no70); 
        no.add(no80);
        
        int matriz_adjacente[][] = {  
            {0,5,4,0,0,0,0,0},  // No 1: 10
            {0,0,0,3,2,0,0,0},  // No 2: 20  
            {0,0,0,0,0,3,0,0},  // No 3: 30  
            {0,0,0,0,0,0,1,0},  // No 4: 40  
            {0,0,0,0,0,0,0,0},  // No 5: 50  
            {0,0,0,0,0,0,0,3},  // No 6: 60  
            {0,0,0,0,0,0,0,0},  // No 7: 70
            {0,0,0,0,0,0,0,0},  // No 8: 80
        };  
        System.out.println("Caminho percorrido no grafo ");  
        BuscaEmLargura largura = new BuscaEmLargura();  
        largura.buscarLargura(matriz_adjacente, no10);    
    } 
}