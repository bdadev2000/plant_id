package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
final class ExpandedRow {
    private final List<ExpandedPair> pairs;
    private final int rowNumber;
    private final boolean wasReversed;

    public ExpandedRow(List<ExpandedPair> list, int i2, boolean z2) {
        this.pairs = new ArrayList(list);
        this.rowNumber = i2;
        this.wasReversed = z2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandedRow)) {
            return false;
        }
        ExpandedRow expandedRow = (ExpandedRow) obj;
        return this.pairs.equals(expandedRow.getPairs()) && this.wasReversed == expandedRow.wasReversed;
    }

    public List<ExpandedPair> getPairs() {
        return this.pairs;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public int hashCode() {
        return this.pairs.hashCode() ^ Boolean.valueOf(this.wasReversed).hashCode();
    }

    public boolean isEquivalent(List<ExpandedPair> list) {
        return this.pairs.equals(list);
    }

    public boolean isReversed() {
        return this.wasReversed;
    }

    public String toString() {
        return "{ " + this.pairs + " }";
    }
}
