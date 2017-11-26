/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

import java.util.Objects;

/**
 * Contains a range of a minimum and maximum integer value.
 * 
 * @author Fernando.Ocampo
 */
public class Range {
    /**
     * Check if a minimum value was added.
     */
    private boolean hasMinimum;
    /**
     * Check if a maximum value was added.
     */
    private boolean hasMaximum;
    /**
     * The minimum value of the range.
     */
    private Number minimum;
    /**
     * The minimum value by default to use if minimum is empty.
     */
    private Number defaultMinimum;
    /**
     * The maximum value of the range.
     */
    private Number maximum;
    /**
     * The maximum value by default to use if maximum is empty.
     */
    private Number defaultMaximum;

    public Range() {
    }

    public Range(Integer minimum, Integer maximum) {
        if(minimum != null) {
            setMinimum(minimum);
        }
        if(maximum != null) {
            setMaximum(maximum);
        }
    }

    public Range(Number minimum, Number maximum) {
        if(minimum != null) {
            setMinimum(minimum);
        }
        if(maximum != null) {
            setMaximum(maximum);
        }
    }

    public Number getMinimum() {
        if(!hasMinimum) {
            return defaultMinimum;
        }
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
        this.hasMinimum = true;
    }

    public Number getMaximum() {
        if(!hasMaximum) {
            return defaultMaximum;
        }
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
        this.hasMaximum = true;
    }

    public void setMinimum(Number minimum) {
        this.minimum = minimum;
        this.hasMinimum = true;
    }

    public void setMaximum(Number maximum) {
        this.maximum = maximum;
        this.hasMaximum = true;
    }

    public boolean hasMinimum() {
        return hasMinimum;
    }

    public boolean hasMaximum() {
        return hasMaximum;
    }
    
    public boolean hasMinimumAndMaximum() {
        return hasMinimum && hasMaximum;
    }
    
    public boolean isEmpty() {
        return !hasMinimum && !hasMaximum;
    }

    public void setDefaultMinimum(Number defaultMinimum) {
        this.defaultMinimum = defaultMinimum;
    }

    public void setDefaultMaximum(Number defaultMaximum) {
        this.defaultMaximum = defaultMaximum;
    }

    @Override
    public String toString() {
        return "Range{" + "hasMinimum=" + hasMinimum + ", hasMaximum=" + hasMaximum + ", minimum=" + minimum + ", defaultMinimum=" + defaultMinimum + ", maximum=" + maximum + ", defaultMaximum=" + defaultMaximum + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.minimum);
        hash = 59 * hash + Objects.hashCode(this.maximum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Range other = (Range) obj;
        if (!Objects.equals(this.minimum, other.minimum)) {
            return false;
        }
        if (!Objects.equals(this.maximum, other.maximum)) {
            return false;
        }
        return true;
    }
}
