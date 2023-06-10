package com.fichosa.technicaltest.dfs;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PrisonerDFS {
    public boolean canEscape(String[] prison) {
        if (prison == null || prison.length < 1 || "".equals(Arrays.stream(prison).collect(Collectors.joining()).trim())) {
            return false;
        }

        return dfs(prison, findPrisionerInitialPosition(prison), new boolean[prison.length][prison[0].length()]);
    }

    private boolean dfs(String[] prison, Point position, boolean[][] visited) {
        int rows = prison.length;
        if (position.getX() < 0 || position.getX() >= rows
                || position.getY() < 0 || position.getY() >= prison[position.getX()].length()
                || visited[position.getX()][position.getY()])
            return false;

        visited[position.getX()][position.getY()] = true;

        if (isAWall(prison, position) || isGuardVisible(prison, position)) {
            return false;
        }

        if (thePrisonerReachedTheExit(prison, position)) {
            return true;
        }

        boolean canEscape = dfs(prison, new Point(position.getX() - 1, position.getY()), visited);
        if (!canEscape) {
            canEscape = dfs(prison, new Point(position.getX() + 1, position.getY()), visited);
        }
        if (!canEscape) {
            canEscape = dfs(prison, new Point(position.getX(), position.getY() + 1), visited);
        }
        if (!canEscape) {
            canEscape = dfs(prison, new Point(position.getX(), position.getY() - 1), visited);
        }

        return canEscape;
    }

    private boolean thePrisonerReachedTheExit(String[] prison, Point position) {
        return prison[position.getX()].charAt(position.getY()) == 'S';
    }

    private boolean isAWall(String[] prison, Point position) {
        return prison[position.getX()].charAt(position.getY()) != ' '
                && prison[position.getX()].charAt(position.getY()) != 'P'
                && prison[position.getX()].charAt(position.getY()) != 'S';
    }

    private boolean isGuardVisible(String[] prison, Point position) {
        int rows = prison.length;
        if (position.getX() + 1 < rows && position.getY() < prison[position.getX() + 1].length()
                && prison[position.getX() + 1].charAt(position.getY()) == '^') {
            return true;
        }

        if (position.getX() - 1 > 0 && position.getY() < prison[position.getX() - 1].length()
                && prison[position.getX() - 1].charAt(position.getY()) == 'v') {
            return true;
        }

        int cols = prison[position.getX()].length();
        if (position.getY() + 1 < cols && prison[position.getX()].charAt(position.getY() + 1) == '<') {
            return true;
        }

        if (position.getY() - 1 > 0 && prison[position.getX()].charAt(position.getY() - 1) == '>') {
            return true;
        }

        return false;
    }

    private Point findPrisionerInitialPosition(String[] prison) {
        int startRow = -1;
        int startColum = -1;

        for (int row = 0; row < prison.length; row++) {
            for (int column = 0; column < prison[row].length(); column++) {
                if (prison[row].charAt(column) == 'P') {
                    startRow = row;
                    startColum = column;
                    break;
                }
            }
        }

        return new Point(startRow, startColum);
    }

    class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
