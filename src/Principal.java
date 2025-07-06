public class Principal {
    public static void main(String[] args) {
        ArvoreBalanceada arvore = new ArvoreAVL();

        arvore.limpar();

        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);
        arvore.inserir(25);
        arvore.inserir(28);
        arvore.inserir(27);
        arvore.inserir(5);

        arvore.remover(27);
        arvore.remover(25);

        arvore.buscar(28);
        arvore.buscar(100);

        arvore.imprimir();
    }
}
