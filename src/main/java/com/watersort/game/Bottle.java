package com.watersort.game;

import java.util.Stack;

public class Bottle {
    public final Stack<String> stack;
    public final int maxCapacity;

    public Bottle(int maxCapacity) {
        this.stack = new Stack<>();
        this.maxCapacity = maxCapacity;
    }

    public boolean isFull() {
        return stack.size() == maxCapacity;
    }

    public int size() {
        return stack.size();
    }

    public int getMaxSize() {
        return maxCapacity;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public String getTop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public boolean add(String color, int count) {
        if (stack.size() + count > maxCapacity) {
            return false;
        }
        if (!stack.isEmpty() && !stack.peek().equals(color)) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            stack.push(color);
        }
        return true;
    }

    public boolean assignColor(String color) {
        if (stack.size() >= maxCapacity) {
            return false;
        }
        stack.push(color);
        return true;
    }

    public void remove() {
        if (stack.isEmpty()) {
            return;
        }
        String topColor = stack.peek();
        while (!stack.isEmpty() && stack.peek().equals(topColor)) {
            stack.pop();
        }
    }

    public int countTopDuplicates() {
        if (stack.isEmpty())
            return 0;

        String topColor = stack.peek();
        int count = 0;

        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).equals(topColor)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public Stack<String> getBottle() {
        return stack;
    }
}
