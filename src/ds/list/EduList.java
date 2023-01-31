package ds.list;

public class EduList {
    private int size;
    private EduNode first;
    private EduNode last;

    public EduList() {
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public EduNode getFirst() {
        if (this.size > 0) return this.first;
        else throw new Error("list is empty!");
    }

    public EduNode getLast() {
        if (this.size > 0) return this.last;
        else throw new Error("list is empty!");
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFirst(EduNode first) {
        this.first = first;
    }

    public void setLast(EduNode last) {
        this.last = last;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void insertFirst(Object ob) {
        this.first = new EduNode(ob, this.first);
        if (isEmpty()) this.last = this.first;
        else this.first.getNext().setPrevious(this.first);
        this.size++;
    }

    public EduNode insertLast(Object ob) {
        this.last = new EduNode(this.last, ob);
        if (isEmpty()) this.first = this.last;
        else this.last.getPrevious().setNext(this.last);
        this.size++;
        return last;
    }

    public EduNode insert(Object ob) {
        return insertLast(ob);
    }

    public void insert(EduNode n) {
        n.setPrevious(this.last);
        n.setNext(null);
        this.last = n;
        if (isEmpty()) this.first = this.last;
        else this.last.getPrevious().setNext(n);
        this.size++;
    }

    public void deleteFirst() {
        if (isEmpty())
            throw new Error("list is empty!");
        EduNode n = this.first;
        this.first = n.getNext();
        this.size--;
        if (!isEmpty())
            this.first.setPrevious(null);
        if (isEmpty()) this.last = null;
    }

    public void deleteLast() {
        if (isEmpty())
            throw new Error("list is empty!");
        EduNode n = this.last;
        this.size--;
        this.last = n.getPrevious();
        if (!isEmpty())
            this.last.setNext(null);
        if (isEmpty()) this.first = null;
    }

    public void delete(EduNode n) {
        if (isEmpty())
            throw new Error("list is empty!");
        if (this.first.equals(n))
            deleteFirst();
        else if (this.last.equals(n))
            deleteLast();
        else {
            if (n.getPrevious() != null)
                n.getPrevious().setNext(n.getNext());
            if (n.getNext() != null)
                n.getNext().setPrevious(n.getPrevious());
            this.size--;
        }
    }
}
