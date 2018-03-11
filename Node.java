import java.util.ArrayList;

//Standard Node Class - Nothing special, just setters and getters
public class Node
{
	private String state;
	private ArrayList<Node> children;
	private Node parent;
	private int cost;
	private int depth;
	private boolean visited;

	public Node(String inState)
	{
		state = inState;
		children = new ArrayList<>();
		parent = null;
		cost = 0;
		depth = 0;
		visited = false;
	}

	public boolean isVisited()
	{
		return visited;
	}

	public void setVisited(boolean inVisited)
	{
		visited = inVisited;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String inState)
	{
		state = inState;
	}

	public void setParent(Node inParent)
	{
		parent = inParent;
	}

	public Node getParent()
	{
		return parent;
	}

	public void setDepth(int inDepth)
	{
		depth = inDepth;
	}

	public int getDepth()
	{
		return depth;
	}

	public void setCost(int inCost)
	{
		cost = inCost;
	}

	public int getCost()
	{
		return cost;
	}

	public void addChild(Node inChild)
	{
		children.add(inChild);
	}
}
