
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de test para Matriz
 *
 * @author  Pedro J. Nuñez-Cacho Fuentes 
 * @version 13/12/2015
 */
public class MatrizTest
{
    /**
     * Metodo de prueba para el constructor con parametro dimension.  
     */
    @Test
    public void testConstructorConDimension()
    {
        // caso 1: dimension dentro de los limites. 
        int dimension = 5; 
        Matriz matriz = new Matriz(5); 
        assertEquals(5 , matriz.getDimension());

        // caso 2: dimension fuera de los limites (superior)>20
        try
        {
            int dimension2 = 25; 
            matriz = new Matriz(dimension2); 
            fail();
        }
        catch (Exception e)
        {
            assertEquals(e.getMessage(), "Parametro incorrecto");
        }
        // caso 2: dimension fuera de los limites (inferior)<0
        try
        {
            int dimension3 = -1; 
            matriz = new Matriz(dimension3); 
            fail();
        }
        catch (Exception e)
        {
            assertEquals(e.getMessage(), "Parametro incorrecto");
        }        

    }

    /**
     * Metodo de prueba para intercambiaDiagonales() 
     * con matriz estática pasada a constructor
     * ---ORIGEN---
     *  11 22 33 44 
     *  12 23 34 45 
     *  13 24 35 46 
     *  14 25 36 47 
     * ---TRANSF---
     *  44 22 33 11 
     *  12 34 23 45 
     *  13 35 24 46 
     *  47 25 36 14 
     */
    @Test
    public void testIntercambiaDiagonales()
    {
        // matriz estática para pruebas. pasada como parametro al constructor. 
        int[][] staticMatriz  = { 
                {11,22,33,44},
                {12,23,34,45},
                {13,24,35,46},
                {14,25,36,47},
            };

        Matriz matriz = new Matriz(staticMatriz);
        // comprobamos dimension de la matriz. 
        assertEquals(4 , matriz.getDimension());
        // caso 1: valores de la matriz estatica, por defecto. 
        // fila 1: 
        assertEquals(11, matriz.getValor(0,0));        
        assertEquals(22, matriz.getValor(0,1));
        assertEquals(33, matriz.getValor(0,2));
        assertEquals(44, matriz.getValor(0,3));

        // fila 2: 
        assertEquals(12, matriz.getValor(1,0));        
        assertEquals(23, matriz.getValor(1,1));
        assertEquals(34, matriz.getValor(1,2));
        assertEquals(45, matriz.getValor(1,3));
        // fila 3: 
        assertEquals(13, matriz.getValor(2,0));        
        assertEquals(24, matriz.getValor(2,1));
        assertEquals(35, matriz.getValor(2,2));
        assertEquals(46, matriz.getValor(2,3));
        // fila 4: 
        assertEquals(14, matriz.getValor(3,0));        
        assertEquals(25, matriz.getValor(3,1));
        assertEquals(36, matriz.getValor(3,2));
        assertEquals(47, matriz.getValor(3,3));

        // caso 2: 
        // cambiamos las diagonales y comprobamos sus valores con un bucle. 
        matriz.intercambiaDiagonales();
        // miramos, despues de intercambio de diagonales, 
        // un valor. El 0,0 debe ser 44 (y no 11)
        assertEquals(44,matriz.getMatriz()[0][0]);

        for (int i = 0; i < staticMatriz.length; i++)
        {
            for (int j = 0; j < staticMatriz[i].length; j++)
            {
                assertEquals(matriz.getValor(i,j),matriz.getMatriz()[i][j]);
            }
        }

    }

