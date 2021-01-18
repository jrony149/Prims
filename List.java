@SuppressWarnings("overrides")

public class List {

    private class Node {

        Object data;

        Node next;

        Node previous;

        public Node() {

            next = null;

            previous = null;

        }

        public String toString(){

            return String.valueOf(data);
        }
    }

    private Node head;

    private Node tail;

    private int index;

    private int length;

    private Node cursor;

    //Constructor

    public List() {

        head = null;

        tail = null;

        index = -1;

        cursor = null;

        length = 0;
    }


    //Access Functions

    int length(){

        return length;
    }

    int index(){

        if(cursor == null){

            return(-1);
        }

        else {

            return index;

        }
    }

    public Object front(){

        if(length < 1){

            throw new RuntimeException("Error.  List module front()  cannot return front element of empty List.\n");
        }

        else {

            return head.data;

        }
    }

    public Object back(){

        if(length < 1){

            throw new RuntimeException("Error.  List module back() cannot return back element of empty List.\n");
        }

        else {

            return tail.data;

        }
    }

    public Object get(){

        if(length == 0){

            throw new RuntimeException("Error.  List module get() method cannot output data element of empty List.\n");
        }

        if(index < 0){

            throw new RuntimeException("Error.  List module get() method cannot output data element of undefined node.\n");
        }

        else {

            return (cursor.data);

        }
    }



    public boolean equals(Object x) {

        List L = (List)x;

        Node temp = new Node();

        Node temp2 = new Node();

        temp2 = L.head;

        temp = head;

        if(length == L.length()) {

            for (int z = 0; z < length(); z++) {

                if (temp.data != temp2.data) {

                    return false;
                }

                temp = temp.next;

                temp2 = temp2.next;
            }

            return true;

        }

        return false;
    }

    //Manipulation procedures:

    void clear(){
        //resetting List to its initial state and letting JVM garbage collection take care of the rest.
        head = null;

        tail = null;

        length = 0;

        index = -1;

        cursor = null;
    }

    void moveFront(){

        if(length > 0){

            cursor = head;

            index = 0;
        }
    }

    void moveBack(){

        if(length > 0){

            cursor = tail;

            index = length - 1;
        }


    }

    void movePrev(){

        if(cursor == null){

            throw new RuntimeException("Error.  List module movePrev() method cannot move undefined cursor.\n");
        }

        if(index == 0){

            cursor = null;

            index = -1;

        }

        else{

            cursor = cursor.previous;

            index = index - 1;
        }
    }

    void moveNext(){

        if(cursor == null){

            throw new RuntimeException("Error.  List module moveNext() method cannot move undefined cursor.\n");
        }

        if(index == length - 1){

            cursor = null;

            index = -1;
        }

        else{

            cursor = cursor.next;

            index = index + 1;
        }
    }

    void moveTo(int i) {

        if (head == null && tail == null) {

            System.out.println("Cannot move cursor in empty List.\n");
        }
        else {

            cursor = head;

            index = 0;

            for (int x = 0; x < i; x++) {

                index = index + 1;

                cursor = cursor.next;
            }
        }

    }

    void prepend(Object num) {

        Node temp = new Node();

        temp.data = num;

        temp.next = null;

        if (head == null) {

            head = temp;

            tail = temp;

            temp = null;

            length = 1;

        }

        else {

            head.previous = temp;

            temp.next = head;

            head = temp;

            length = length + 1;

            if(cursor != null){

                index = index + 1;
            }
        }
    }

    void append(Object num) {

        Node temp = new Node();

        temp.data = num;

        temp.next = null;

        if (head == null) {

            head = temp;

            tail = temp;

            temp = null;

            length = 1;
        }

        else {

            tail.next = temp;

            temp.previous = tail;

            tail = temp;

            length = length + 1;

        }
    }

