package com.verma.tarun;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

import static java.lang.Math.*;

public class BloomFilter {
    private final BitSet bitSet;
    private final int size;
    private final int numHashFunctions;
    static final double probabilityFalsePositive = 0.01d;

    public BloomFilter(int n) {
        this.size = (int) ceil((n * log(probabilityFalsePositive)) / log(1 / pow(2, log(2))));;
        this.numHashFunctions = (int) round(((double) size / n) * log(2));
        this.bitSet = new BitSet(size);
    }

    public void add(String element) {
        for (int i=0; i<numHashFunctions; i++) {
            int hash = hash(element, i);
            bitSet.set(hash%size, true);
        }
    }

    public boolean contains(String element) {
        for (int i=0; i<numHashFunctions; i++) {
            int hash = hash(element, i);
            if (!bitSet.get(hash%size)) {
                return false;
            }
        }
        return true;
    }

    private int hash(String element, int seed) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String inputData = element + '-' + seed;
            byte[] hashBytes = digest.digest(inputData.getBytes());
            int result = 0;
            for (byte b : hashBytes) {
                result = (result << 8) | (b & 0xFF);
            }
            return Math.abs(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