    /**
     * Metodo de prueba para intercambiaFilasImpares
     * Verifica los valores esperados despues de llamar al metodo. 
     * ---ORIGEN---
     *  11 22 33 44 
     *  14 25 36 47 
     *  13 24 35 46 
     *  12 23 34 45 
     * ---TRANSF---
     *  11 22 33 44 
     *  14 25 36 47 
     *  13 24 35 46 
     *  12 23 34 45 

     */
    @Test
    public void testIntercambiaFilasImpares()
    {
        int[][] staticMatriz  = { 
                {11,22,33,44},
                {12,23,34,45},
                {13,24,35,46},
                {14,25,36,47},
            };

        Matriz matriz = new Matriz(staticMatriz); 
        // comprobamos dimension de la matriz. 
        assertEquals(4 , matriz.getDimension());

        // caso 1: llamada al metodo de intercambio de filas impares. 
        // Los valores deben ser los devueltos por el metodo getMatriz()[i][j]
        matriz.intercambiaFilasImpares();
        for (int i = 0; i < staticMatriz.length; i++)
        {
            for (int j = 0; j < staticMatriz[i].length; j++)
            {
                assertEquals(matriz.getValor(i,j),matriz.getMatriz()[i][j]);
            }
        }
        // comprobamos de forma manual una de las filas 
        // fila 1 (impar) debe ser:  14 25 36 47 
        assertEquals(14,matriz.getValor(1,0));
        assertEquals(25,matriz.getValor(1,1));
        assertEquals(36,matriz.getValor(1,2));
        assertEquals(47,matriz.getValor(1,3));
    }

    /**
     * Metodo de prueba para intercambio de columnas pares. 
     * ---ORIGEN---
     *  11 22 33 44 
     *  12 23 34 45 
     *  13 24 35 46 
     *  14 25 36 47 
     * ---TRANSF---
     *  33 22 11 44 
     *  34 23 12 45 
     *  35 24 13 46 
     *  36 25 14 47 
     *  
     */
    @Test
    public void testIntercambiaColumnasPares()
    {
        int[][] staticMatriz  = { 
                {11,22,33,44},
                {12,23,34,45},
                {13,24,35,46},
                {14,25,36,47},
            };

        Matriz matriz = new Matriz(staticMatriz); 
        // comprobamos dimension de la matriz. 
        assertEquals(4 , matriz.getDimension());
        matriz.intercambiaColumnasPares();
        for (int i = 0; i < staticMatriz.length; i++)
        {
            for (int j = 0; j < staticMatriz[i].length; j++)
            {
                assertEquals(matriz.getValor(i,j),matriz.getMatriz()[i][j]);
            }
        }
        // comprobamos de forma manual una de las filas 
        // columna 0 (par) debe ser:  33 34 35 36
        assertEquals(33, matriz.getValor(0,0));
        assertEquals(34, matriz.getValor(1,0));
        assertEquals(35, matriz.getValor(2,0));
        assertEquals(36, matriz.getValor(3,0));

    }

    /**
     * Metodo de prueba para rotacion de 90 a la derecha 
     * ---ORIGEN---
     *  11 22 33 44 
     *  12 23 34 45  
     *  13 24 35 46 
     *  14 25 36 47 
     * ---TRANSF---
     *  14 13 12 11 
     *  25 24 23 22 
     *  36 35 34 33 
     *  47 46 45 44 
     */

    @Test
    public void testRotarNoventaDerecha()
    {
        int[][] staticMatriz  = { 
                {11,22,33,44},
                {12,23,34,45},
                {13,24,35,46},
                {14,25,36,47},
            };

        Matriz matriz = new Matriz(staticMatriz); 
        // comprobamos dimension de la matriz. 
        assertEquals(4 , matriz.getDimension());
        matriz.rotarNoventaDerecha();
        for (int i = 0; i < staticMatriz.length; i++)
        {
            for (int j = 0; j < staticMatriz[i].length; j++)
            {
                assertEquals(matriz.getValor(i,j),matriz.getMatriz()[i][j]);
            }
        }
        // comprobamos de forma manual una de las filas 
        // columna 3 rotada debe ser:  11 22 33 44 
        assertEquals(11, matriz.getValor(0,3));
        assertEquals(22, matriz.getValor(1,3));
        assertEquals(33, matriz.getValor(2,3));
        assertEquals(44, matriz.getValor(3,3));

    }

