//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package app;

import java.io.PrintStream;
import lib.ArvoreBinariaExemplo;
import lib.IArvoreBinaria;

public class AppRelatorioArvoreBinaria {
    public AppRelatorioArvoreBinaria() {
    }

    public static void main(String[] args) {
        GeradorDeArvores gerador = new GeradorDeArvores();
        ComparadorAlunoPorMatricula comparador = new ComparadorAlunoPorMatricula();
        IArvoreBinaria<Aluno> arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvoreDegenerada(100, arv);
        System.out.println("Árvore Degenerada Criada");
        PrintStream var10000 = System.out;
        int var10001 = arv.quantidadeNos();
        var10000.println("Quantidade de Nós: " + var10001 + " Altura: " + arv.altura());
        arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvoreDegenerada(200, arv);
        System.out.println("Árvore Degenerada Criada");
        var10000 = System.out;
        var10001 = arv.quantidadeNos();
        var10000.println("Quantidade de Nós: " + var10001 + " Altura: " + arv.altura());
        arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvoreDegenerada(1000, arv);
        System.out.println("Árvore Degenerada Criada");
        var10000 = System.out;
        var10001 = arv.quantidadeNos();
        var10000.println("Quantidade de Nós: " + var10001 + " Altura: " + arv.altura());
        arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 100, arv);
        System.out.println("Árvore Perfeitamente Balanceada Criada");
        var10000 = System.out;
        var10001 = arv.quantidadeNos();
        var10000.println("Quantidade de Nós: " + var10001 + " Altura: " + arv.altura());
        arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 200, arv);
        System.out.println("Árvore Perfeitamente Balanceada Criada");
        var10000 = System.out;
        var10001 = arv.quantidadeNos();
        var10000.println("Quantidade de Nós: " + var10001 + " Altura: " + arv.altura());
        arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 1000, arv);
        System.out.println("Árvore Perfeitamente Balanceada Criada");
        var10000 = System.out;
        var10001 = arv.quantidadeNos();
        var10000.println("Quantidade de Nós: " + var10001 + " Altura: " + arv.altura());
        arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 50000, arv);
        System.out.println("Árvore Perfeitamente Balanceada Criada");
        System.out.println("Altura: " + arv.altura());
        arv = new ArvoreBinariaExemplo(comparador);
        gerador.geraArvoreDegenerada(50000, arv);
        System.out.println("Árvore Degenerada Criada");
        System.out.println(" Altura: " + arv.altura());
    }
}
