# svg-data-structure-grapher

Svg data structure grapher.

With proprietary implementations of data structures, which meet the complexity in time and space.

## Prerequisites.
**_The program makes use of the MAVEN 3.8.6 or higher packager._**

* Linux.
```sh
  https://maven.apache.org/download.cgi
```
1. Clone the repository.
```sh
   git clone https://github.com/richardfm77/svg-data-structure-grapher.git
```
```sh
   cd svg-data-structure-grapher/proyecto2
```

2. Compile.
```sh
   mvn compile
```

3. Run tests.

**It is necessary to keep the txt files for the tests to work correctly, which are located in the "proyecto2/testArchivos/" directory.**

```sh
   mvn test
```

## Start program.

* Installation of the files **.jar**.
```sh
   mvn install
```

**Important:**

The program makes use of two types of input.

* Run:
```sh
   java -jar target/proyecto2.jar
```
to make the program read the standard input.

* Run:
```sh
   java -jar target/proyecto2.jar [file]
```
to make the program read a file, where **[file]** is the directory of that file.

# Input format

Regardless of the type of input you choose to use (standard or file), the input must follow the following format:

```sh
   [typeOfStructure] [elements]
```
Where the types of data structures are:

* ArbolAVL
* ArbolBinarioCompleto
* ArbolBinarioOrdenado
* ArbolRojinegro
* Arreglo
* Cola
* Grafica
* MonticuloMinimo
* Lista
* Pila      

And the elements are positive integers, whose range goes from "0 < n < 100", each element must be separated by a space.

**Note:**

* The progaram ignores line breaks and ignores any string followed by the **#** character.
* For the *Graphic* structure an even number of elements is required, since to join edges they are joined in pairs. If you do not want edges, just join them with the same element.

# Output

The output of the program is only via standard output, however you can redirect the output to a *.svg* file with the following command.
```sh
   java -jar target/proyecto2.jar [fileInput] > [fileOutput].svg
```

# Examples:

* ArbolRojinegro.

**Input**

```sh
   java -jar target/proyecto2.jar testArchivos/ArbolRojinegro/ArbolRojinegro.txt > Output.svg
```
Where the file "testArchivos/ArbolRojinegro/ArbolRojinegro.txt" contains the following entry: 
```sh
   #Clase
    ArbolRojinegro
    #elementos
    57 19 8 6 15 1 94 99 97
```

**Output**

Generates a file named Output.svg which contains the following image:

![TreeRedBlack](https://user-images.githubusercontent.com/90520860/216840664-899d5963-a933-42af-8da4-f6b62478ec6b.png)



* ArbolAVL.

**Input**

```sh
   java -jar target/proyecto2.jar testArchivos/ArbolRojinegro/ArbolRojinegro.txt > Output.svg
```
Where the file "testArchivos/ArbolAVL/ArbolAVL.txt" contains the following entry: 
```sh
  ArbolAVL 3 26 89 59 75 82 84 53 30 50
```

**Output**

Generates a file named Output.svg which contains the following image:

![TreeAVL](https://user-images.githubusercontent.com/90520860/216840805-d244d057-f875-47ce-a526-3bd81cd1f238.png)

* Lista

```sh
   java -jar target/proyecto2.jar testArchivos/Lista/Lista.txt > Output.svg
```
Where the file "testArchivos/ArbolAVL/ArbolAVL.txt" contains the following entry: 
```sh
  Lista 34 24 55 66 0
```

**Output**

Generates a file named Output.svg which contains the following image:

![List](https://user-images.githubusercontent.com/90520860/216840951-52d6971d-c7f6-4d73-a13d-a5684c86f3b1.png)

* Grafica

```sh
   java -jar target/proyecto2.jar testArchivos/Grafica/Grafica.txt > Output.svg
```
Where the file "testArchivos/ArbolAVL/ArbolAVL.txt" contains the following entry: 
```sh
  Grafica
  1 2 2 3 3 4 4 5 5 1 1 3 1 4 2 4 2 5 3 5 1 2
```

**Output**

Generates a file named Output.svg which contains the following image:

![Graph](https://user-images.githubusercontent.com/90520860/216840996-ffb4a453-21f5-453b-86fc-4a42a77566ab.png)

For more sample images: ![Examples Images](https://github.com/richardfm77/svg-data-structure-grapher/tree/main/proyecto2/testArchivos)
