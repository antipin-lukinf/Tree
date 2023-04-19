package ru.gb.lesson4;

import java.util.*;
import java.util.function.Consumer;

public class Tree {



    class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void add(int value) {
        root = appendNode(root, value);
    }

    private Node appendNode(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (current.value > value) {
            current.left = appendNode(current.left, value);
        } else if (current.value < value) {
            current.right = appendNode(current.right, value);
        }
        return current;
    }

    public boolean contains(int value) {
        return findNode(root, value) != null;
    }

    private Node findNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value > value) {
            return findNode(current.left, value);
        } else if (current.value < value) {
            return findNode(current.right, value);
        }
        return current;
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }

    private Node removeNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value > value) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (current.value < value) {
            current.right = removeNode(current.right, value);
            return current;
        }

        // Нужно удалить текущий узел.
        // 3 случая:
        // 1. Нет дочерних узлов
        if (current.left == null && current.right == null) {
            return null;
        }

        // 2. Есть только 1 дочерний узел
        if (current.left == null) { //  && current.right != null
            return current.right;
        }
        if (current.right == null) { // && current.left != null
            return current.left;
        }

        // 3. Есть оба дочерних узла
        // Нужно найти минимальный элемент в правом поддереве
        Node smallestNodeOnTheRight = findFirst(current.right);
        int smallestValueOnTheRight = smallestNodeOnTheRight.value;
        // Вставить его значение в текущий узел
        current.value = smallestValueOnTheRight;
        // И удалить этот найденный минимальный узел у правого поддерева
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public int findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return findFirst(root).value;
    }

    private Node findFirst(Node current) {
        if (current.left != null) {
            return findFirst(current.left);
        }
        return current;
    }

    public void dfs(Consumer<Integer> valueConsumer) {
        dfsInternal(root, valueConsumer);
    }

    private void dfsInternal(Node current, Consumer<Integer> valueConsumer) {
        if (current != null) {
            dfsInternal(current.left, valueConsumer);
            valueConsumer.accept(current.value);
            dfsInternal(current.right, valueConsumer);
        }
    }

    public void bfs(Consumer<Integer> valueConsumer) {
        bfsInternal(valueConsumer);
    }

    private void bfsInternal(Consumer<Integer> valueConsumer) {
        if (root == null) {
            return;
        }

        // FIFO
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            valueConsumer.accept(node.value);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public int size() {
        final int[] counter = new int[1];
        // или dfs
        bfs(n -> counter[0]++);
        return counter[0];
    }

    public List<Integer> findLast(){
        List<Integer> resultMax = new ArrayList<>();
        findLast(root, resultMax);
        return resultMax;
    }
    // 1. Реализовать поиск максимального элемента в дереве. Метод назвать findLast
    private void findLast(Node current, List<Integer> resultMax){
        if (current != null){
            findLast(current.left, resultMax);
            resultMax.add(current.value);
            findLast(current.right, resultMax);
        }
    }
    // 2. Реализовать подсчет листьев. Листья - это такие узлы дерева, у которых нет дочерних элементов. Метод назвать getChildrenCount

    public int getChildrenCount(Node current, int value) {
        int i = 0;

        if (current.left == null && current.right != null){ //
            i++;

        }
        return i;
    }


}
