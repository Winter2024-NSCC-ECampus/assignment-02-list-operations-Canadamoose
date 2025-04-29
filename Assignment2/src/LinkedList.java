public class LinkedList<T extends Comparable<T>> {

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head;

    public LinkedList() {
        this.head = null;
    }

    public void append(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void prepend(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
    }

    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public T head() {
        return (head != null) ? head.value : null;
    }

    public T tail() {
        if (head == null) {
            return null;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.value;
    }

    public T at(int index) {
        Node<T> current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.value;
            }
            count++;
            current = current.next;
        }
        return null;
    }

    public T pop() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            T value = head.value;
            head = null;
            return value;
        }
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        T value = current.next.value;
        current.next = null;
        return value;
    }

    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if ((value == null && current.value == null) ||
                    (value != null && value.equals(current.value))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Integer find(T value) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if ((value == null && current.value == null) ||
                    (value != null && value.equals(current.value))) {
                return index;
            }
            index++;
            current = current.next;
        }
        return null;
    }

    // insert
    public void insertSorted(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null || head.value.compareTo(value) >= 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null && current.next.value.compareTo(value) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    // Delete
    public T deleteFirst() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public T remove(int index) {
        if (index < 0 || head == null) {
            return null;
        }
        if (index == 0) {
            return deleteFirst();
        }
        Node<T> current = head;
        int count = 0;
        while (current != null && count < index - 1) {
            current = current.next;
            count++;
        }
        if (current == null || current.next == null) {
            return null;
        }
        T value = current.next.value;
        current.next = current.next.next;
        return value;
    }

    // split front and back sublists
    public LinkedList<T> frontBackSplit() {
        LinkedList<T> backList = new LinkedList<>();
        if (head == null) {
            return backList;
        }

        Node<T> slow = head;
        Node<T> fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        backList.head = slow.next;
        slow.next = null;

        return backList;
    }

    // sort and merge
    public void sort() {
        if (head == null || head.next == null) {
            return;
        }

        LinkedList<T> backList = this.frontBackSplit();

        this.sort();
        backList.sort();

        this.merge(backList);
    }

    private void merge(LinkedList<T> otherList) {
        Node<T> dummy = new Node<>(null);
        Node<T> current = dummy;
        Node<T> a = this.head;
        Node<T> b = otherList.head;

        while (a != null && b != null) {
            if (a.value.compareTo(b.value) <= 0) {
                current.next = a;
                a = a.next;
            } else {
                current.next = b;
                b = b.next;
            }
            current = current.next;
        }

        current.next = (a != null) ? a : b;

        this.head = dummy.next;
        otherList.head = null;
    }

    public static <T extends Comparable<T>> LinkedList<T> merge(LinkedList<T> list1, LinkedList<T> list2) {
        LinkedList<T> mergedList = new LinkedList<>();
        Node<T> a = list1.head;
        Node<T> b = list2.head;

        while (a != null && b != null) {
            if (a.value.compareTo(b.value) <= 0) {
                mergedList.append(a.value);
                a = a.next;
            } else {
                mergedList.append(b.value);
                b = b.next;
            }
        }

        while (a != null) {
            mergedList.append(a.value);
            a = a.next;
        }

        while (b != null) {
            mergedList.append(b.value);
            b = b.next;
        }

        return mergedList;
    }

    // found online
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}