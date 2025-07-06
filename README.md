
# Árvore AVL em Java

Este projeto apresenta uma implementação de uma Árvore Binária de Busca Balanceada (AVL) em Java. O código é composto por uma interface, uma classe de nó e a implementação da árvore AVL, além de um programa principal para demonstração das operações.

## Estrutura dos Arquivos

```
ArvoreAVL/
├── ArvoreBalanceada.java   // Interface com métodos básicos
├── NoAVL.java              // Classe do nó da árvore
├── ArvoreAVL.java          // Implementação da árvore AVL
└── Principal.java          // Programa principal para testes
```

## Funcionalidades

- Inserção de valores com balanceamento automático (AVL)
- Remoção de valores com re-balanceamento
- Busca de elementos na árvore
- Impressão da árvore no console de forma visual
- Implementação de interface para padronização dos métodos

## Exemplo de Uso

```java
public class Principal {
    public static void main(String[] args) {
        ArvoreBalanceada arvore = new ArvoreAVL();

        arvore.limpar();
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);
        arvore.remover(20);
        arvore.buscar(30);
        arvore.imprimir();
    }
}
```

Exemplo de saída no console:

```
Inserindo 10
  -> Criando no' 10
Arvore (in-order):
\-- 10
---------------------

Inserindo 20
  -> Criando no' 20
Arvore (in-order):
    /-- 20
\-- 10
---------------------

Removendo 20
  Removendo no' 20
Arvore (in-order):
\-- 10
---------------------

Buscar 30: encontrado
```

## Compilação e Execução

Para compilar e executar o projeto:

1. Abra o terminal na pasta onde estão os arquivos.
2. Compile todos os arquivos `.java` com o comando:
   ```
   javac *.java
   ```
3. Execute o programa principal:
   ```
   java Principal
   ```

## Sobre

Projeto desenvolvido como exercício de estrutura de dados, demonstrando as operações e balanceamento de uma árvore AVL em Java.
