

public class GenericsBox<T> {

    private T t;          

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        GenericsBox<Integer> integerBox = new GenericsBox<Integer>();
        integerBox.set(new Integer(10));
        integerBox.inspect(new Double(20.00)); 
        GenericsBox<String> stringBox = new GenericsBox<String>();

        stringBox.set("string");
        stringBox.inspect(new Double(20.00)); 
        
        // error: this is still String!
    }
}