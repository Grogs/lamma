package io.lamma;

import static io.lamma.LammaConversion.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Lamma4jTest {

    @Test
    public void testSequenceGenerationWith2Params() {
        Date start = date(2014, 5, 10);
        Date to = date(2014, 5, 12);
        List<Date> expected = Arrays.asList(date(2014, 5, 10), date(2014, 5, 11), date(2014, 5, 12));
        List<Date> actual = Lamma4j.sequence(start, to);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSequenceGenerationWith3Params() {
        Date start = date(2014, 5, 10);
        Date to = date(2014, 5, 12);
        Pattern pattern = everyDay();
        List<Date> expected = Arrays.asList(date(2014, 5, 10), date(2014, 5, 11), date(2014, 5, 12));
        List<Date> actual = Lamma4j.sequence(start, to, pattern);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSequenceGenerationWith4Params() {
        Date start = date(2014, 5, 10);
        Date to = date(2014, 5, 12);
        Pattern pattern = everyDay();
        Shifter shifter = noShift();
        List<Date> expected = Arrays.asList(date(2014, 5, 10), date(2014, 5, 11), date(2014, 5, 12));
        List<Date> actual = Lamma4j.sequence(start, to, pattern, shifter);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSequenceGenerationWith5Params() {
        Date start = date(2014, 5, 10);
        Date to = date(2014, 5, 12);
        Pattern pattern = everyDay();
        Shifter shifter = noShift();
        Selector selector = sameDay();
        List<Date> expected = Arrays.asList(date(2014, 5, 10), date(2014, 5, 11), date(2014, 5, 12));
        List<Date> actual = Lamma4j.sequence(start, to, pattern, shifter, selector);
        assertThat(actual, is(expected));
    }

    @Test
    public void testScheduleGenerationWith3Params() {
        Date start = date(2014, 5, 1);
        Date end = date(2014, 8, 31);
        Pattern pattern = months(lastDayOfMonth());

        List<Period> expectedPeriods = Lists.newArrayList(
                new Period(date(2014, 5, 1), date(2014, 5, 31)),
                new Period(date(2014, 6, 1), date(2014, 6, 30)),
                new Period(date(2014, 7, 1), date(2014, 7, 31)),
                new Period(date(2014, 8, 1), date(2014, 8, 31))
        );

        Schedule4j schedule = Lamma4j.schedule(start, end, pattern);
        assertThat(schedule.getPeriods(), is(expectedPeriods));
    }

    @Test
    public void testScheduleGenerationWith4Params() {
        Date start = date(2014, 5, 1);
        Date end = date(2014, 8, 31);
        Pattern pattern = months(lastDayOfMonth());
        PeriodBuilder periodBuilder = stubRulePeriodBuilder();

        List<Period> expectedPeriods = Lists.newArrayList(
                new Period(date(2014, 5, 1), date(2014, 5, 31)),
                new Period(date(2014, 6, 1), date(2014, 6, 30)),
                new Period(date(2014, 7, 1), date(2014, 7, 31)),
                new Period(date(2014, 8, 1), date(2014, 8, 31))
        );

        Schedule4j schedule = Lamma4j.schedule(start, end, pattern, periodBuilder);
        assertThat(schedule.getPeriods(), is(expectedPeriods));
    }

    @Test
    public void testScheduleGenerationWith5Params() {
        Date start = date(2014, 5, 1);
        Date end = date(2014, 8, 31);
        Pattern pattern = months(lastDayOfMonth());
        PeriodBuilder periodBuilder = stubRulePeriodBuilder();
        DateDef couponDate = dateDef("CouponDate", modifiedFollowing());
        DateDef settlementDate = dateDef("SettlementDate", otherDate("CouponDate"), shiftCalendarDays(2));

        List<Period> expectedPeriods = Lists.newArrayList(
                new Period(date(2014, 5, 1), date(2014, 5, 31)),
                new Period(date(2014, 6, 1), date(2014, 6, 30)),
                new Period(date(2014, 7, 1), date(2014, 7, 31)),
                new Period(date(2014, 8, 1), date(2014, 8, 31))
        );

        Schedule4j schedule = Lamma4j.schedule(start, end, pattern, periodBuilder, list(couponDate, settlementDate));
        assertThat(schedule.getPeriods(), is(expectedPeriods));
    }
}