    void insertBefore(Object data){

        if(length == 0){

            throw new RuntimeException("Error.  List module insertBefore() method cannot insert element in empty List.\n");
        }

        if(index < 0){

            throw new RuntimeException("Error.  List module insertBefore() method cannot insert element in undefined position.\n");
        }

        if(index == 0){

            prepend(data);
        }


        else{

            Node temp = new Node();

            temp.data = data;

            cursor.previous.next = temp;

            temp.previous = cursor.previous;

            temp.next = cursor;

            cursor.previous = temp;

            length = length + 1;

            index = index + 1;
        }
    }

    void insertAfter(Object data){

        if(length() == 0){

            throw new RuntimeException("Error.  List module insertAfter() method cannot insert element in empty List.\n");
        }

        if(index() < 0){

            throw new RuntimeException("Error.  List module insertAfter() method cannot insert element in undefined position.\n");
        }

        if(index() == length() - 1){

            append(data);
        }

        else{

            Node temp = new Node();

            temp.data = data;

            cursor.next.previous = temp;

            temp.next = cursor.next;

            temp.previous = cursor;

            cursor.next = temp;

            length = length + 1;
        }
    }

    void peek(){

        Node temp = new Node();

        temp = head;

        if(length == 1){

            System.out.println(temp.data);
        }

        while(temp.next != null){

            System.out.print(temp.data + "\r\n");

            temp = temp.next;

            if(temp.next == null){

                System.out.print(temp.data + " ");

                break;
            }
        }
    }

    void deleteFront(){

        if(length < 1){

            throw new RuntimeException("Error.  List module delteFront() method cannot dlete leement from empty List.\n");
        }

        else if(length() == 1){

            if(cursor == head || cursor == tail){

                cursor = null;

                index = -1;
            }

            head = null;

            tail = null;

            length = 0;
        }

        else if(length > 1) {

            if(cursor == head){

                cursor = null;

                index = -1;
            }

            if(cursor != head && cursor != null){

                index = index -1;
            }

            head = head.next;

            head.previous = null;

            length = length - 1;

        }

    }

    void deleteBack(){

        if(length < 1){

            throw new RuntimeException("Error.  List module deleteBack() method cannot delete element from empty List.\n");
        }

        else if(length == 1){

            if(cursor == tail || cursor == head){

                cursor = null;

                index = -1;
            }

            head = null;

            tail = null;

            length = 0;
        }

        if(cursor == tail && length > 1){

            cursor = null;

            index = -1;
        }

        else if(length > 1){

            tail = tail.previous;

            tail.next = null;

            length = length - 1;

        }

    }

    void delete(){

        if(length == 0){

            throw new RuntimeException("List module delete() method cannot delete node from empty List.\n");
        }

        if(index == 0 && 0 != length - 1){

            head = head.next;

            length = length - 1;

            cursor = null;

            index = -1;
        }

        else if(index == length - 1 && length != 1){

            tail = tail.previous;

            tail.next = null;

            length = length - 1;

            cursor = null;

            index = -1;
        }

        else if(index != 0 && index != length - 1){

            cursor.next.previous = cursor.previous;

            cursor.previous.next = cursor.next;

            cursor = null;

            index = -1;

            length = length - 1;
        }

        else if(length == 1){

            clear();
        }


    }

    public String toString(){

        if(length() == 0){

            return(" ");
        }

        Node temp = head;

        String output = temp.toString() + " ";

        int count = 1;

        temp = temp.next;

        while(count != length()){

            output = output + temp.toString() + " ";

            temp = temp.next;

            count = count + 1;
        }

        return output;
    }

    /*List copy(){

        List copy = new List();

        if(length() == 0){

            return(copy);
        }

        else {

            moveFront();

            for (int x = 0; x < length(); x++) {

                copy.append(get());

                moveNext();
            }

        }

        return copy;
    }

    List concat(List L){

        List concat = new List();

        moveFront();

        for(int x = 0; x < length(); x++){

            concat.append(get());

            moveNext();
        }

        L.moveFront();

        for(int x = 0; x < L.length(); x++){

            concat.append(L.get());

            L.moveNext();
        }

        return(concat);

    }*/

}
