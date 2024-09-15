package me.quyen.filters;

import org.slf4j.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Utils {
    private static int count = 0;
    public static void classInfo(Class<?> clazz, org.slf4j.Logger LOG){
        Optional.ofNullable(clazz).ifPresent(klass -> {
            LOG.info("{},class-name:{}",++Utils.count,klass.toGenericString());
            Arrays.stream(klass.getInterfaces())
                    .forEach(itf -> LOG.info("{},super-interface:{}",++Utils.count,itf.toGenericString()));
            Optional.ofNullable(klass.getSuperclass())
                    .ifPresent(zuper -> LOG.info("{},super-class:{}",++Utils.count,zuper.toGenericString()));
            Stream.concat(
                    Arrays.stream(klass.getConstructors()), Arrays.stream(klass.getDeclaredConstructors())
            ).forEach(c -> LOG.info("{},constructor:{}",++Utils.count,c.toGenericString()));
            Stream.concat(
                    Arrays.stream(klass.getFields()), Arrays.stream(klass.getDeclaredFields())
            ).forEach(f -> LOG.info("{},field:{}",++Utils.count,f.toGenericString()));
            Stream.concat(
                    Arrays.stream(klass.getMethods()), Arrays.stream(klass.getDeclaredMethods())
            ).forEach(m -> LOG.info("{},method:{}",++Utils.count,m.toGenericString()));
            Arrays.stream(klass.getNestMembers())
                    .forEach(nested -> LOG.info("{},nested-class:{}",++Utils.count,nested.toGenericString()));
        });
    }

    public static void printInfo(String methodDetail,String info,Logger log){
        log.info("[ORDER:{}] at [{}] in `{}` = {}"
                ,++CustomHeadersLoggingFilter.count, LocalDateTime.now(),methodDetail,info);
    }
}
