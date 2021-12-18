package org.erni.adventofcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day14ExtendedPolymerization {

    static String templateTest = "NNCB";
    static String template = "FSHBKOOPCFSFKONFNFBB";

    static String[][] rulesTest = {
            {"CH","B"},
            {"HH","N"},
            {"CB","H"},
            {"NH","C"},
            {"HB","C"},
            {"HC","B"},
            {"HN","C"},
            {"NN","C"},
            {"BH","H"},
            {"NC","B"},
            {"NB","B"},
            {"BN","B"},
            {"BB","N"},
            {"BC","B"},
            {"CC","N"},
            {"CN","C"}
    };

    static String[][] rules = {
            {"FO", "K"},
            {"FF", "H"},
            {"SN", "C"},
            {"CC", "S"},
            {"BB", "V"},
            {"FK", "H"},
            {"PC", "P"},
            {"PH", "N"},
            {"OB", "O"},
            {"PV", "C"},
            {"BH", "B"},
            {"HO", "C"},
            {"VF", "H"},
            {"HB", "O"},
            {"VO", "N"},
            {"HK", "N"},
            {"OF", "V"},
            {"PF", "C"},
            {"KS", "H"},
            {"KV", "F"},
            {"PO", "B"},
            {"BF", "P"},
            {"OO", "B"},
            {"PS", "S"},
            {"KC", "P"},
            {"BV", "K"},
            {"OC", "B"},
            {"SH", "C"},
            {"SF", "P"},
            {"NH", "C"},
            {"BS", "C"},
            {"VH", "F"},
            {"CH", "S"},
            {"BC", "B"},
            {"ON", "K"},
            {"FH", "O"},
            {"HN", "O"},
            {"HS", "C"},
            {"KK", "V"},
            {"OK", "K"},
            {"VC", "H"},
            {"HV", "F"},
            {"FS", "H"},
            {"OV", "P"},
            {"HF", "F"},
            {"FB", "O"},
            {"CK", "O"},
            {"HP", "C"},
            {"NN", "V"},
            {"PP", "F"},
            {"FC", "O"},
            {"SK", "N"},
            {"FN", "K"},
            {"HH", "F"},
            {"BP", "O"},
            {"CP", "K"},
            {"VV", "S"},
            {"BO", "N"},
            {"KN", "S"},
            {"SB", "B"},
            {"SC", "H"},
            {"OS", "S"},
            {"CF", "K"},
            {"OP", "P"},
            {"CO", "C"},
            {"VK", "C"},
            {"NB", "K"},
            {"PB", "S"},
            {"FV", "B"},
            {"CS", "C"},
            {"HC", "P"},
            {"PK", "V"},
            {"BK", "P"},
            {"KF", "V"},
            {"NS", "P"},
            {"SO", "C"},
            {"CV", "P"},
            {"NP", "V"},
            {"VB", "F"},
            {"KO", "C"},
            {"KP", "F"},
            {"KH", "N"},
            {"VN", "S"},
            {"NO", "P"},
            {"NF", "K"},
            {"CB", "H"},
            {"VS", "V"},
            {"NK", "N"},
            {"KB", "C"},
            {"SV", "F"},
            {"NC", "H"},
            {"VP", "K"},
            {"PN", "H"},
            {"OH", "K"},
            {"CN", "N"},
            {"BN", "F"},
            {"NV", "K"},
            {"SP", "S"},
            {"SS", "K"},
            {"FP", "S"}
    };

    public static String step(Map<String,String> rulesMap, String template) {
        StringBuilder result = new StringBuilder();
        result.append(template.charAt(0));

        for (int i = 0; i < template.length() - 1; i++) {
            String key = template.substring(i, i + 2);
            String element = rulesMap.get(key);
            result.append(element).append(key.charAt(1));
        }

        return result.toString();
    }

    public static void part1(Map<String,String> rulesMap, int steps, String temp) {
        String result = step(rulesMap, temp);

        for (int i = 1; i < steps; i++) {
            result = step(rulesMap, result);
        }

        String res = result.toString();
        //System.out.println("Result after " + steps + " steps, is : " + res);

        Map<Character, MutableLong> elements = new HashMap<>();
        for (int i = 0; i < res.length(); i++) {
            Character el = res.charAt(i);
            MutableLong count = elements.get(el);
            if (count == null) {
                MutableLong init = new MutableLong();
                elements.put(el, init);
            } else count.increment();
        }

        long max = 0;
        long min = Integer.MAX_VALUE;
        for (MutableLong count : elements.values()) {
            if (count.get() > max) max = count.get();
            if(count.get() < min) min = count.get();
        }

        System.out.println("Max is: " + max);
        System.out.println("Min is: " + min);
        System.out.println("Difference: " + (max - min));
    }

    public static Map<String,MutableLong> getCurrentPairs(Map<String,MutableLong> pairs) {
        Map<String,MutableLong> currentPairs = new HashMap<>();

        for (String pair : pairs.keySet()) {
            MutableLong m = pairs.get(pair);
            long pairsNum = m.get();

            if (pairsNum > 0) {
                currentPairs.put(pair, new MutableLong(pairsNum));
                m.set(0);
            }
        }

        return currentPairs;
    }

    public static void step2(Map<String,String> rulesMap, Map<Character, MutableLong> elements, Map<String,MutableLong> pairs, int totalSteps) {
        int steps = 1;

        while (steps <= totalSteps) {
            Map<String,MutableLong> currentPairs = getCurrentPairs(pairs);

            for (String pair : currentPairs.keySet()) {
                long pairsNum  = currentPairs.get(pair).get();

                String el = rulesMap.get(pair);
                MutableLong count = elements.get(el.charAt(0));
                if (count == null) elements.put(el.charAt(0), new MutableLong(pairsNum));
                else count.increment(pairsNum);

                pairs.get(el + pair.charAt(1)).increment(pairsNum);
                pairs.get(pair.charAt(0) + el).increment(pairsNum);
            }
            steps++;
        }
    }

    public static void part2(Map<String,String> rulesMap, Map<String,MutableLong> pairs, int totalSteps, String temp) {
        Map<Character, MutableLong> elements = new HashMap<>();

        for (int i = 0; i < temp.length(); i++) {
            Character el = temp.charAt(i);
            MutableLong count = elements.get(el);
            if (count == null) {
                MutableLong init = new MutableLong();
                elements.put(el, init);
            } else count.increment();
        }

        for (int i = 0; i < temp.length() - 1; i++) {
            String pair = temp.substring(i, i + 2);
            MutableLong count = pairs.get(pair);
            count.increment();
        }

        step2(rulesMap, elements, pairs, totalSteps);

        long max = 0;
        long min = Long.MAX_VALUE;
        for (MutableLong count : elements.values()) {
            if (count.get() > max) max = count.get();
            if(count.get() < min) min = count.get();
        }

        System.out.println("Max is: " + max);
        System.out.println("Min is: " + min);
        System.out.println("Difference: " + (max - min));
    }

    public static void main(String[] args) {
        Map<String,String> rulesMap = new HashMap<>();
        Map<String,MutableLong> pairs = new HashMap<>();

        for (String[] rule : rules) {
            rulesMap.put(rule[0], rule[1]);

            MutableLong init = new MutableLong();
            init.decrement();
            pairs.put(rule[0], init);
        }

        String temp = template;

        //part1(rulesMap, 40, temp);
        part2(rulesMap, pairs, 40, temp);
    }

}
