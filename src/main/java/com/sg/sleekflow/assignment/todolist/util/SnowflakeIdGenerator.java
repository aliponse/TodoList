package com.sg.sleekflow.assignment.todolist.util;

public class SnowflakeIdGenerator {

    private final long startTimestamp = 1609459200000L; // 2021-01-01 00:00:00
    private final long machineIdBits = 10L;
    private final long sequenceBits = 12L;

    private final long maxMachineId = ~(-1L << machineIdBits);
    private final long maxSequence = ~(-1L << sequenceBits);

    private final long timestampShift = machineIdBits + sequenceBits;
    private final long machineIdShift = sequenceBits;

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    private final long machineId;

    public SnowflakeIdGenerator(long machineId) {
        if (machineId > maxMachineId || machineId < 0) {
            throw new IllegalArgumentException("Machine ID out of range");
        }
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - startTimestamp) << timestampShift)
                | (machineId << machineIdShift)
                | sequence;
    }

    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1);
        System.out.println("Generated ID: " + generator.nextId());
    }
}