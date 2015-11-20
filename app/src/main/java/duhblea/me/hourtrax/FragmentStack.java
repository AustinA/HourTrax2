package duhblea.me.hourtrax;

import android.app.Fragment;
import java.util.ArrayList;

/**
 * Created by austinalderton on 11/8/15.
 */
public class FragmentStack {
    private int size;
    private ArrayList<Fragment> theStack;

    public FragmentStack(int stackSize) {
        size = stackSize;
        theStack = new ArrayList<Fragment>();
    }

    public void push(Fragment aFragment) {

        int existingIndex = findFragment(aFragment);

        if (existingIndex > -1) {
            theStack.remove(existingIndex);
        }
        theStack.add(0, aFragment);
        maintainStackSize();
        theStack.trimToSize();
    }

    public Fragment pull() {
        Fragment returnFrag = theStack.get(0);
        theStack.remove(0);
        theStack.trimToSize();
        return returnFrag;
    }

    public Fragment peek() {
        return theStack.get(0);
    }

    public boolean isEmpty() {
        return theStack.isEmpty();
    }

    private void maintainStackSize() {
        if (theStack.size() > size) {
            theStack.remove(size - 1);
        }

    }

    private int findFragment(Fragment aFragment) {
        for (int i = 0; i < theStack.size(); i++) {
            if (theStack.get(i) == aFragment) {
                return i;
            }
        }
        return -1;
    }

}
