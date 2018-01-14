package com.imooc.security.rbac.mapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.imooc.security.rbac.mybatis.entity.SysRole;
import com.imooc.security.rbac.mybatis.entity.SysUser;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Scanner;

public class ResultGenerator {

    public static void main(String[] args) {
        System.out.println((new ResultGenerator()).getResultsStr(SysUser.class));
//        fuLu(0.022);
    }


    /**
     * 1.用于获取结果集的映射关系
     */
    public String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toLowerCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }

    public static void fuLu(double r) {
        while (true) {
            System.out.println("---------------------------------------------");
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入天数: ");//println换行；print不换行
            int days = scanner.nextInt();//数据类型为int

            System.out.println("----------------------方式一--------------------");
//            double a[] = {373.02, 374.98, 374.98, 349.32, 705.74};
//            double b[] = {373.02, 374.98, 374.98, 349.32, 705.74};

            double a[] = {505.89, 508.55, 508.55, 503.74, 924.11};
            double b[] = {505.89, 508.55, 508.55, 503.74, 924.11};

            for (int i = 1; i <= days; i++) {
                for (int j = 0; j < a.length; j++) {
                    a[j] *= (1 + r);
                    BigDecimal bg = new BigDecimal(a[j]);
                    a[j] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }

                for (int k = 0; k < a.length - 1; k++) {
                    if (a[k] >= 311.0) {
                        a[k] -= 11.0;
                        a[a.length - 1] += 10;
                    } else {
                        BigDecimal bg = new BigDecimal(a[k]);
                        a[k] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }
            }

            double sum = 0.0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i];
                System.out.println(a[i]);
            }
            System.out.println("求和:" + sum);
            System.out.println("----------------------方式二--------------------");

            for (int i = 1; i <= days; i++) {
                for (int j = 0; j < b.length; j++) {
                    b[j] *= (1 + r);

                    BigDecimal bg = new BigDecimal(b[j]);
                    b[j] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }

            sum = 0.0;
            for (int i = 0; i < b.length; i++) {
                sum += b[i];
                System.out.println(b[i]);
            }
            System.out.println("求和:" + sum);
        }
    }


}
