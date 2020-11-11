package com.epam.jwd.core_final.util;

import java.util.Collection;

public final class PrintUtil {
    private PrintUtil() { }

    public static void printMsgToConsole(String msg) {
        System.out.println(msg);
    }

    public static void printCollectionToConsole(Collection<?> collection) {
        System.out.print("{");
        for (Object e : collection) {
            System.out.print("{"+e.toString()+"},\n");
        }
        System.out.print("}");
    }
}
