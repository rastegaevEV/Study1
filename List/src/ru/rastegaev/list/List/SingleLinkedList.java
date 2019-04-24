package ru.rastegaev.list.List;

public class SingleLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SingleLinkedList() {

    }

    public SingleLinkedList(T head) {
        this.head = new ListItem<>(head);
    }

    public SingleLinkedList(SingleLinkedList<T> list) {
        if (list.head == null) {
            throw new NullPointerException("Список пуст");
        }
        this.head = new ListItem<>(list.head.getData());
        for (ListItem<T> item = list.head.getNext(); item != null; item = item.getNext()) {
            addToTail(item.getData());
        }
    }

    public ListItem<T> getHead() {
        return head;
    }

    public void setHead(T head) {
        this.head = new ListItem<>(head);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ListItem<T> item = this.head; item != null; item = item.getNext()) {
            stringBuilder.append(item.getData()).append(" ");
        }
        return stringBuilder.toString();
    }

    public void print() {
        for (ListItem<T> item = this.head; item != null; item = item.getNext()) {
            System.out.println(item.getData());
        }
    }

    public int getSize() {
        return this.count;
    }

    public T getFirstItemData() {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        return this.head.getData();
    }

    public ListItem<T> getIndexItem(int index) {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        int currentIndex = 0;
        for (ListItem<T> indexItem = this.head; indexItem != null; indexItem = indexItem.getNext()) {
            if (currentIndex == index) {
                return indexItem;
            }
            ++currentIndex;
        }
        return null;
    }

    public T getDataViaIndex(int index) {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Такого индекса в списке нет");
        }
        return getIndexItem(index).getData();
    }

    public T setViaIndex(int index, T data) {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Такого индекса в списке нет");
        }
        ListItem<T> indexItem = getIndexItem(index);
        T oldData = indexItem.getData();
        indexItem.setData(data);
        return oldData;
    }

    public T deleteViaIndex(int index) {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Такого индекса в списке нет");
        }
        if (index == 0) {
            --this.count;
            return deleteFirst();
        }
        int currentIndex = 1;

        for (ListItem<T> currentItem = head.getNext(), previousItem = head; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentIndex == index) {
                previousItem.setNext(currentItem.getNext());
                --this.count;
                return currentItem.getData();
            }
            ++currentIndex;
        }
        return null;
    }

    public void addToTop(T data) {
        this.head = new ListItem<>(data, head);
        ++this.count;
    }

    public void insertViaIndex(int index, T data) {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Такого индекса в списке нет");
        }
        if (index == 0) {
            addToTop(data);
        }
        ListItem<T> item = new ListItem<>(data);
        int currentIndex = 1;
        for (ListItem<T> currentItem = this.head.getNext(), previousItem = this.head; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentIndex == index) {
                previousItem.setNext(item);
                item.setNext(currentItem);
                ++this.count;
                return;
            }
            ++currentIndex;
        }
    }

    public boolean deleted(T data) {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        if (this.head.getData().equals(data)) {
            deleteFirst();
            return true;
        }
        for (ListItem<T> currentItem = this.head.getNext(), previousItem = head; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentItem.getData().equals(data)) {
                previousItem.setNext(currentItem.getNext());
                return true;
            }
        }
        return false;
    }

    public T deleteFirst() {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        T deleteData = this.head.getData();
        this.head = this.head.getNext();
        --this.count;
        return deleteData;
    }

    public void addToTail(T data) {
        if (this.head == null) {
            addToTop(data);
        }
        ListItem<T> item = new ListItem<>(data);
        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            if (currentItem.getNext() == null) {
                currentItem.setNext(item);
                ++this.count;
                return;
            }
        }
    }

    public void turn() {
        if (this.head == null) {
            throw new NullPointerException("Список пуст");
        }
        ListItem<T> previousItem = null;
        for (ListItem<T> currentItem = this.head, nextItem = currentItem.getNext(); nextItem != null; currentItem = nextItem, nextItem = nextItem.getNext()) {
            currentItem.setNext(previousItem);
            if (nextItem.getNext() == null) {
                this.head = nextItem;
                nextItem.setNext(currentItem);
                return;
            }
            previousItem = currentItem;
        }
    }
}
