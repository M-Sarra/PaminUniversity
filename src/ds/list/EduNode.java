package ds.list;

public class EduNode {
    private Object element;
    private EduNode next;
    private EduNode previous;
    private EduNode grade;
    private EduNode leftPre;
    private EduNode leftNext;
    private EduNode rightPre;
    private EduNode rightNext;

    public EduNode() {
    }

    public EduNode(Object element, EduNode next) {
        this.element = element;
        this.next = next;
    }

    public EduNode(EduNode previous, Object element) {
        this.element = element;
        this.previous = previous;
    }

    public EduNode(Object element, EduNode next, EduNode previous) {
        this.element = element;
        this.next = next;
        this.previous = previous;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public EduNode getNext() {
        return next;
    }

    public void setNext(EduNode next) {
        this.next = next;
    }

    public EduNode getPrevious() {
        return previous;
    }

    public void setPrevious(EduNode previous) {
        this.previous = previous;
    }

    public EduNode getGrade() {
        return grade;
    }

    public void setGrade(EduNode grade) {
        this.grade = grade;
    }

    public EduNode getLeftPre() {
        return leftPre;
    }

    public void setLeftPre(EduNode leftPre) {
        this.leftPre = leftPre;
    }

    public EduNode getLeftNext() {
        return leftNext;
    }

    public void setLeftNext(EduNode leftNext) {
        this.leftNext = leftNext;
    }

    public EduNode getRightPre() {
        return rightPre;
    }

    public void setRightPre(EduNode rightPre) {
        this.rightPre = rightPre;
    }

    public EduNode getRightNext() {
        return rightNext;
    }

    public void setRightNext(EduNode rightNext) {
        this.rightNext = rightNext;
    }
}
