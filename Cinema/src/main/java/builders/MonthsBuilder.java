package builders;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class MonthsBuilder {
    public enum Month {JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUN, JULY, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER}
    final Set<Month> months;
    public enum Week {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}
    final Set<Week> weeks;

    public abstract static class Builder<T extends Builder<T>> {
        EnumSet<Month> months = EnumSet.noneOf(Month.class);
        EnumSet<Week> weeks = EnumSet.noneOf(Week.class);
        public T addDayOfSeance(Month month, Week week) {
            months.add(Objects.requireNonNull(month));
            weeks.add(Objects.requireNonNull(week));
            return self();
        }
        public abstract MonthsBuilder build();

        protected abstract T self();
    }
    public MonthsBuilder(Builder<?> builder) {
        months = builder.months.clone();
        weeks = builder.weeks.clone();
    }

    @Override
    public String toString() {
        return "months=" + months +
                ", weeks=" + weeks +
                '}';
    }
}
