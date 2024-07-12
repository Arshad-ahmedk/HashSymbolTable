import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashSymbolTable<Key, Value> implements SymbolTable<Key, Value> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;
    private ArrayList<LinkedList<Pair<Key, Value>>> table;

    // Pair class
    private static class Pair<Key, Value> {
        private Key key;
        private Value value;

        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    public HashSymbolTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashSymbolTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
        this.table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
    }

    // Helper method to get index in hash table
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    // Resize the table
    private void resize(int newCapacity) {
        ArrayList<LinkedList<Pair<Key, Value>>> newTable = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            newTable.add(new LinkedList<>());
        }
        for (LinkedList<Pair<Key, Value>> bucket : table) {
            for (Pair<Key, Value> pair : bucket) {
                int index = (pair.key.hashCode() & 0x7fffffff) % newCapacity;
                newTable.get(index).add(pair);
            }
        }
        this.table = newTable;
        this.capacity = newCapacity;
    }

    // Implement methods defined in SymbolTable interface
    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        if (size >= capacity * 0.75) resize(2 * capacity);
        int index = hash(key);
        LinkedList<Pair<Key, Value>> bucket = table.get(index);
        for (Pair<Key, Value> pair : bucket) {
            if (pair.key.equals(key)) {
                pair.value = value; // Update value if key exists
                return;
            }
        }
        bucket.add(new Pair<>(key, value));
        size++;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        int index = hash(key);
        LinkedList<Pair<Key, Value>> bucket = table.get(index);
        for (Pair<Key, Value> pair : bucket) {
            if (pair.key.equals(key)) {
                return pair.value; // Return value if key exists
            }
        }
        return null; // Key not found
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        int index = hash(key);
        LinkedList<Pair<Key, Value>> bucket = table.get(index);
        Pair<Key, Value> toRemove = null;
        for (Pair<Key, Value> pair : bucket) {
            if (pair.key.equals(key)) {
                toRemove = pair;
                break;
            }
        }
        if (toRemove != null) {
            bucket.remove(toRemove);
            size--;
        }
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        int index = hash(key);
        LinkedList<Pair<Key, Value>> bucket = table.get(index);
        for (Pair<Key, Value> pair : bucket) {
            if (pair.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        LinkedList<Key> keys = new LinkedList<>();
        for (LinkedList<Pair<Key, Value>> bucket : table) {
            for (Pair<Key, Value> pair : bucket) {
                keys.add(pair.key);
            }
        }
        return keys;
    }

    // Clear the table
    public void clear() {
        table.clear();
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
        size = 0;
    }

    public static void main(String[] args) {
        HashSymbolTable<String, Integer> symbolTable = new HashSymbolTable<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect operation:");
            System.out.println("1. Insert a symbol");
            System.out.println("2. Lookup a symbol");
            System.out.println("3. Delete a symbol");
            System.out.println("4. Print all symbols");
            System.out.println("5. Clear the table");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter symbol (key): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter value: ");
                    int value = scanner.nextInt();
                    symbolTable.put(symbol, value);
                    System.out.println("Symbol '" + symbol + "' inserted with value " + value);
                    break;
                case 2:
                    System.out.print("Enter symbol (key) to lookup: ");
                    symbol = scanner.nextLine();
                    Integer lookupValue = symbolTable.get(symbol);
                    if (lookupValue != null) {
                        System.out.println("Value associated with '" + symbol + "': " + lookupValue);
                    } else {
                        System.out.println("Symbol '" + symbol + "' not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter symbol (key) to delete: ");
                    symbol = scanner.nextLine();
                    symbolTable.delete(symbol);
                    System.out.println("Symbol '" + symbol + "' deleted.");
                    break;
                case 4:
                    System.out.println("Symbols in symbol table:");
                    for (String key : symbolTable.keys()) {
                        System.out.println(key + ": " + symbolTable.get(key));
                    }
                    break;
                case 5:
                    symbolTable.clear();
                    System.out.println("Table cleared.");
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}
