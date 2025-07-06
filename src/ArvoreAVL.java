public class ArvoreAVL implements ArvoreBalanceada {
    private NoAVL raiz;

    @Override
    public void limpar() {
        raiz = null;
        System.out.println("Arvore limpa.");
    }

    @Override
    public void inserir(int valor) {
        System.out.println("\nInserindo " + valor);
        raiz = inserirRec(raiz, valor);
        imprimir();
    }

    private NoAVL inserirRec(NoAVL no, int valor) {
        if (no == null) {
            System.out.println("  -> Criando no' " + valor);
            return new NoAVL(valor);
        }
        if (valor < no.valor) {
            no.esquerda = inserirRec(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRec(no.direita, valor);
        } else {
            System.out.println("  Valor ja existe: " + valor);
            return no;
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int balanceamento = fatorBalanceamento(no);

        // Balanceamento
        if (balanceamento > 1 && valor < no.esquerda.valor) {
            System.out.println("  Rotacao simples a direita em " + no.valor);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && valor > no.direita.valor) {
            System.out.println("  Rotacao simples a esquerda em " + no.valor);
            return rotacaoEsquerda(no);
        }
        if (balanceamento > 1 && valor > no.esquerda.valor) {
            System.out.println("  Rotacao dupla esquerda-direita em " + no.valor);
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && valor < no.direita.valor) {
            System.out.println("  Rotacao dupla direita-esquerda em " + no.valor);
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    @Override
    public void remover(int valor) {
        System.out.println("\nRemovendo " + valor);
        raiz = removerRec(raiz, valor);
        imprimir();
    }

    private NoAVL removerRec(NoAVL no, int valor) {
        if (no == null) {
            System.out.println("  Valor nao encontrado: " + valor);
            return null;
        }
        if (valor < no.valor) {
            no.esquerda = removerRec(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = removerRec(no.direita, valor);
        } else {
            System.out.println("  Removendo no' " + valor);
            if (no.esquerda == null || no.direita == null) {
                NoAVL temp = (no.esquerda != null) ? no.esquerda : no.direita;
                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoAVL temp = menorNo(no.direita);
                no.valor = temp.valor;
                no.direita = removerRec(no.direita, temp.valor);
            }
        }

        if (no == null)
            return null;

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int balanceamento = fatorBalanceamento(no);

        // Balanceamento
        if (balanceamento > 1 && fatorBalanceamento(no.esquerda) >= 0) {
            System.out.println("  Rotacao simples a direita em " + no.valor);
            return rotacaoDireita(no);
        }
        if (balanceamento > 1 && fatorBalanceamento(no.esquerda) < 0) {
            System.out.println("  Rotacao dupla esquerda-direita em " + no.valor);
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && fatorBalanceamento(no.direita) <= 0) {
            System.out.println("  Rotacao simples à esquerda em " + no.valor);
            return rotacaoEsquerda(no);
        }
        if (balanceamento < -1 && fatorBalanceamento(no.direita) > 0) {
            System.out.println("  Rotacao dupla direita-esquerda em " + no.valor);
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private NoAVL menorNo(NoAVL no) {
        NoAVL atual = no;
        while (atual.esquerda != null)
            atual = atual.esquerda;
        return atual;
    }

    @Override
    public boolean buscar(int valor) {
        boolean achou = buscarRec(raiz, valor);
        System.out.println("Buscar " + valor + ": " + (achou ? "encontrado" : "nao encontrado"));
        return achou;
    }

    private boolean buscarRec(NoAVL no, int valor) {
        if (no == null)
            return false;
        if (valor == no.valor)
            return true;
        if (valor < no.valor)
            return buscarRec(no.esquerda, valor);
        else
            return buscarRec(no.direita, valor);
    }

    private int altura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
    }

    private int fatorBalanceamento(NoAVL no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));
        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));
        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));

        return y;
    }

    @Override
    public void imprimir() {
        System.out.println("Arvore (in-order):");
        imprimirRec(raiz, "", true);
        System.out.println("---------------------");
    }

    private void imprimirRec(NoAVL no, String prefixo, boolean ehUltimo) {
    if (no != null) {
        imprimirRec(no.direita, prefixo + (ehUltimo ? "    " : "|   "), false);
        System.out.println(prefixo + (ehUltimo ? "\\-- " : "/-- ") + no.valor);
        imprimirRec(no.esquerda, prefixo + (ehUltimo ? "    " : "|   "), true);
    }
}
}
