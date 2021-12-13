package org.erni.adventofcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day12PassagePathing {

    static String[][] paths = {
            {"DA","xn"},
            {"KD","ut"},
            {"gx","ll"},
            {"dj","PW"},
            {"xn","dj"},
            {"ll","ut"},
            {"xn","gx"},
            {"dg","ak"},
            {"DA","start"},
            {"ut","gx"},
            {"YM","ll"},
            {"dj","DA"},
            {"ll","xn"},
            {"dj","YM"},
            {"start","PW"},
            {"dj","start"},
            {"PW","gx"},
            {"YM","gx"},
            {"xn","ak"},
            {"PW","ak"},
            {"xn","PW"},
            {"YM","end"},
            {"end","ll"},
            {"ak","end"},
            {"ak","DA"}
    }; // 3369, works ok

    static String[][] pathsTest = {
            {"start","A"},
            {"start","b"},
            {"A","c"},
            {"A","b"},
            {"b","d"},
            {"A","end"},
            {"b","end"}
    }; // result: 10, works ok

    static String[][] pathsTest2 = {
            {"dc","end"},
            {"HN","start"},
            {"start","kj"},
            {"dc","start"},
            {"dc","HN"},
            {"LN","dc"},
            {"HN","end"},
            {"kj","sa"},
            {"kj","HN"},
            {"kj","dc"}
    }; // result 19, works ok

    public static int pathsNumber = 0;

    public static Set<String> pathsToTheEnd = new HashSet<>();

    public static void move(Map<String, Set<String>> pathsMap, String position, Set<String> visited) {
        if (visited.contains(position)) return;
        if (position.equals("end")) {
            pathsNumber++;
            return;
        }
        // if non-capital we add it as visited
        if (!position.equals(position.toUpperCase())) visited.add(position);

        Set<String> destinies = pathsMap.get(position);
        if (destinies == null) return;
        for (String destiny : destinies) {
            move(pathsMap,destiny,new HashSet<>(visited));
        }
    }

    public static void part1(Map<String, Set<String>> pathsMap) {
        Set<String> visited = new HashSet<>();
        move(pathsMap, "start", visited);
        System.out.println(pathsNumber);
    }

    // start,A,b,A,c,A,c,A,end
    public static void move2(Map<String, Set<String>> pathsMap, String position, Set<String> visited, String smallCave, boolean visitedOnce, String trace) {
        if (visited.contains(position)) return;

        if (position.equals("end")) {
            pathsNumber++;
            pathsToTheEnd.add(trace);
            return;
        }
        // if non-capital we add it as visited
        if (!position.equals(position.toUpperCase())) {
            if (!position.equals(smallCave)) visited.add(position);
            else {
                if (visitedOnce) visited.add(position);
                else visitedOnce = true;
            }
        }

        Set<String> destinies = pathsMap.get(position);
        if (destinies == null) return;
        for (String destiny : destinies) {
            move2(pathsMap,destiny,new HashSet<>(visited), smallCave, visitedOnce, trace + "," + destiny);
        }
    }

    public static void part2(Map<String, Set<String>> pathsMap) {
        Set<String> lowerCases = new HashSet<>();

        for (String key : pathsMap.keySet()) {
            if (!key.equals(key.toUpperCase())) {
                lowerCases.add(key);
            }
        }
        lowerCases.remove("start");
        lowerCases.remove("end");

        System.out.println("Lower case set: " + lowerCases.toString());

        Set<String> visited;
        for (String smallCave: lowerCases) {
            visited = new HashSet<>();
            move2(pathsMap, "start", visited, smallCave, false, "start");
        }
        System.out.println(pathsNumber);
        System.out.println(pathsToTheEnd.size());
    }

    public static void main(String[] args) {
        Map<String, Set<String>> pathsMap = new HashMap<>();

        for (String[] path: paths) {
            Set<String> destinies = pathsMap.get(path[0]);
            if (destinies == null) destinies = new HashSet<>();
            destinies.add(path[1]);
            pathsMap.put(path[0], destinies);

            destinies = pathsMap.get(path[1]);
            if (destinies == null) destinies = new HashSet<>();
            destinies.add(path[0]);
            pathsMap.put(path[1], destinies);
        }

        System.out.println(pathsMap.toString());

        //part1(pathsMap);
        part2(pathsMap);
    }

}
