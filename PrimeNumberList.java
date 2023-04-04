/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.primenumberlist;

/**
 *
 * @author nicke
 */
public class PrimeNumberList {
    
    public static void main(String[] args) {
        int n = 100;
        SinglyLinkedList<Integer> primeNumbers = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> threePrime = new SinglyLinkedList<>();
        
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primeNumbers.addLast(i);
            }
        }
        
        SinglyLinkedList.Node<Integer> currentNode = primeNumbers.getHead();
        while (currentNode != null) {
            int primeNumber = currentNode.getData();
            if (hasDigitThree(primeNumber)) {
                threePrime.addLast(primeNumber);
                primeNumbers.remove(primeNumber);
            }
            currentNode = currentNode.getNext();
        }
        
        int sum = 0;
        currentNode = threePrime.getHead();
        while (currentNode != null) {
            sum += currentNode.getData();
            currentNode = currentNode.getNext();
        }
        
        System.out.println("Prime numbers from 0 to " + n + ": " + primeNumbers.toString());
        System.out.println("Prime numbers with digit '3': " + threePrime.toString());
        System.out.println("Sum of prime numbers with digit '3': " + sum);
    }
    
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean hasDigitThree(int n) {
        while (n > 0) {
            if (n % 10 == 3) {
                return true;
            }
            n /= 10;
        }
        return false;
    }
    
}

class SinglyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.getData().equals(data)) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            return;
        }
        Node<T> currentNode = head;
        while (currentNode.getNext() != null) {
            if (currentNode.getNext().getData().equals(data)) {
                currentNode.setNext(currentNode.getNext().getNext());
                if (currentNode.getNext() == null) {
                    tail = currentNode;
                }
                return;
            }
            currentNode = currentNode.getNext();
        }
    }

    public Node<T> getHead() {
        return head;
    }

    @Override // this was listed as the "recommended tip in netbeans" for line 119.
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> currentNode = head;
        while (currentNode.getNext() != null) {
            sb.append(currentNode.getData()).append(", ");
            currentNode = currentNode.getNext();
        }
        sb.append(currentNode.getData()).append("]");
        return sb.toString();
    }

    static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}