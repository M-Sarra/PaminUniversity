package ds.hashTable;

import ds.list.EduNode;

public class HashNode {
    private int code;
    private EduNode eduData;
    private HashNode next;
    private HashNode previous;

    public HashNode(int code, EduNode eduData) {
        this.code = code;
        this.eduData = eduData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public EduNode getEduData() {
        return eduData;
    }

    public void setEduData(EduNode eduData) {
        this.eduData = eduData;
    }

    public HashNode getNext() {
        return next;
    }

    public void setNext(HashNode next) {
        this.next = next;
    }

    public HashNode getPrevious() {
        return previous;
    }

    public void setPrevious(HashNode previous) {
        this.previous = previous;
    }
}
