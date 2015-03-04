package org.datalorax.populace.graph;

import org.apache.commons.lang3.Validate;
import org.datalorax.populace.field.filter.FieldFilter;
import org.datalorax.populace.graph.inspector.Inspector;
import org.datalorax.populace.typed.ImmutableTypeMap;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * @author datalorax - 28/02/2015.
 */
public class WalkerContext {
    private final FieldFilter fieldFilter;
    private final ImmutableTypeMap<Inspector> inspectors;

    public WalkerContext(final FieldFilter fieldFilter, final ImmutableTypeMap<Inspector> inspectors) {
        Validate.notNull(fieldFilter, "fieldFilter null");
        Validate.notNull(inspectors, "inspector null");
        Validate.notNull(inspectors.getDefault(), "No default inspector provided");
        Validate.notNull(inspectors.getArrayDefault(), "No default inspector provided for array types");
        this.fieldFilter = fieldFilter;
        this.inspectors = inspectors;
    }

    public boolean isExcludedField(final Field field) {
        return !fieldFilter.evaluate(field);
    }

    public Inspector getInspector(final Type type) {
        return inspectors.get(type);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final WalkerContext that = (WalkerContext) o;
        return fieldFilter.equals(that.fieldFilter) && inspectors.equals(that.inspectors);
    }

    @Override
    public int hashCode() {
        int result = fieldFilter.hashCode();
        result = 31 * result + inspectors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WalkerContext{" +
                "fieldFilter=" + fieldFilter +
                ", inspectors=" + inspectors +
                '}';
    }
}
