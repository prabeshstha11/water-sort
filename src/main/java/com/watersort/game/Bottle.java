package com.watersort.game;

import java.util.Stack;

public class Bottle {
    private final Stack<String> stack = new Stack<>();
    public final int size;

    public Bottle(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean isFull() {
        return stack.size() == size;
    }

    public String top() {
        if (isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public int countDuplicate() {
        if (isEmpty()) {
            return 0;
        }
        String topColor = top();
        int count = 0;
        for (String color : stack) {
            if (color.equals(topColor)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public void removeTop(int count) {
        for (int i = 0; i < count; i++) {
            if (!isEmpty()) {
                stack.pop();
            } else {
                break;
            }
        }
    }

    public boolean add(String color, int count) {
        if (isEmpty()) {
            for (int i = 0; i < count; i++) {
                stack.push(color);
            }
            return true;
        } else if (isFull()) {
            return false;
        } else if (top().equals(color) && (stack.size() + count <= size)) {
            for (int i = 0; i < count; i++) {
                stack.push(color);
            }
            return true;
        }
        return false;
    }

    public void assignColor(String color) {
        if (!isFull()) {
            stack.push(color);
        }
    }

    public Stack<String> getBottle() {
        return stack;
    }

    @Override
    public String toString() {
        return "Bottle{" + "size=" + size + ", stack=" + stack + '}';
    }
}
