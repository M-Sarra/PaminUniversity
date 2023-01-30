package ds.hashTable;

public class HashList {
    private int size;
    private HashNode first;
    private HashNode last;

    public HashList() {
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public HashNode getFirst() {
        if (this.size > 0) return this.first;
        else throw new Error("list is empty!");
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void insert(HashNode n) {
        n.setPrevious(this.last);
        n.setNext(null);
        this.last = n;
        if (isEmpty()) this.first = this.last;
        else this.last.getPrevious().setNext(n);
        this.size++;
    }

    public void delete(HashNode n) {
        if (isEmpty())
            throw new Error("list is empty!");
        else {
            if (n.getPrevious() != null)
                n.getPrevious().setNext(n.getNext());
            if (n.getNext() != null)
                n.getNext().setPrevious(n.getPrevious());
            this.size--;
            if (isEmpty()) {
                this.first = null;
                this.last = null;
            }
            else if (this.first.equals(n))
                this.first = n.getNext();
            else if (this.last.equals(n))
                this.last = n.getPrevious();
        }
    }
}
