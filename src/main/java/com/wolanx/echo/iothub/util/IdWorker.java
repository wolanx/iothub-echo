package com.wolanx.echo.iothub.util;

import java.util.ArrayList;
import java.util.List;

/**
 * snowflake
 *
 * @author wolanx
 * 1629191974013
 * 1427560723827242019
 * (1629191974013, 2, 27, 1059)
 * @link https://blog.csdn.net/zyt425916200/article/details/52775542
 * @link https://github.com/falcondai/python-snowflake/blob/master/snowflake.py
 */
public class IdWorker {

    private final static long TIME = 1288834974657L;

    private final static int LA = 5;
    private final static int LB = 5;
    private final static int LC = 12;

    public static final int SA_MAX = 1 << LA;
    public static final int SB_MAX = 1 << LB;
    public static final int SC_MAX = 1 << LC;

    private final static int P_ABC = LA + LB + LC;
    private final static int P_BC = LB + LC;
    private final static int P_C = LC;

    private final int workerId;
    private final int datacenterId;

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(66, 123);
        long a = idWorker.encode(1629191974013L);
        System.out.println("a = " + a);
        List<Long> b = idWorker.decode(a);
        System.out.println("b = " + b);
    }

    public IdWorker(int datacenterId, int workerId) {
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    public static synchronized long nextId() {
        IdWorker idWorker = new IdWorker(66, 123);
        long ms = System.currentTimeMillis();
        return idWorker.encode(ms);
    }

    private long encode(long ms) {
        int seqId = 435235;
        return ((ms - TIME) << P_ABC)
                | (datacenterId % SA_MAX << P_BC)
                | (workerId % SB_MAX << P_C)
                | seqId % SC_MAX;
    }

    private List<Long> decode(long id) {
        List<Long> ret = new ArrayList<>();
        ret.add((id >> P_ABC) + TIME);
        ret.add((id >> P_BC) & (SA_MAX - 1));
        ret.add((id >> P_C) & (SB_MAX - 1));
        ret.add(id & (SC_MAX - 1));
        return ret;
    }

}
