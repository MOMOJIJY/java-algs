public class Steque<Item>
{
	private Stack<Item> s1 = new Stack<Item>();
	private Stack<Item> s2 = new Stack<Item>();

	public void push(Item item)
	{
		s2.push(item);
	}

	public Item pop()
	{
		while (!s1.isEmpty())
			s2.push(s1.pop());
		item = s2.pop();
		while (!s2.isEmpty())
			s1.push(s2.pop());
		return item;
	}


}