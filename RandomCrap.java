import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RandomCrap
{

		//Knowing the position and desired final position of the board allows you to
		//	calculate the direct children (Surroundig tiles) in the board.
		//Each number is the index associated with the board.
		public static List<String> getChildren(String state) {
			List<String> successors = new ArrayList<String>();

			switch (state.indexOf("0")) {
				case 0: {
					successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
					successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
					break;
				}
				case 1: {
					successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
					successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
					successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
					break;
				}
				case 2: {

					successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
					successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
					break;
				}
				case 3: {
					successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
					successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
					successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
					break;
				}
				case 4: {
					successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
					successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
					successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
					successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
					break;
				}
				case 5: {
					successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
					successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
					successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
					break;
				}
				case 6: {
					successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
					successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
					break;

				}
				case 7: {
					successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
					successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
					successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
					break;
				}
				case 8: {
					successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
					successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
					break;
				}
			}

			return successors;
		}

		//This is a simple print function that returns the path travelled from start to the goal node along with a bunch of stats.
		//Can be improved with more stat and a bit of logic checking
		public static void printSolution(Node goal, Set<String> visited, Node startNode, int popped)
		{
			int totalCost = 0;

			Stack<Node> solutionStack = new Stack<Node>();
			solutionStack.push(goal);
			while (!goal.getState().equals(startNode.getState()))
			{
				solutionStack.push(goal.getParent());
				goal = goal.getParent();
			}
			String sourceState = (startNode).getState();
			String destinationState;
			int cost = 0;
			for (int i = solutionStack.size() - 1; i >= 0; i--)
			{
				System.out.println("-----------------------------");
				destinationState = solutionStack.get(i).getState();
				if (!sourceState.equals(destinationState))
				{
					System.out.println("Move " + destinationState.charAt(sourceState.indexOf('0')) + " " + transition(sourceState, destinationState));
					cost = Character.getNumericValue(destinationState.charAt(sourceState.indexOf('0')));
					totalCost += cost;
				}

				sourceState = destinationState;
				System.out.println("Cost of the movement: " + cost);
				System.out.println("*******");
				System.out.println("* " + solutionStack.get(i).getState().substring(0, 3)+" *");
				System.out.println("* " + solutionStack.get(i).getState().substring(3, 6)+" *");
				System.out.println("* " + solutionStack.get(i).getState().substring(6, 9)+" *");
				System.out.println("*******");

			}
			System.out.println("---------------------------------");
			System.out.println("** Number of transitions to get to the goal state from the initial state:  " + (solutionStack.size() - 1));
			System.out.println("** Number of visited states:  " + (visited.size()));
			System.out.println("** Total cost for this solution: " + totalCost);
			System.out.println("** Number of Nodes poped out of the queue: " + popped);
			System.out.println("----------------------------------");
		}

		//A method used to find out what the previous transition between the two states was,
		// understanding that only one change will be made at each iteration and  it will either be
		// up, down, left or right which corresponds to these numbers, This case would not be the change if using a 3x3 matrix.
		public static String transition(String source, String destination)
		{
			int zeroPosDifference = destination.indexOf('0') - source.indexOf('0');
			String hold = null;
			switch(zeroPosDifference)
			{
				case 3:
					hold = "DOWN";
					break;
				case -3:
					hold = "UP";
					break;
				case -1:
					hold = "LEFT";
					break;
				case 1:
					hold = "RIGHT";
					break;
				default:
					throw new IllegalArgumentException("Something is wrong with the transition method");
			}

			return hold;
		}
}
