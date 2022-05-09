package transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public record Amount(BigDecimal value) {

    public static final Amount ZERO = Amount.valueOf(0);

    public Amount asNegative() {
        return new Amount(this.value.negate());
    }

    public Amount addAndGet(Amount amount) {
        return new Amount(value.add(amount.value));
    }

    public String format(DecimalFormat decimalFormat) {
        return decimalFormat.format(value);

    }


    public static Amount valueOf(int value) {
        return new Amount(new BigDecimal(value));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

}
