import java.util.Iterator;

//Kasper Rosenberg  karo5568 - kasper.rosenberg@live.se
//Sammmarbete med Hussein Alabdali - hual0441 - hussein.alabdali@outlook.com
//och Philip Yayo -phya4625 - philipyayo@gmail.com 

public class MyALDAList<E> implements ALDAList<E> {
	private Node<E> first;
	private int listSize = 0;

	private static class Node<E> {
		E data;
		Node next;

		public Node(E data) {
			this.data = data;
		}

		public String toString() {
			return (String) data;
		}

	}

	@Override
	public void add(E data) {
		Node<E> newNode = new Node<E>(data);
		if (size() == 0) {
			first = newNode;
			listSize++;
			return;
		}
		Node<E> tempNode = first;
		while (tempNode.next != null) {
			tempNode = tempNode.next;
		}
		newNode.next = tempNode.next;
		tempNode.next = newNode;
		listSize++;
		return;
	}

	@Override
	public void add(int index, E data) {
		int i = 0;
		Node<E> newNode = new Node<E>(data);
		Node<E> tempNode = first;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			newNode.next = tempNode;
			first = newNode;
			listSize++;
			return;
		}
		while (i < index - 1) {
			tempNode = tempNode.next;
			i++;
		}
		newNode.next = tempNode.next;
		tempNode.next = newNode;
		listSize++;
	}

	@Override
	public E remove(int index) {

		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> tempNode = first;
		Node<E> removedNode = null;
		if (index == 0) {
			first = first.next;
			listSize--;
			return tempNode.data;
		}
		int i = 0;
		while (i < index - 1) {
			tempNode = tempNode.next;
			i++;
		}
		removedNode = tempNode.next;
		tempNode.next = tempNode.next.next;
		listSize--;
		return removedNode.data;

	}

	@Override
	public boolean remove(E data) {
		for (Node<E> tempNode = first; tempNode != null; tempNode = tempNode.next) {
			if (tempNode.data == data || tempNode.data.equals(data)) {
				remove(indexOf(tempNode.data));
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int index) {
		int i = 0;
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		for (Node<E> tempNode = first; tempNode != null; tempNode = tempNode.next) {
			if (i == index) {
				return (E) tempNode.data;
			}
			i++;
		}
		return null;
	}

	@Override
	public boolean contains(E data) {
		for (Node<E> tempNode = first; tempNode != null; tempNode = tempNode.next) {
			if (tempNode.data == data || tempNode.data.equals(data)) {
				return true;
			}
		}
		return false;

	}

	@Override
	public int indexOf(E data) {
		int i = 0;
		for (Node<E> tempNode = first; tempNode != null; tempNode = tempNode.next) {
			if (tempNode.data == data || tempNode.data.equals(data)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public void clear() {
		first = null;
		listSize = 0;
	}

	@Override
	public int size() {
		return listSize;
	}

	private class MyALDAListIterator implements Iterator<E> {
		private Node<E> pointer = first;
		private boolean okToRemove = false;

		@Override
		public boolean hasNext() {
			return pointer != null;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			E data = pointer.data;
			pointer = pointer.next;
			okToRemove = true;
			return data;
		}

		public void remove() {
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			for (Node<E> tempNode = first; tempNode != null; tempNode = tempNode.next) {
				if (tempNode.next == pointer) {
					MyALDAList.this.remove(tempNode.data);
				}
			}
			okToRemove = false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MyALDAListIterator();

	}

	public String toString() {
		String content = "";
		for (Node<E> tempNode = first; tempNode != null; tempNode = tempNode.next) {
			if (tempNode.next != null) {
				content += tempNode.data + ", ";
			} else {
				content += tempNode.data;
			}

		}
		return "[" + content + "]";
	}
}