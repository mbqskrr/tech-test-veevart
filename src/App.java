import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {

    /*
     * Variables identificadas segùn el enunciado
     * el nùmero de pisos es una constante, al igual que el los sentidos a los que
     * se dirige el ascensor,
     * el resto de variables se modifican en lo que se ejecuta el programa
     */
    final static String UP = "Subiendo";
    final static String DOWN = "Bajando";
    final static int FLOORS = 29;
    static int startFloor;
    static int actualFloor;
    static int[] floorsArray;
    static String orientation;
    static List<Integer> stopFloors = new ArrayList<>();
    // static int[]
    static HashMap<Integer, Integer> inputFloors = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        Scanner sc = new Scanner(System.in);
        // Se settea en los que el ascensor deberà de parar
        System.out.println(
                "Digite el numero de los pisos (separados por una coma (,)) en los que el ascensor se detendrá.");
        String stringFloors = sc.nextLine();
        String[] floors = stringFloors.split(",");
        floorsArray = Arrays.stream(floors).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(floorsArray));

        // Setteo del piso inicial donde empezarà el ascensor
        System.out.println("Ingrese el piso inicial de ejecuciòn del ascensor");
        startFloor = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese los pisos de destino");
        String stringFloorsStop = sc.nextLine();
        String[] stop = stringFloorsStop.split(",");
        int[] stopInt = Arrays.stream(stop).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < floorsArray.length; i++) {
            inputFloors.put(floorsArray[i], stopInt[i]);
        }
        System.out.println(inputFloors);
        /*
         * for (Integer floor : floorsArray) {
         * System.out.println("En el piso "+floor+" hacia el piso ");
         * }
         */
        System.out.println("Sentido del ascensor: \n1)Subiendo \n2)Bajando  ");
        orientation = sc.nextLine();
        if (orientation.equals("1")) {
            orientation = UP;
        } else {
            orientation = DOWN;
        }
        sc.close();
        System.out.println("Resumen: \nArreglo de pisos: " + floorsArray.toString() + "\nPiso inicial de ejecución: "
                + startFloor +
                "\nPisos ingresados: " + inputFloors + "\nSentido: " + orientation);

        start(floorsArray, startFloor, inputFloors, orientation);

    }

    private static void start(int[] floorsArray2, int startFloor2, HashMap<Integer, Integer> inputFloors2,
            String orientation) {
        stopFloors(floorsArray2, inputFloors2);
        actualFloor = startFloor2;
        Collections.sort(stopFloors);
        Arrays.sort(floorsArray2);

        if (orientation.equals(UP)) {
            goingUp(stopFloors, startFloor2);
        }

        if (orientation.equals(DOWN)) {
            goingdDown(stopFloors, startFloor2);
        }
    }

    private static void goingdDown(List<Integer> stopFloors2, int startFloor2) {
        System.out.println("Ascensor descendiendo");
        for (int i = actualFloor; i > 0; i--) {
            System.out.println("El ascensor se encuentra en el piso: " + actualFloor);
            for (int j = 0; j < stopFloors2.size(); j++) {
                if (stopFloors2.get(j) == actualFloor) {
                    System.out.println("Asecnesor se detine");
                }

                if (actualFloor > 0) {
                    System.out.println("Ascensor descendiendo");
                    actualFloor--;
                }

                if (actualFloor == 1) {
                    System.out.println("El Ascensor se encuentra en el piso: " + actualFloor);
                    System.out.println("Ascensor se detiene");
                    break;
                }
            }
            actualFloor = actualFloor + startFloor2;
        }
    }

    private static void goingUp(List<Integer> stopFloors2, int startFloor2) {
        System.out.println("Ascensor subiendo");
        actualFloor += 1;

        for (int i = startFloor2; i < FLOORS; i++) {
            System.out.println("Ascensor en el piso" + actualFloor);
            for (int j = 0; j < stopFloors2.size(); j++) {
                if (stopFloors2.get(j) == actualFloor) {
                    System.out.println("Ascensor se detiene");
                    if (j + 1 < stopFloors2.size()) {
                        if (stopFloors2.get(j) == stopFloors2.get(j + 1)) {
                            System.out.println("Piso ingresado: " + inputFloors.get(stopFloors2.get(j)));
                            stopFloors2.remove(j);
                            stopFloors2.remove(j + 1);

                        } else {
                            System.out.println("Piso ingresado: " + inputFloors.get(stopFloors2.get(j)));
                            stopFloors2.remove(j);
                        }
                    } else {
                        System.out.println("Piso ingresado: " + inputFloors.get(stopFloors2.get(j)));
                        stopFloors2.remove(j);
                    }
                }
                if (actualFloor < FLOORS) {
                    System.out.println("Ascensor sigue subiendo");
                    actualFloor++;
                }
            }
            actualFloor = actualFloor - 1;

        }
    }

    private static void stopFloors(int[] floorsArray2, HashMap<Integer, Integer> inputFloors2) {
        Integer[] stopFloors2 = inputFloors2.values().toArray(new Integer[0]);
        int pos = 0;
        for (int i = 0; i < floorsArray2.length; i++) {
            stopFloors.add(floorsArray2[i]);
            pos = i;
        }

        for (int i = 0; i < stopFloors2.length; i++) {
            stopFloors.add(stopFloors2[i]);
        }
    }

}
