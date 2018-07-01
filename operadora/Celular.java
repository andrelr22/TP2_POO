// Universidade Federal de Minas Gerais
// Programacao Orientada a Objetos
// 2018-1
//
// Trabalho Pratico 2
// Sistema de Operadora de Celular
//
// Andre Lage
// Augusto Mafra

package operadora;

public abstract class Celular {
    static private int proximoNumero = 0;

    private String numero;

    public Celular() {
        // Formato 0 0000 0000 para os numeros de telefone
        System.out.println("Criando celular " + String.format("%09d", proximoNumero + 1));
        this.numero = String.format("%09d", proximoNumero++);
    }
}
