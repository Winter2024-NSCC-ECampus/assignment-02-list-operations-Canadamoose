public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.prepend(5);

        System.out.println("List size: " + list.size());
        System.out.println("Head: " + list.head());
        System.out.println("Tail: " + list.tail());
        System.out.println("Element at index 1: " + list.at(1));
        System.out.println("Contains 20? " + list.contains(20));
        System.out.println("Index of 10: " + list.find(10));
        System.out.println("Popped element: " + list.pop());

        // Assignment 2 stuff
        list.append(11);
        list.append(1);
        list.append(3);
        list.append(8);
        list.append(7);

        // split lists
        System.out.println("\nOriginal list: " + list);
        LinkedList<Integer> backList = list.frontBackSplit();
        System.out.println("\nAfter splitting:");
        System.out.println("Front half: " + list);
        System.out.println("Back half: " + backList);

        // sort and merge
        list.sort();
        backList.sort();
        System.out.println("\nSorted lists:");
        System.out.println("Sorted A: " + list);
        System.out.println("Sorted B: " + backList);

        LinkedList<Integer> mergedList = LinkedList.merge(list, backList);
        System.out.println("\nMerged result:");
        System.out.println(mergedList);

        // insertion for assignment 2
        mergedList.insertSorted(6);
        mergedList.insertSorted(4);
        System.out.println("\nInsert result: ");
        System.out.println(mergedList);

        // deletion for assignment 2
        System.out.println("\nDeleted first: " + mergedList.deleteFirst());
        System.out.println("Deleted last: " + mergedList.pop());
        System.out.println("Deleted index 1: " + mergedList.remove(1));

        System.out.println("Final list: " + mergedList);
    }
}