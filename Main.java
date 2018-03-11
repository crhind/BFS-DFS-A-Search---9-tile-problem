public class Main {

    public static void main(String[] args)
    {
        String goalState = "012345678";
        if(args.length == 2)
        {
            if(args[0].length() == 9)
            {
                Node startNode = new Node(args[0]);
                long startTime;
                long finishTime;
                long completionTime;
                Methods method = new Methods();

                if(args[1].equals("BFS"))
                {
                    startTime = System.currentTimeMillis();
                    method.BFS(startNode, goalState);
                    finishTime = System.currentTimeMillis();
                    completionTime = finishTime - startTime;
                    System.out.println("Completion Time: " + completionTime + " milliseconds");
                }
                else if (args[1].equals("DFS"))
                {
                    startTime = System.currentTimeMillis();
                    method.DFS(startNode, goalState);
                    finishTime = System.currentTimeMillis();
                    completionTime = finishTime - startTime;
                    System.out.println("Completion Time: " + completionTime + " milliseconds");

                }
                else if(args[1].equals("AST"))
                {
                    startTime = System.currentTimeMillis();
                    method.A_star(startNode, goalState);
                    finishTime = System.currentTimeMillis();
                    completionTime = finishTime - startTime;
                    System.out.println("Completion Time: " + completionTime + " milliseconds");
                }
            }
            else
            {
                System.out.println("Fkd up your start state.");
            }
        }
        else
        {
            System.out.println("Not enough command line arguments");
        }
    }
}
