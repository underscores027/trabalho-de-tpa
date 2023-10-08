/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinariaExemplo<T> implements IArvoreBinaria<T> {
    //public class ArvoreBinariaExemplo<T extends Comparable<T>> implements IArvoreBinaria<T> {

    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
    
    protected No<T> atual = null;
    private ArrayList<No<T>> pilhaNavegacao = null;

    public ArvoreBinariaExemplo(Comparator<T> comp) {
        comparador = comp;
    }

    public void adicionar(T novoValor) {
        raiz = adicionarRecursivamente(raiz, novoValor);
    }

    private No<T> adicionarRecursivamente(No<T> noAtual, T novoValor) {
        if (noAtual == null) {
            return new No<T>(novoValor);
        }

        // Comparar o novo valor com o valor do nó atual para determinar o
        // ramo onde o novo valor deve ser adicionado.
        int comparacao = comparador.compare(novoValor,noAtual.getValor());
        if (comparacao < 0) { //
            noAtual.setFilhoEsquerda(adicionarRecursivamente(noAtual.getFilhoEsquerda(), novoValor));
        }
        else if (comparacao > 0) {
            noAtual.setFilhoDireita(adicionarRecursivamente(noAtual.getFilhoDireita(), novoValor));
        }
        else {
            System.out.println("valor duplicado");
            return noAtual;
        }

        return noAtual;
    }
    @Override
    public T pesquisar(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remover(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
        return calcAltura(raiz);
    }

    private int calcAltura(No<T> no){
        if (no == null) {
            return -1;
        }

        if (no.getFilhoEsquerda() == null && no.getFilhoDireita() == null) {
            return 0;
        }

        int esquerda = calcAltura(no.getFilhoEsquerda());
        int direita = calcAltura(no.getFilhoDireita());

        return Math.max(esquerda, direita) + 1;
    }


    @Override
    public int quantidadeNos() {
        return quantidadeNos(raiz);
    }

    @Override
    public int quantidadeNos(No<T> noAtual) {
        if (noAtual == null){
            return 0;
        } else {
            return 1 + quantidadeNos(noAtual.getFilhoDireita()) + quantidadeNos(noAtual.getFilhoEsquerda());
        }
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null) {
            return "[]"; //caso de arvore vazia
        }

        StringBuilder result = new StringBuilder("[");
        Queue<No<T>> fila = new LinkedList<>();
        fila.offer(raiz); // Adiciona o nó raiz à fila.

        while (!fila.isEmpty()) {
            No<T> noAtual = fila.poll(); // Remove o nó da fila.
            result.append(noAtual.getValor().toString()).append(", \n"); // Adiciona o valor do nó à saída.

            if (noAtual.getFilhoEsquerda() != null) {
                fila.offer(noAtual.getFilhoEsquerda()); // Adiciona o filho esquerdo à fila.
            }

            if (noAtual.getFilhoDireita() != null) {
                fila.offer(noAtual.getFilhoDireita()); // Adiciona o filho direito à fila.
            }
        }

        // finalzinho da fila
        result.setLength(result.length() - 2);
        result.append("]");

        return result.toString();
    }
    
    @Override
    public String caminharEmOrdem() {
        if (raiz == null) {
            return "[]"; // Retorna uma string vazia se a árvore estiver vazia.
        }

        StringBuilder resultado = new StringBuilder("[");
        caminharEmOrdemRecursivo(raiz, resultado);
        resultado.setLength(resultado.length() - 2); // finalzinho da fila
        resultado.append("]"); //isso não é uma lista, stringbuilder usa .append

        return resultado.toString();
    }

    private void caminharEmOrdemRecursivo(No<T> no, StringBuilder resultado) {
        if (no == null) {
            return;
        }
        caminharEmOrdemRecursivo(no.getFilhoEsquerda(), resultado);

        resultado.append(no.getValor().toString()).append(", \n");

        caminharEmOrdemRecursivo(no.getFilhoDireita(), resultado);
    }
    
    
    @Override
    public T obterProximo(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void reiniciarNavegacao(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
