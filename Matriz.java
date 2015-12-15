import java.util.Random;

/**
 * Clase para gestionar Matriz
 * 
 * @author Pedro J. Nuñez-Cacho Fuentes
 * @version 12/12/2015
 */
public class Matriz
{
    // atributos
    private int[][] matriz;
    private int dimension; 
    public final static int MIN_VALUE=1;
    public final static int MAX_VALUE=50;
    public final static int MIN_DIM=0;
    public final static int MAX_DIM=20;

    /**
     * Constructor con parametro int para definir los valores de la matriz
     * Se debe lanzar una excepcion para los valores fuera del rango [1,50]
     * 
     */
    public Matriz(int dimension)
    {
        this.dimension = dimension; 

        if (dimension >= MIN_DIM && dimension <= MAX_DIM)
        {
            matriz = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++)
                for (int j = 0; j < dimension; j++)
                    matriz[i][j] = generaDatosMatriz();

        }
        else
        {
            throw new RuntimeException("Parametro incorrecto");
        }
        // 
        salida();  

    }

    /**
     * Constructor con parametro int para definir los valores de la matriz
     * Se debe lanzar una excepcion para los valores fuera del rango [1,50]
     * 
     */
    public Matriz(int[][] matriz)
    {
        this.dimension=matriz.length;
        setMatriz(matriz);

    }

    /**
     * Metodo para concoer la dimension de la matriz. 
     * 
     * @return - dimension de la matriz. 
     */
    public int getDimension()
    {
        return this.dimension; 
    }

    /**
     * Metodo de acceso a la matriz 
     * 
     * @return int[][] - Matriz 
     */
    public int[][] getMatriz()
    {
        return matriz; 
    }

    /**
     * Metodo de modificacion a la matriz 
     * Llena la matriz de valores concretos. 
     * 
     * @param 
     */
    public void setMatriz(int[][] matriz)
    {
        this.matriz = matriz; 
    }

    /**
     * Metodo para observar cualquier elemento (i,j) de la matriz.
     * 
     * @param i - para obtener numero de fila 
     * @param j - para obtener numero de columna
     * 
     * @return devuelve el elemento para fila y columna 
     */
    public  int getValor(int i, int j){
        return matriz[i][j];
    }

    /**
     * Metodo setter que permite establecer el valor de un elemento. 
     * 
     */
    public void setValor(int i, int j, int num) {
        // solo si nuestra dimension de la matrz es correcta hacemos la modificacion. 
        if (i >= MIN_DIM && i <= MAX_DIM && j >= MIN_DIM && j <= MAX_DIM)
        {
            matriz[i][j] = num;
        }
        else
        {
            throw new RuntimeException("Parametro incorrecto");
        }
    }

    /**
     * Metodo para intercambiar diagonales en la matriz.
     * Se intercambia la diagonal primera con la segunda y viceversa.
     * 
     */
    public void intercambiaDiagonales()
    {
        int[][] auxiliar = new int[dimension][dimension];
        for(int i=0, j=0, k = dimension - 1; j<dimension; j++, i++, k--)
        {
            auxiliar[i][j] = matriz[j][i];
            matriz[j][i] = matriz[j][k];
            matriz[j][k] =  auxiliar[i][j];
        }
    }

    /**
     * Metodo para intercambiar filas impares en la matriz.
     * Se intercambian los elementos de las filas impares de arriba a abajo. 
     * 
     */
    public void intercambiaFilasImpares()
    {
        // Establecemos una matriz auxiliar y la variable de calculo de filas impares. 
        int[][] auxiliar = new int[dimension][dimension];
        int filasImpares;
        // llenamos la matriz auxiliar con los valores de la matriz actual. 
        for (int i=0;i<auxiliar.length;i++)
            for (int j=0;j<auxiliar[i].length;j++)
                auxiliar[i][j]=matriz[i][j];

        // es par/impar?
        if (matriz.length%2==0)
            filasImpares=matriz.length-1;
        else 
            filasImpares=matriz.length-2;
        // recorremos para mover filas impares a la ubicacion pedida. 
        for (int i=1; i<filasImpares; i=i+2)
        {
            for (int j=0; j<matriz[i].length;j++)
            {
                // primera fila impar. 
                if (i==1)
                {
                    setValor(filasImpares,j,auxiliar[i][j]);
                }
                setValor(i,j,auxiliar[i+2][j]);
            }

        }

    }

    /**
     * Metodo para intercambiar filas impares en la matriz.
     * Se intercambian los elementos de las filas de arriba a abajo. 
     * 
     */
    public void intercambiaColumnasPares()
    {
        // Establecemos una matriz auxiliar y la variable de calculo de columnas pares. 
        int[][] auxiliar = new int[dimension][dimension];
        int columnasPares;
        // llenamos la matriz auxiliar con los valores de la matriz actual. 
        for (int i=0;i<auxiliar.length;i++)
            for (int j=0;j<auxiliar[i].length;j++)
                auxiliar[i][j]=matriz[i][j];

        // es par/impar?
        if (matriz.length%2==0)
            columnasPares=matriz.length/2;
        else 
            columnasPares=(matriz.length+1)/2;

        // recorremos para mover columnas pares a la ubicacion pedida. 
        for (int i=0; i<matriz.length; i++)
        {
            for (int j=0; j<columnasPares;j++)
            {
                // primera columna par. 
                if (j==0)
                {                    
                    setValor(i,dimension-2,auxiliar[i][j]);
                }
                if (j%2==0)
                    setValor(i,j,auxiliar[i][j+2]);
            }
        }
    }

    /**
     * Metodos de rotacion
     * Traspuesta de la matriz 
     * 
     */
    private void transpuesta() 
    {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = i; j < matriz[0].length; j++) {
                int num = matriz[i][j];
                matriz[i][j] = matriz[j][i];
                matriz[j][i] = num;
            }
        }
    }

    /**
     * Metodos de rotacion
     * Intercambia las filas de la matriz
     * 
     */
    public void intercambiarFilas() 
    {
        for (int  i = 0, k = matriz.length - 1; i < k; ++i, --k) {
            int[] num = matriz[i];
            matriz[i] = matriz[k];
            matriz[k] = num;
        }
    }

    /**
     * Metodos de rotacion
     * Intercambia las columnas de la matriz
     * 
     */
    public void intercambiarColumnas() 
    {
        for (int  j = 0, k = matriz.length - 1 ; j < k; ++j, --k) 
        {
            int[] num = matriz[j];
            matriz[j] = matriz[k];
            matriz[k] = num;

        }
    }

    /**
     * Metodos de rotacion
     * Rota la matriz 90 grados a la deecha
     * 
     */

    public void rotarNoventaDerecha()
    {
        intercambiarFilas();
        transpuesta();
    }

    /**
     * Metodos de rotacion
     * Rota la matriz 90 grados a la izquierda
     * 
     */
    public void rotarNoventaIzquierda() 
    {
        transpuesta();
        intercambiarFilas();
    }

    /**
     * Metodo para invertir los elementos de las columnas impares. 
     * 
     */
    public void zigzagColumnasImpares()
    {
        // local auxiliar de la matriz
        int[][] auxiliar = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
        {

            for (int j = 0; j < dimension; j=j+2)
            {
                auxiliar[i][j] = matriz[i][j];
            }
        }

        for (int j = 1; j < dimension; j=j+2)
        {
            for (int i = 0; i < dimension; i++)
            {
                auxiliar[i][j]=matriz[dimension-i-1][j];
            }
        }
        // rellenamos la matriz 
        matriz = auxiliar;
    }

    /**
     * Metodo para invertir los elementos de las filas impares. 
     * 
     */
    public void zigzagFilasImpares()
    {
        // local auxiliar de la matriz
        int[][] auxiliar = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
        {
            if (i%2!=0)
            {
                for (int j = 0; j < dimension; j++)
                {
                    auxiliar[i][dimension-1-j] = matriz[i][j];
                }
            }else{
                for (int j = 0; j < dimension; j++)
                {
                    auxiliar[i][j]=matriz[i][j];
                }
            }
        }
        // rellenamos la matriz 
        matriz = auxiliar;
    }

    /**
     * Metodo auxiliar para mostrar la matriz generada en el constructor con parametro entero. 
     * 
     */
    public void imprime() 
    {
        for (int i = 0; i < dimension; i++)
        {
            System.out.print(" ");
            for (int j = 0; j < dimension; j++)
            {
                System.out.print(matriz[i][j]);
                if (matriz[i][j]<10)                
                    System.out.print("  ");
                else
                    System.out.print(" ");
            }
            System.out.println();

        }
        System.out.println();

    }

    /**
     * Metodo auxiliar para generar valores aleatorios para la matriz 
     * 
     */
    public int generaDatosMatriz()
    {
        Random r = new Random();
        int number = r.nextInt(MAX_VALUE - MIN_VALUE ) + MIN_VALUE;
        return number; 
    }

    /**
     * Metodo salida
     * Prueba el funcionamiento metodo a metodo 
     * 
     */
    public void salida()
    {
        System.out.println("[+] MATRIZ FUENTE / ORIGEN --------------- "); 
        imprime();

        intercambiaDiagonales();
        System.out.println("[+] MATRIZ DIAGONAL ------------------- ");         
        imprime();

        intercambiaFilasImpares();
        System.out.println("[+] MATRIZ FILAS IMPARES -------------- ");         
        imprime();

        System.out.println("[+] MATRIZ COLUMNAS PARES ------------- ");         
        intercambiaColumnasPares();
        imprime();

        System.out.println("[+] MATRIZ 90 DERECHA ----------------- ");         
        rotarNoventaDerecha();
        imprime();

        System.out.println("[+] MATRIZ 90 IZQUIERDA --------------- ");         
        rotarNoventaIzquierda();
        imprime();

        System.out.println("[+] MATRIZ ZIGZAG COLS. IMPARES ------------- ");         
        zigzagColumnasImpares();
        imprime();

        System.out.println("[+] MATRIZ ZIGZAG FILAS IMPARES ------------- ");         
        zigzagFilasImpares();
        imprime();

    }
}
