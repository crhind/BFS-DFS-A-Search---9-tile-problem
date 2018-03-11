import java.util.*;

public class Methods
{
	//Breadth First Search, Fairly generic - taken directly from lecture notes
	// Goes through the top levels first working its way downwards - only difference from
	// Depth First Search is the use of a queue instead of a stack
	public void BFS(Node root, String goalState)
	{
		Set<String> visited = new HashSet<String>();
		int popped = 0;
		Node node = new Node(root.getState());
		Queue<Node> queue = new LinkedList<>();
		Node currentNode = node;
		while(!currentNode.getState().equals(goalState))
		{
			visited.add(currentNode.getState());
			List<String> nodeChildren = RandomCrap.getChildren(currentNode.getState());
			for(String x : nodeChildren)
			{
				if(!visited.contains(x))
				{
					visited.add(x);
					Node child = new Node(x);
					currentNode.addChild(child);
					child.setParent(currentNode);
					queue.add(child);
				}
			}
			currentNode = queue.poll();
			popped += 1;
		}

		RandomCrap.printSolution(currentNode, visited, root, popped);
	}

	//Depth First Search, pretty generic - Taken straight from the lecture notes
	// Searches as deep as it can in the tree first before moving sidewards.
	public void DFS(Node root, String goalState)
	{
		Set<String> visited = new HashSet<>();
		int popped = 0;
		Node node = new Node(root.getState());
		Stack<Node> stack = new Stack<>();
		Node currentNode = node;
		while(!currentNode.getState().equals(goalState))
		{
			visited.add(currentNode.getState());
			List<String> nodeChildren = RandomCrap.getChildren(currentNode.getState());
			for(String x : nodeChildren)
			{
				if(!visited.contains(x))
				{
					visited.add(x);
					Node child = new Node(x);
					currentNode.addChild(child);
					child.setParent(currentNode);
					stack.push(child);
				}
			}
			currentNode = stack.pop();
			popped += 1;
		}

		RandomCrap.printSolution(currentNode, visited, root, popped);
	}

	// The best of the 3 Searches, uses "knowledge" of the environment to search in the correct direction
	// Can be optimised by playing around with the PriorityQueue compare function
	// or messing around with the type of Heuristic you use. - This uses Manhattan.
	public void A_star(Node root, String goalState)
	{
		Set<String> visited = new HashSet<>();
		int popped = 0;
		Node node = new Node(root.getState());
		node.setCost(0);

		NodeCompare compare = new NodeCompare();

		PriorityQueue<Node> priority = new PriorityQueue<>(10, compare);
		Node current = node;
		while(!current.getState().equals(goalState))
		{
			List<String> nodeChildren = RandomCrap.getChildren(current.getState());
			for(String x : nodeChildren)
			{
				if(!visited.contains(x))
				{
					visited.add(x);
					Node child = new Node(x);
					current.addChild(child);
					child.setParent(current);

					child.setCost(current.getCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))) + manhattan(child.getState(), goalState));
					priority.add(child);
				}
			}
			current = priority.poll();
			popped+=1;
		}
		RandomCrap.printSolution(current, visited, root, popped);
	}

	//This is the Manhattan Heuristic.
	private int manhattan(String currentState, String goalSate)
	{
		int difference = 0;
		for (int i = 0; i < currentState.length(); i += 1)
		{
			for (int j = 0; j < goalSate.length(); j += 1)
			{
				if (currentState.charAt(i) == goalSate.charAt(j))
				{
					difference = difference + ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3));
				}
			}
		}
		return difference;
	}

	//Comparitor Class for the A_star search - Fairly simple change.
	private class NodeCompare implements Comparator<Node>
	{
		@Override
		public int compare(Node x, Node y)
		{
			int returnVal = 0;
			if(x.getCost() < y.getCost())
			{
				returnVal = -1;
			}
			else if(x.getCost() > y.getCost())
			{
				returnVal = 1;
			}
			return returnVal;
		}
	}
}
