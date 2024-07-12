# HashSymbolTable

A simple implementation of a hash table in Java, supporting basic operations such as insertion, lookup, deletion, and retrieval of all keys. The table uses separate chaining with linked lists to handle collisions.

## Features

- **Insert**: Add a key-value pair to the table.
- **Lookup**: Retrieve the value associated with a given key.
- **Delete**: Remove a key-value pair from the table.
- **Contains**: Check if the table contains a specific key.
- **Is Empty**: Check if the table is empty.
- **Size**: Get the number of key-value pairs in the table.
- **Keys**: Retrieve an iterable of all keys in the table.
- **Clear**: Clear all entries in the table.
- **Automatic Resizing**: The table resizes dynamically to maintain efficiency as the number of entries grows.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Installation

1. Clone the repository:
```
git clone https://github.com/Arshad-ahmedk/HashSymbolTable.git
```
2.Navigate to the project directory:
```
cd HashSymbolTable
```
3.Compile and run the HashSymbolTable class:
```
javac HashSymbolTable.java
java HashSymbolTable
```
### The program provides an interactive menu to perform various operations on the hash table:

1.Insert a symbol: Adds a key-value pair to the table.  
2.Lookup a symbol: Retrieves the value associated with a given key.  
3.Delete a symbol: Removes a key-value pair from the table.  
4.Print all symbols: Displays all key-value pairs in the table.  
5.Clear the table: Removes all entries from the table.  
6.Exit: Exits the program.

### Project Structure

HashSymbolTable.java: The main class implementing the hash table.  
SymbolTable.java: The interface defining the methods for the hash table.  