    /**
     * Metodo de prueba para rotacion de 90 a la derecha 
     * ---ORIGEN---
     *  11 22 33 44 
     *  12 23 34 45 
     *  13 24 35 46 
     *  14 25 36 47  
     * ---TRANSF---
     *  44 45 46 47 
     *  33 34 35 36 
     *  22 23 24 25 
     *  11 12 13 14 
     */

    @Test
    public void testRotarNoventaIzquierda()
    {
        int[][] staticMatriz  = { 
                {11,22,33,44},
                {12,23,34,45},
                {13,24,35,46},
                {14,25,36,47},
            };

        Matriz matriz = new Matriz(staticMatriz); 
        // comprobamos dimension de la matriz. 
        assertEquals(4 , matriz.getDimension());
        matriz.rotarNoventaIzquierda();
        for (int i = 0; i < staticMatriz.length; i++)
        {
            for (int j = 0; j < staticMatriz[i].length; j++)
            {
                assertEquals(matriz.getValor(i,j),matriz.getMatriz()[i][j]);
            }
        }
        // comprobamos de forma manual una de las filas 
        // columna 0 rotada debe ser:  44 33 22 11
        assertEquals(44, matriz.getValor(0,0));
        assertEquals(33, matriz.getValor(1,0));
        assertEquals(22, matriz.getValor(2,0));
        assertEquals(11, matriz.getValor(3,0));

    }    

    /**
     * Metodo de prueba zig-zag columnas impares
     * ---ORIGEN---
     * 11 22 33 44 
     * 12 23 34 45 
     * 13 24 35 46 
     * 14 25 36 47 
     * ---TRANSF---
     * 11 25 33 47 
     * 12 24 34 46 
     * 13 23 35 45 
     * 14 22 36 44 
     */

    @Test
    public void testZigZagColumnasImpares()
    {
        int[][] staticMatriz  = { 
                {11,22,33,44},
                {12,23,34,45},
                {13,24,35,46},
                {14,25,36,47},
            };

        Matriz matriz = new Matriz(staticMatriz); 
        // comprobamos dimension de la matriz. 
        assertEquals(4 , matriz.getDimension());
        matriz.zigzagColumnasImpares();
        for (int i = 0; i < staticMatriz.length; i++)
        {
            for (int j = 0; j < staticMatriz[i].length; j++)
            {
                assertEquals(matriz.getValor(i,j),matriz.getMatriz()[i][j]);
            }
        }
        // comprobamos de forma manual una de las filas 
        // columna 1 rotada debe ser:  25 24 23 22
        assertEquals(25, matriz.getValor(0,1));
        assertEquals(24, matriz.getValor(1,1));
        assertEquals(23, matriz.getValor(2,1));
        assertEquals(22, matriz.getValor(3,1));

    }

    /**
     * Metodo de prueba zig-zag filas impares
     * ---ORIGEN---
     * 11 22 33 44 
     * 12 23 34 45 
     * 13 24 35 46 
     * 14 25 36 47 
     * ---TRANSF---
     * 11 22 33 44  
     * 45 34 23 12 
     * 13 24 35 46 
     * 47 36 25 14 
     */
    
    @Test
    public void testZigZagFilasImpares()
    {
        int[][] staticMatriz  = { 
                {11,22,33,44},
                {12,23,34,45},
                {13,24,35,46},
                {14,25,36,47},
            };

        Matriz matriz = new Matriz(staticMatriz); 
        // comprobamos dimension de la matriz. 
        assertEquals(4 , matriz.getDimension());
        matriz.zigzagFilasImpares();
        for (int i = 0; i < staticMatriz.length; i++)
        {
            for (int j = 0; j < staticMatriz[i].length; j++)
            {
                assertEquals(matriz.getValor(i,j),matriz.getMatriz()[i][j]);
            }
        }
        // comprobamos de forma manual una de las filas 
        // fila 1 rotada debe ser:  45 34 23 12 
        assertEquals(45, matriz.getValor(1,0));
        assertEquals(34, matriz.getValor(1,1));
        assertEquals(23, matriz.getValor(1,2));
        assertEquals(12, matriz.getValor(1,3));
    }    
}

