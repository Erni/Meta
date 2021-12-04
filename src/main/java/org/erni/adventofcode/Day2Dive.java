package org.erni.adventofcode;

import java.util.HashMap;
import java.util.Map;

public class Day2Dive {

    static String[] commands = {"forward 9","forward 7","down 7","down 3","forward 2","forward 3","forward 7","down 6","forward 7","down 7","forward 9","down 9","up 2","down 5","up 1","forward 5","forward 6","up 4","down 3","down 4","down 5","up 6","down 3","forward 6","forward 4","down 4","forward 5","down 2","up 1","up 8","down 1","down 6","forward 8","down 4","forward 8","forward 6","forward 2","forward 2","forward 3","forward 8","up 9","up 2","down 3","up 3","forward 5","forward 2","up 5","forward 9","down 7","down 2","up 7","down 4","down 6","up 2","down 9","forward 7","down 8","forward 6","up 1","forward 6","forward 4","down 5","forward 6","down 8","down 3","forward 7","down 8","up 7","down 1","up 1","forward 9","down 7","up 3","down 6","down 6","down 6","down 7","down 9","down 6","down 9","down 8","down 3","down 7","down 3","up 8","down 5","down 9","up 4","forward 5","forward 5","forward 2","up 1","forward 6","down 6","down 2","forward 1","forward 8","down 4","down 1","down 8","down 7","forward 6","forward 8","down 8","up 1","up 1","forward 1","forward 3","up 8","down 1","forward 4","down 3","forward 3","forward 4","forward 3","down 3","down 9","down 3","up 6","forward 6","forward 8","forward 2","up 1","up 4","up 4","down 2","down 2","down 2","forward 7","up 9","down 9","up 1","down 5","forward 4","down 2","down 4","forward 3","down 7","down 9","forward 1","up 5","down 5","down 5","forward 2","down 6","forward 8","up 4","forward 6","up 1","down 4","forward 2","down 9","forward 5","down 7","down 8","down 9","forward 6","up 6","forward 9","forward 9","down 7","forward 7","forward 5","up 9","forward 3","down 9","down 1","down 8","down 4","forward 5","forward 6","forward 8","forward 8","down 4","down 3","down 8","forward 3","down 6","down 8","down 2","up 8","up 9","down 6","forward 3","down 4","down 6","forward 9","forward 6","up 2","down 8","forward 2","down 7","forward 9","up 9","down 9","down 2","forward 9","down 4","down 9","up 4","forward 6","down 2","down 9","forward 8","forward 2","up 8","forward 9","forward 2","forward 3","down 2","up 3","forward 9","down 6","down 3","down 1","forward 9","forward 8","down 9","up 7","down 8","up 7","forward 1","forward 1","forward 7","down 2","down 1","up 1","up 6","down 5","up 9","up 7","forward 1","forward 6","forward 1","up 4","down 6","forward 2","up 7","down 2","up 8","forward 9","forward 6","forward 3","forward 8","down 1","forward 8","up 3","forward 1","forward 1","up 9","down 1","down 8","down 2","forward 8","down 8","forward 7","down 5","forward 8","forward 3","forward 6","forward 7","up 5","down 5","forward 8","down 2","forward 3","down 4","down 9","forward 6","forward 5","up 4","forward 7","down 3","forward 9","forward 5","down 3","up 5","forward 4","forward 8","down 7","up 2","forward 7","down 5","up 2","down 9","forward 4","down 3","forward 5","forward 4","down 3","forward 6","up 1","forward 8","down 1","up 7","forward 8","up 1","up 1","forward 2","down 8","forward 4","forward 8","up 6","forward 5","forward 7","up 6","up 4","up 6","down 1","forward 3","down 1","down 1","down 8","forward 8","down 5","down 5","forward 5","forward 9","down 9","forward 7","down 3","down 5","forward 6","down 1","down 5","up 8","down 9","forward 3","down 6","up 2","down 2","forward 2","up 2","forward 8","down 2","forward 9","forward 2","down 7","down 5","forward 1","forward 7","up 6","up 8","forward 8","forward 8","up 3","forward 8","down 6","down 6","forward 4","down 8","down 5","down 7","forward 1","forward 9","forward 9","up 5","down 9","down 1","forward 4","forward 1","up 9","forward 6","down 6","forward 2","up 6","forward 9","up 1","down 2","up 3","forward 2","forward 1","forward 6","down 9","up 1","forward 7","up 3","forward 6","forward 6","up 2","down 8","forward 4","down 4","forward 2","forward 2","down 4","down 7","down 4","down 5","forward 3","down 1","forward 1","forward 8","down 7","up 1","forward 7","forward 2","down 9","down 2","up 2","forward 3","down 4","down 7","down 8","forward 4","forward 5","forward 3","up 3","down 6","forward 4","forward 4","forward 8","forward 1","up 2","up 3","down 4","up 9","forward 1","forward 1","forward 9","down 2","down 5","up 9","down 7","down 9","down 2","down 4","forward 1","forward 1","forward 8","down 9","down 6","forward 2","up 3","down 8","forward 1","forward 8","forward 4","up 7","forward 5","forward 2","forward 2","up 8","down 5","forward 6","down 3","up 5","forward 8","forward 3","forward 9","down 1","down 3","forward 8","down 2","forward 6","forward 2","down 3","down 3","forward 6","forward 4","forward 7","forward 2","up 7","up 4","up 6","forward 9","down 3","down 3","up 7","down 4","up 3","up 3","down 5","forward 1","up 3","down 1","forward 2","up 9","forward 7","down 6","forward 4","forward 8","up 1","forward 6","down 7","down 4","up 9","forward 4","down 7","up 1","forward 9","down 4","down 7","forward 1","down 6","down 6","forward 3","up 8","forward 3","down 1","down 5","down 8","forward 2","up 5","forward 2","up 7","forward 5","forward 1","forward 3","forward 4","forward 5","up 1","forward 9","down 5","down 7","up 9","down 9","forward 7","up 6","up 7","forward 2","forward 1","up 4","forward 6","forward 9","down 1","forward 4","down 5","forward 4","down 3","down 5","forward 6","forward 3","down 3","down 8","down 2","down 4","down 6","down 4","forward 2","up 9","down 3","forward 1","forward 9","forward 5","forward 5","forward 9","up 1","down 4","down 4","up 7","down 3","up 3","up 4","forward 3","forward 1","forward 8","up 6","down 8","down 4","forward 7","forward 9","forward 2","forward 8","up 2","down 4","down 5","forward 9","down 6","down 7","down 8","up 8","forward 3","forward 7","forward 8","up 2","down 9","down 6","forward 3","forward 4","down 4","forward 2","up 6","forward 1","forward 7","down 2","down 1","forward 2","forward 2","down 2","forward 2","forward 7","up 4","down 3","forward 9","down 7","down 7","down 6","forward 3","forward 9","down 9","forward 2","down 5","down 4","down 9","up 9","down 6","down 8","down 1","forward 8","up 4","up 4","down 8","forward 6","down 2","forward 4","forward 3","forward 2","forward 4","down 4","forward 6","down 9","up 7","up 5","down 7","down 4","up 3","forward 4","down 9","forward 6","forward 4","forward 4","down 9","forward 3","forward 2","up 7","forward 3","down 1","down 3","up 5","down 8","down 3","down 4","forward 7","forward 9","up 2","forward 3","up 4","down 5","up 3","up 9","down 6","down 2","down 5","up 4","up 6","forward 4","forward 6","up 5","up 5","forward 8","down 6","forward 6","down 7","down 5","down 3","down 8","forward 6","forward 9","forward 9","up 9","down 3","up 5","forward 4","down 7","forward 5","down 7","down 4","forward 2","forward 9","down 8","up 3","up 7","down 7","up 7","forward 3","down 2","forward 7","down 4","forward 1","down 6","forward 1","up 4","down 7","up 3","forward 7","forward 5","forward 7","forward 6","up 2","down 4","down 8","down 4","up 3","forward 3","up 3","up 3","down 7","down 2","down 3","forward 7","down 6","down 9","up 1","down 8","down 6","down 3","up 2","up 6","forward 9","forward 5","forward 4","forward 9","down 9","forward 2","up 7","down 4","down 8","up 2","forward 6","up 6","up 4","down 2","forward 6","forward 4","up 3","down 6","forward 5","forward 3","up 4","down 7","down 2","down 6","up 7","forward 2","forward 1","forward 3","down 2","forward 1","forward 2","forward 4","down 2","down 5","down 7","down 8","down 1","up 1","up 1","forward 9","down 3","down 1","forward 4","up 6","up 8","forward 7","forward 9","down 3","forward 9","down 9","forward 6","down 1","forward 7","down 9","forward 1","down 8","forward 8","up 7","forward 4","up 5","up 9","forward 1","forward 4","forward 3","down 3","down 8","up 3","forward 1","up 5","forward 5","up 6","forward 8","forward 1","down 7","forward 2","down 9","forward 3","forward 7","forward 2","down 4","forward 2","up 6","down 7","up 3","forward 7","down 8","down 3","forward 2","up 7","down 2","down 8","up 6","forward 7","forward 1","down 3","forward 2","forward 8","down 8","forward 1","down 7","down 1","up 5","up 3","forward 5","down 5","up 9","up 9","down 3","up 3","down 4","down 6","up 7","forward 3","up 5","down 3","forward 4","down 1","up 1","up 6","down 8","forward 5","up 2","down 5","forward 6","forward 4","forward 9","down 9","down 5","forward 5","down 7","down 7","down 8","forward 3","down 6","forward 5","forward 5","down 6","forward 3","down 7","up 4","up 3","down 5","forward 9","forward 9","up 9","down 1","up 2","up 3","down 7","forward 3","down 7","down 4","down 5","down 1","down 4","up 9","forward 1","up 8","forward 7","up 6","down 1","up 2","forward 2","up 9","down 6","forward 4","down 2","up 5","forward 1","forward 4","down 6","down 2","up 8","forward 2","forward 8","forward 4","down 9","up 3","forward 5","forward 9","forward 4","down 2","up 4","up 9","down 5","up 2","forward 6","up 2","down 6","up 5","up 3","up 9","forward 8","down 2","forward 7","up 8","down 9","forward 2","forward 2","down 6","forward 9","forward 2","forward 8","up 3","forward 5","down 4","forward 2","down 7","up 6","forward 7","down 6","down 8","down 3","up 4","up 5","down 2","down 9","forward 2","down 7","forward 2","forward 3","forward 9","down 6","down 1","forward 6","down 5","forward 2","down 5","down 1","forward 5","down 4","down 6","down 5","forward 9","up 6","up 5","up 2","down 1","down 8","forward 4","down 2","forward 5","down 1","forward 7","down 8","down 9","down 7","up 1","forward 2","up 8","down 9","down 2","down 1","down 9","down 2","down 5","forward 9","forward 1","down 1","forward 9","forward 7","down 6","down 1","down 7","forward 4","forward 1","forward 4","forward 5","forward 5","down 2","forward 7","forward 6","forward 3","forward 9","up 1","down 5","down 4","down 2","forward 1","up 7","forward 2"};
    static final String FORWARD = "forward";
    static final String UP = "up";
    static final String DOWN = "down";
    static final String DEPTH = "depth";

