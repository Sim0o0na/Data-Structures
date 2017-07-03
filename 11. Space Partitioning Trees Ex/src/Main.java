import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Simona Simeonova on 6/20/2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<ProblemObject> objects = new ArrayList<>();
        HashMap<String, ProblemObject> objectsById = new HashMap<>();

        boolean running = true;
        int ticks = 0;

        String command = scan.nextLine();
        while (!command.equals("end")){
            String[] cmdArgs = command.split("\\s+");
            switch (cmdArgs[0]){
                case "add":
                    String id = cmdArgs[1];
                    int x = Integer.parseInt(cmdArgs[2]);
                    int y = Integer.parseInt(cmdArgs[3]);

                    ProblemObject obj = new ProblemObject(id,x,y);
                    objects.add(obj);
                    objectsById.put(id, obj);
                    break;
                case "start":
                    while (running){
                        command =scan.nextLine();
                        cmdArgs = command.split("\\s+");
                        if(cmdArgs[0].equals("end")){
                            running=false;
                        }
                        if(cmdArgs[0].equals("move")){
                            ProblemObject objToMove = objectsById.get(cmdArgs[1]);
                            int xMove = Integer.parseInt(cmdArgs[2]);
                            int yMove = Integer.parseInt(cmdArgs[3]);
                            objToMove.setX1(xMove);
                            objToMove.setY1(yMove);
                            ticks++;
                        }
                        if(cmdArgs[0].equals("tick")){
                            insertionSort(objects);
                            ticks++;
                        }
                        sweep(ticks, objects);
                    }
                    break;
            }
            command = scan.nextLine();
        }
    }
    private static void sweep(int ticks, List<ProblemObject> objs){
        insertionSort(objs);
        for (int i = 0; i < objs.size(); i++) {
             ProblemObject curr = objs.get(i);
            for (int j = i+1; j < objs.size(); j++) {
                ProblemObject candidate = objs.get(j);
                if (candidate.getX1() > curr.getX1()) {
                    return;
                }
                if (curr.collides(candidate)) {
                    System.out.printf("(%s) %s collides with %s\n", ticks, curr.getId(), candidate.getId());
                }
            }
        }
    }

    private static void insertionSort(List<ProblemObject> objs){
        for(int i = 1; i<objs.size(); i++){
            int j = i;
            while(j>0 && objs.get(j-1).getX1() > objs.get(j).getX1()){
                swap(j-1,j,objs);
                j--;
            }
        }
    }

    private static void swap(int i, int j, List<ProblemObject> objs) {
        ProblemObject temp = objs.get(i);
        objs.set(i,objs.get(j));
        objs.set(j,temp);
    }


}
