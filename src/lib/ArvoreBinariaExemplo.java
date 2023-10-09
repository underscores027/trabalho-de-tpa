/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.*;


/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinariaExemplo<T> implements IArvoreBinaria<T> {
    //public class ArvoreBinariaExemplo<T extends Comparable<T>> implements IArvoreBinaria<T> {

    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
    
    protected No<T> atual = null;
    private Stack<No<T>> pilha = new Stack<>();

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
        if (valor == null) {
            return null;
        } else {
            return pesquisarRecursivo(valor, raiz);
        }
    }

    private T pesquisarRecursivo(T valor, No<T> no) {
        if (no == null) {
            return null;
        }

        int comparacao = comparador.compare(valor, no.getValor());

        if (comparacao == 0) {
            return no.getValor();
        } else if (comparacao < 0) {
            return pesquisarRecursivo(valor, no.getFilhoEsquerda());
        } else {
            return pesquisarRecursivo(valor, no.getFilhoDireita());
        }
    }

    @Override
    public void remover(T valor) {
        /**
         * Método que retorna a altura da árvore
         * @return Retorna a altura da árvore. Árvores só com raiz tem altura zero(0). Se raiz for nula retorne -1.
         */
        this.raiz = removerRecursivo(this.raiz, valor);
    }

    private No<T> removerRecursivo(No<T> no, T valor) {
        if (no == null) {
            return no;
        }
        //primeira caso: nao tem nenhum nó na arvore

        if (comparador.compare(valor, no.getValor()) < 0) {
            no.setFilhoEsquerda(removerRecursivo(no.getFilhoEsquerda(), valor));
        } else if (comparador.compare(valor, no.getValor()) > 0) {
            no.setFilhoDireita(removerRecursivo(no.getFilhoDireita(), valor));
        } else {
            // Caso 2: Nó sem filhos ou com apenas um filho
            if (no.getFilhoEsquerda() == null) { //Acho que é no.getFilhoEsquerda
                return no.getFilhoDireita();
            } else if (no.getFilhoDireita() == null) { //Acho que é no.getFilhoDireita
                return no.getFilhoEsquerda();
            }

            // Caso 3: Nó com dois filhos - Pegando o mínimo do lado direito
            no.setValor(encontrarMinimo(no.getFilhoDireita()).getValor());
            no.setFilhoDireita(removerRecursivo(no.getFilhoDireita(), no.getValor()));
        }
        return no;
    }

    private No<T> encontrarMinimo(No<T> no) {
        No<T> atual = no;
        while (atual.getFilhoEsquerda() != null) {
            atual = atual.getFilhoEsquerda();
        }
        return atual;
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
        if(atual == null){
            atual = raiz;
        }
        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual); //Adicionando o valor atual na pilha
                atual = atual.getFilhoEsquerda(); //Pegando todos os filhos a esquerda
            }

            atual = pilha.pop(); //desempilhando o último valor da esquerda e igualando ao atual
            T valor = atual.getValor(); //iguala o valor ao ultimo pego
            atual = atual.getFilhoDireita(); //pega o nó a direita da subarvore atual

            return valor; //retorna o valor
        }

        return null;
    }


    @Override
    public void reiniciarNavegacao(){
        pilha.clear(); //esvazia a pilha
        atual = null; //retorna o valor do no atual para nulo
    }
    
}
