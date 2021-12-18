package org.erni.adventofcode;

public class MutableLong {
    MutableLong(long value) { this.value = value; }
    MutableLong() {
        this.value = 1;
    }
    private long value;
    public void increment () { ++value; }
    public void increment (long dec) { value += dec; }
    public void decrement () { --value; }
    public long get ()       { return value; }
    public void set (long val)       { this.value = val;}

}
