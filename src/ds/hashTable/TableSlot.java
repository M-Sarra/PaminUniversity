package ds.hashTable;

public class TableSlot {
    private final HashList hashList;

    public TableSlot() {
        this.hashList = new HashList();
    }

    public HashList getHashList() {
        return hashList;
    }

    public void addEduData(HashNode n) {
        hashList.insert(n);
    }
}
