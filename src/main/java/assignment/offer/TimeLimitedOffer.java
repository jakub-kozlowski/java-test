package assignment.offer;

import assignment.Item;

import java.time.LocalDate;
import java.util.List;

public abstract class TimeLimitedOffer implements Offer {

    private final LocalDate timeFrom;
    private final LocalDate timeTo;

    public TimeLimitedOffer() {
        timeFrom = initTimeFrom();
        timeTo = initTimeTo();
    }

    protected abstract LocalDate initTimeFrom();

    protected abstract LocalDate initTimeTo();

    @Override
    public boolean doesApply(List<Item> items, int purchaseDaysDelta) {
        return isWithinOfferDate(purchaseDaysDelta) && doesApplyForItems(items);
    }

    private boolean isWithinOfferDate(int purchaseDaysDelta) {
        LocalDate date = LocalDate.now().plusDays(purchaseDaysDelta);
        return (date.isEqual(timeFrom) || date.isAfter(timeFrom))
                && (date.isEqual(timeTo) || date.isBefore(timeTo));
    }

    protected abstract boolean doesApplyForItems(List<Item> items);
}
