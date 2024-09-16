package tree;

import estrut.Tree;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree implements Tree {
    private class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRec(root, valor);
    }

    private boolean buscaElementoRec(Node root, int valor) {
        if (root == null) {
            return false;
        }
        if (root.value == valor) {
            return true;
        }
        return valor < root.value ? buscaElementoRec(root.left, valor) : buscaElementoRec(root.right, valor);
    }

    @Override
    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia");
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia");
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElementoRec(root, valor);
    }

    private Node insereElementoRec(Node root, int valor) {
        if (root == null) {
            root = new Node(valor);
            return root;
        }
        if (valor < root.value) {
            root.left = insereElementoRec(root.left, valor);
        } else if (valor > root.value) {
            root.right = insereElementoRec(root.right, valor);
        }
        return root;
    }

    @Override
    public void remove(int valor) {
        root = removeRec(root, valor);
    }

    private Node removeRec(Node root, int valor) {
        if (root == null) {
            return root;
        }
        if (valor < root.value) {
            root.left = removeRec(root.left, valor);
        } else if (valor > root.value) {
            root.right = removeRec(root.right, valor);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.value = minValue(root.right);
            root.right = removeRec(root.right, root.value);
        }
        return root;
    }

    private int minValue(Node root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    @Override
    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdemRec(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void preOrdemRec(Node root, List<Integer> result) {
        if (root != null) {
            result.add(root.value); // Visita o nó raiz primeiro
            preOrdemRec(root.left, result); // Depois visita a subárvore esquerda
            preOrdemRec(root.right, result); // Finalmente, visita a subárvore direita
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        emOrdemRec(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void emOrdemRec(Node root, List<Integer> result) {
        if (root != null) {
            emOrdemRec(root.left, result);
            result.add(root.value);
            emOrdemRec(root.right, result);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        posOrdemRec(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void posOrdemRec(Node root, List<Integer> result) {
        if (root != null) {
            posOrdemRec(root.left, result);
            posOrdemRec(root.right, result);
            result.add(root.value);
        }
    }
}
