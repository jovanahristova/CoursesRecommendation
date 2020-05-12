package com.mk.ukim.finki.RecommendationSystem.util;

import java.util.Collection;

public class CosineSimilarityCalculator {
    public static double cosineSimilarity (Collection<Double> c1, Collection<Double> c2) {
        double [] array1;
        double [] array2;
        array1 = c1.stream().mapToDouble(i -> i).toArray();
        array2 = c2.stream().mapToDouble(i -> i).toArray();
        double up = 0.0;
        double down1=0, down2=0;

        for (int i=0;i<c1.size();i++) {
            up+=(array1[i] * array2[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down1+=(array1[i]*array1[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down2+=(array2[i]*array2[i]);
        }

        return up/(Math.sqrt(down1)*Math.sqrt(down2));
    }
}
