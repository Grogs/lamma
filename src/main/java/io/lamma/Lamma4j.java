package io.lamma;

import static io.lamma.LammaConversion.*;

import scala.collection.immutable.List;
import scala.collection.JavaConverters;

public class Lamma4j {

    public static java.util.List<Date> sequence(Date from, Date to) {
        DateRange range = new DateRange(from, to, DateRange$.MODULE$.apply$default$3(), DateRange$.MODULE$.apply$default$4(), DateRange$.MODULE$.apply$default$5(), DateRange$.MODULE$.apply$default$6());
        return range.javaList();
    }

    public static java.util.List<Date> sequence(Date from, Date to, Pattern pattern) {
        DateRange range = new DateRange(from, to, pattern, DateRange$.MODULE$.apply$default$4(), DateRange$.MODULE$.apply$default$5(), DateRange$.MODULE$.apply$default$6());
        return range.javaList();
    }

    public static java.util.List<Date> sequence(Date from, Date to, Pattern pattern, Shifter shifter) {
        DateRange range = new DateRange(from, to, pattern, DateRange$.MODULE$.apply$default$4(), list(shifter), DateRange$.MODULE$.apply$default$6());
        return range.javaList();
    }

    public static java.util.List<Date> sequence(Date from, Date to, Pattern pattern, Shifter shifter, Selector selector) {
        DateRange range = new DateRange(from, to, pattern, DateRange$.MODULE$.apply$default$4(), list(shifter), list(selector));
        return range.javaList();
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern) {
        Schedule schedule = Schedule$.MODULE$.apply(start, end, pattern, Schedule$.MODULE$.apply$default$4(), Schedule$.MODULE$.apply$default$5(), Schedule$.MODULE$.apply$default$6());
        return new Schedule4j(schedule);
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern, java.util.List<DateDef> dateDefs) {
        return schedule(start, end, pattern, Schedule$.MODULE$.apply$default$4(), JavaConverters.asScalaBufferConverter(dateDefs).asScala().toList(), Schedule$.MODULE$.apply$default$6());
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern, List<DateDef> dateDefs) {
        Schedule schedule = Schedule$.MODULE$.apply(start, end, pattern, Schedule$.MODULE$.apply$default$4(), dateDefs, Schedule$.MODULE$.apply$default$6());
        return new Schedule4j(schedule);
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern, PeriodBuilder periodBuilder) {
        Schedule schedule = Schedule$.MODULE$.apply(start, end, pattern, periodBuilder, Schedule$.MODULE$.apply$default$5(), Schedule$.MODULE$.apply$default$6());
        return new Schedule4j(schedule);
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern, PeriodBuilder periodBuilder, java.util.List<DateDef> dateDefs) {
        return schedule(start, end, pattern, periodBuilder, JavaConverters.asScalaBufferConverter(dateDefs).asScala().toList(), Schedule$.MODULE$.apply$default$6());
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern, PeriodBuilder periodBuilder, List<DateDef> dateDefs) {
        Schedule schedule = Schedule$.MODULE$.apply(start, end, pattern, periodBuilder, dateDefs, Schedule$.MODULE$.apply$default$6());
        return new Schedule4j(schedule);
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern, PeriodBuilder periodBuilder, java.util.List<DateDef> dateDefs, Boolean forward) {
        return schedule(start, end, pattern, periodBuilder, JavaConverters.asScalaBufferConverter(dateDefs).asScala().toList(), forward);
    }

    public static Schedule4j schedule(Date start, Date end, Pattern pattern, PeriodBuilder periodBuilder, List<DateDef> dateDefs, Boolean forward) {
        Schedule schedule = Schedule$.MODULE$.apply(start, end, pattern, periodBuilder, dateDefs, forward);
        return new Schedule4j(schedule);
    }
}
