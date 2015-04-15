package core.util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public class RandomQueue<E> implements Queue<E> {
    static int Squads = 32;
    static int wid = 8;
    @SuppressWarnings("unchecked")
    LinkedList<E>[][] bulk = new LinkedList[wid][wid];
    Random r;
    int count = 0;

    @Override
    public int size() {
        return count;
    }

    public RandomQueue() {
        r = new Random(System.currentTimeMillis());
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j] = new LinkedList<E>();
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return count > 0 ? false : true;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                if (bulk[i][j].contains(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        ArrayList<E> list = new ArrayList<E>();
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j].toArray();
                list.addAll(bulk[i][j]);
            }
        }
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        ArrayList<E> list = new ArrayList<E>();
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j].toArray();
                list.addAll(bulk[i][j]);
            }
        }
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        ArrayList<E> list = new ArrayList<E>();
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j].toArray();
                list.addAll(bulk[i][j]);
            }
        }
        return list.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                if (bulk[i][j].contains(o)) {
                    return bulk[i][j].remove(o);
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        ArrayList<E> list = new ArrayList<E>();
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j].toArray();
                list.addAll(bulk[i][j]);
            }
        }
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int i = r.nextInt(wid);
        int j = r.nextInt(wid);
        return bulk[i][j].addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j].removeAll(c);
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j].retainAll(c);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < wid; ++i) {
            for (int j = 0; j < wid; ++j) {
                bulk[i][j].clear();
            }
        }
    }

    @Override
    public boolean add(E e) {
        int i = r.nextInt(wid);
        int j = r.nextInt(wid);
        return bulk[i][j].add(e);
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        int i = r.nextInt(wid);
        int j = r.nextInt(wid);
        for (int k = i; k < wid; ++k) {
            for (int l = j; l < wid; ++l) {
                if (bulk[k][l].isEmpty()) {
                    continue;
                }
                return bulk[k][l].remove();
            }
        }
        for (int k = 0; k < i; ++k) {
            for (int l = 0; l < j; ++l) {
                if (bulk[k][l].isEmpty()) {
                    continue;
                }
                return bulk[k][l].remove();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public E poll() {
        int i = r.nextInt(wid);
        int j = r.nextInt(wid);
        try {
            for (int k = i; k < wid; ++k) {
                for (int l = j; l < wid; ++l) {
                    if (bulk[k][l].isEmpty()) {
                        continue;
                    }
                    return bulk[k][l].remove();
                }
            }
            for (int k = 0; k < i; ++k) {
                for (int l = 0; l < j; ++l) {
                    if (bulk[k][l].isEmpty()) {
                        continue;
                    }
                    return bulk[k][l].remove();
                }
            }
        } catch (NoSuchElementException e) {
            return null;
        }
        return null;
    }

    @Override
    public E element() {
        int i = r.nextInt(wid);
        int j = r.nextInt(wid);
        for (int k = i; k < wid; ++k) {
            for (int l = j; l < wid; ++l) {
                if (bulk[k][l].isEmpty()) {
                    continue;
                }
                return bulk[k][l].getFirst();
            }
        }
        for (int k = 0; k < i; ++k) {
            for (int l = 0; l < j; ++l) {
                if (bulk[k][l].isEmpty()) {
                    continue;
                }
                return bulk[k][l].getFirst();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public E peek() {
        int i = r.nextInt(wid);
        int j = r.nextInt(wid);
        try {
            for (int k = i; k < wid; ++k) {
                for (int l = j; l < wid; ++l) {
                    if (bulk[k][l].isEmpty()) {
                        continue;
                    }
                    return bulk[k][l].getFirst();
                }
            }
            for (int k = 0; k < i; ++k) {
                for (int l = 0; l < j; ++l) {
                    if (bulk[k][l].isEmpty()) {
                        continue;
                    }
                    return bulk[k][l].getFirst();
                }
            }
        } catch (NoSuchElementException e) {
            return null;
        }
        return null;
    }
    
    public static void main(String[] args) {
		
	}

}