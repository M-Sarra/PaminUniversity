package ds.hashTable;

import ds.list.EduNode;

public class DynamicTable {
    private int size;
    private int num;
    private TableSlot[] table;
    private final int a;
    private final int b;
    private final int p;

    public DynamicTable(int a, int b, int p) {
        this.size = 0;
        this.num = 0;
        this.table = new TableSlot[0];
        this.a = a;
        this.b = b;
        this.p = p;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public TableSlot[] getTable() {
        return table;
    }

    public void setTable(TableSlot[] table) {
        this.table = table;
    }

    public void insert(EduNode n, int code) {
        if (this.size == 0) {
            this.table = new TableSlot[1];
            this.size = 1;
        }
        if (this.num == this.size) {
            TableSlot[] newTable = new TableSlot[2 * size];
            insertAll(table, newTable);
            this.setTable(newTable);
            this.size = this.size * 2;
        }
        HashNode node = new HashNode(code, n);
        int hash = hash(node.getCode(), table.length);
        if (table[hash] == null)
            table[hash] = new TableSlot();
        table[hash].addEduData(node);
        this.num++;
    }

    private void insertAll(TableSlot[] table, TableSlot[] newTable) {
        for (int i = 0; i < table.length; i++) {
            HashNode node = table[i].getHashList().getFirst();
            for (int j = 0; j < table[i].getHashList().getSize(); j++) {
                int hash = hash(node.getCode(), newTable.length);
                if (newTable[hash] == null)
                    newTable[hash] = new TableSlot();
                newTable[hash].addEduData(node);
                node = node.getNext();
            }
        }
    }

    public void delete(int code) {
        int hash = hash(code, table.length);
        HashNode node = table[hash].getHashList().getFirst();
        for (int i = 0; i < table[hash].getHashList().getSize(); i++) {
            if (node.getCode() == code) {
                table[hash].getHashList().delete(node);
                break;
            }
            node = node.getNext();
        }
        if (num <= size/4.0) {
            TableSlot[] newTable = new TableSlot[size/2];
            insertAll(table, newTable);
            this.setTable(newTable);
            this.size = this.size / 2;
        }
    }

    public EduNode search(int code) {
        int hash = hash(code, table.length);
        HashNode node = table[hash].getHashList().getFirst();
        for (int i = 0; i < table[hash].getHashList().getSize(); i++) {
            if (node.getCode() == code)
                return node.getEduData();
            node = node.getNext();
        }
        return null;
    }

    public int hash(int x, int n) {
        return ((a * n + b) % p) % x;
    }
}