    public static void part1() {
        Map<String, Integer> depth = new HashMap<>(3);
        depth.put(FORWARD,0);
        depth.put(UP,0);
        depth.put(DOWN,0);

        for (String command : commands) {
            String[] commandAndLength= command.split(" ");
            String comm = commandAndLength[0];
            int length = Integer.valueOf(commandAndLength[1]);
            depth.put(comm, depth.get(comm) + length);
        }

        System.out.println("Map has a size of: " + depth.size());
        int forward = depth.get(FORWARD);
        int up = depth.get(UP);
        int down = depth.get(DOWN);
        System.out.println("Forward: " + forward);
        System.out.println("UP: " + up);
        System.out.println("DOWN: " + down);
        System.out.println(forward * (down - up));
    }

    public static void part2() {
        int aim = 0;
        int horizontal = 0;
        int depth = 0;

        for (String command : commands) {
            String[] commandAndLength= command.split(" ");
            String comm = commandAndLength[0];
            int length = Integer.valueOf(commandAndLength[1]);

            switch (comm) {
                case FORWARD:
                    // It increases your horizontal position by X units.
                    // It increases your depth by your aim multiplied by X.
                    horizontal += length;
                    depth += aim * length;
                    break;
                case UP:
                    aim -= length;
                    break;
                case DOWN:
                    aim += length;
                    break;
            }
        }

        System.out.println(horizontal * depth);
    }

    public static void main(String[] args) {
        part1();
        part2();
    }

}
