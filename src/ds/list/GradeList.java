package ds.list;

public class GradeList{
    private int size;
    private EduNode first;
    private EduNode last;

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

    public EduNode insert(Object ob,EduNode student, EduNode leftNext,
                          EduNode course, EduNode rightNext) {
        EduNode n = new EduNode(this.last, ob);
        n.setLeftPre(student);
        n.setLeftNext(leftNext);
        n.setRightPre(course);
        n.setRightNext(rightNext);
        if (n.getLeftNext() != null)
            n.getLeftNext().setLeftPre(n);
        if (n.getRightNext() != null)
            n.getRightNext().setRightPre(n);
        if (isEmpty()) this.first = this.last;
        else this.last.getPrevious().setNext(this.last);
        this.size++;
        return n;
    }

    public void delete(EduNode n) {
        if (isEmpty())
            throw new Error("list is empty!");
        if (n.getPrevious() != null)
            n.getPrevious().setNext(n.getNext());
        if (n.getNext() != null)
            n.getNext().setPrevious(n.getPrevious());
        if (n.getLeftPre() != null)
            n.getLeftPre().setLeftNext(n.getLeftNext());
        if (n.getLeftNext() != null)
            n.getLeftNext().setLeftPre(n.getLeftPre());
        if (n.getRightPre() != null)
            n.getRightPre().setRightNext(n.getRightNext());
        if (n.getRightNext() != null)
            n.getRightNext().setRightPre(n.getRightPre());
        if (isEmpty()) {
            this.first = null;
            this.last = null;
        }
        else if (this.first.equals(n))
            this.first = n.getNext();
        else if (this.last.equals(n))
            this.last = n.getPrevious();
        this.size--;
    }
}
