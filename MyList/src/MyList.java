public class MyList<T> {

   private T[] list;
   private T[] tempList;
  private int capacity;
  private int size;

public void setList(Integer capacity){
    this.list= (T[]) new Object[this.capacity];
    this.size=0;
}
public void changeList(){
    this.tempList= (T[]) new Object[this.capacity];
    for (int i =0;i< this.list.length;i++){
        tempList[i]=list[i];
    }
    this.list=this.tempList;
}
public void incCapacity(){
    this.capacity = 2 * capacity ;
}
public  <M> void toString(M[] tempList){
    System.out.print("List = [ ");
    for (M i :tempList) {
        if (i==null) break;
        System.out.print(i+" ");
    }
    System.out.println(" ]");
    }
    public MyList() {
        this.capacity=10;
        setList(capacity);
    }

    public MyList(int capacity) {
        this.capacity = capacity;
        setList(capacity);
    }
    MyList<T> subList(int start, int finish){
        MyList<T> subList = new MyList<>();
    for (int i =start ; i<finish;i++){
        subList.getList()[i]=this.list[i];
    }
    return subList;
    }

    public void insert(T data){
        for (int i =0 ;i<getCapacity();i++){
            if (list[i]==null){
                list[i]=data;
                this.size++;
                break;
            }
        }
        if (getCapacity()==this.size){
            incCapacity();
            changeList();
        }
    }



public T get(int index){
    if (index>=size)return null;
    return list[index];
}

int indexOf(T data){
    for (int i = 0 ; i<this.size;i++){
        if (list[i]==data) return i;
    }
    return -1;
}
int lastIndexOf(T data){
    for (int i = 0 ; i<this.size;i++){
        for (int j = (this.size-1) ; j > i ; j--){
            if (list[i]==list[j]) {
                return j;
            }
        }
    }
    return -1;
}
public T[] toArray(){
    T[] newArray = (T[]) new Object[this.size];
    for (int i =0 ; i<this.size ; i++) {
        newArray[i]=list[i];
    }
    return newArray;
}
public boolean isEmpty(){
    if (this.size==0) return true;
    return false;
}
public void set(int index , T data){
    this.list[index]=data;
}
public boolean contains(T data){
    for (T i : list ) {
        if (i==data) return true;
    }
    return false;
}
public void clear(){
    this.capacity=10;
    setList(10);
}

    public  int size(){
    return size;
    }

    public T[] getList() {
        return list;
    }

    public void setList(T[] list) {
        this.list = list;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
